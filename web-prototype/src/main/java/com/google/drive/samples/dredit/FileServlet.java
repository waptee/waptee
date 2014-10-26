/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.drive.samples.dredit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.File.Labels;
import com.google.api.services.drive.model.FileList;
import com.google.drive.samples.dredit.model.ClientFile;
import com.google.gson.Gson;
import com.waptee.catalog.profile.PersonalCatalog;

/**
 * Servlet providing a small API for the DrEdit JavaScript client to use in
 * manipulating files.  Each operation (GET, POST, PUT) issues requests to the
 * Google Drive API.
 *
 * @author vicfryzel@google.com (Vic Fryzel)
 */
@SuppressWarnings("serial")
public class FileServlet extends DrEditServlet {
  /**
   * Given a {@code file_id} URI parameter, return a JSON representation
   * of the given file.
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Drive service = getDriveService(getCredential(req, resp));
    String fileId = req.getParameter("catalog");

    if (fileId == null) {
      sendError(resp, 400, "The catalog be specified.");
      return;
    }

    File file = null;
    try {
      
      file = getPersonalCatalogFile(service);
      
    } catch (GoogleJsonResponseException e) {
      if (e.getStatusCode() == 401) {
        // The user has revoked our token or it is otherwise bad.
        // Delete the local copy so that their next page load will recover.
        deleteCredential(req, resp);
        sendGoogleJsonResponseError(resp, e);
        return;
      }
    }

    try {
      if (file != null) {
        String content = downloadFileContent(service, file);
        if (content == null) {
          content = "";
        }
        
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonalCatalog.class);
        
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        PersonalCatalog personal = (PersonalCatalog) jaxbUnmarshaller.unmarshal(new StringReader(content));
        
        Gson gson = new Gson();
        content = gson.toJson(personal);
        
        sendJson(resp, new ClientFile(file, content));
      
      } else {
        sendError(resp, 404, "File not found");
      }
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  private File getPersonalCatalogFile(Drive service) throws IOException {
    File file;
    List files = service.files().list();
    files = files.setQ("title='waptee_personal'");
    FileList fileList = files.execute();
    java.util.List<File> list = fileList.getItems();
    file = list.get(0);
    return file;
  }

  /**
   * Create a new file given a JSON representation, and return the JSON
   * representation of the created file.
   */
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    
    Drive service = getDriveService(getCredential(req, resp));
    
    StringWriter writer = new StringWriter();
    
    PersonalCatalog personal = new PersonalCatalog();
    
    personal.setFirstName(req.getParameter("firstName"));
    personal.setLastName(req.getParameter("lastName"));
    personal.setNickname(req.getParameter("nickname"));
    personal.setBirthday(req.getParameter("birthday"));
    
    try {
      
      JAXBContext jaxbContext = JAXBContext.newInstance(PersonalCatalog.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(personal, writer);
      
      File file = getPersonalCatalogFile(service);
      
      if (file == null) {
        
        file = new File();
        
        Labels labels = new Labels();
        labels.put("Appplication Name", APP_NAME);
        labels.put("Topic", "Profile");
        labels.put("Catalog", "Personal");
        
        file.setTitle("waptee_personal");
        file.setDescription("Dados pessoais");
        file.setMimeType("text/xml");
        file.setLabels(labels);
        file.setEditable(true);
        
        ClientFile clientFile = new ClientFile(file, writer.getBuffer().toString());
        file = clientFile.toFile();
        
        file = service.files().insert(file,
            ByteArrayContent.fromString(clientFile.mimeType, clientFile.content))
            .execute();
        
      } else {
        
        InputStreamContent mediaContent = new InputStreamContent("text/xml", new ByteArrayInputStream(writer.getBuffer().toString().getBytes()));

        file = service.files().update(file.getId(), file, mediaContent).execute();
        
      }
      sendJson(resp, file.getId());
      
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }

  /**
   * Update a file given a JSON representation, and return the JSON
   * representation of the created file.
   */
  @Override
  public void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    boolean newRevision = req.getParameter("newRevision").equals(Boolean.TRUE);
    Drive service = getDriveService(getCredential(req, resp));
    ClientFile clientFile = new ClientFile(req.getReader());
    File file = clientFile.toFile();
    // If there is content we update the given file
    if (clientFile.content != null) {
      file = service.files().update(clientFile.resource_id, file,
          ByteArrayContent.fromString(clientFile.mimeType, clientFile.content))
          .setNewRevision(newRevision).execute();
    } else { // If there is no content we patch the metadata only
      file = service.files()
          .patch(clientFile.resource_id, file)
          .setNewRevision(newRevision)
          .execute();
    }
    sendJson(resp, file.getId());
  }

  /**
   * Download the content of the given file.
   *
   * @param service Drive service to use for downloading.
   * @param file File metadata object whose content to download.
   * @return String representation of file content.  String is returned here
   *         because this app is setup for text/plain files.
   * @throws IOException Thrown if the request fails for whatever reason.
   */
  private String downloadFileContent(Drive service, File file)
      throws IOException {
    GenericUrl url = new GenericUrl(file.getDownloadUrl());
    HttpResponse response = service.getRequestFactory().buildGetRequest(url)
        .execute();
    try {
      return new Scanner(response.getContent()).useDelimiter("\\A").next();
    } catch (java.util.NoSuchElementException e) {
      return "";
    }
  }

}
