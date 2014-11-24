package com.waptee.web.auth.credentials.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.appengine.api.utils.SystemProperty;
import com.waptee.closure.Parameters;
import com.waptee.entity.user.User;
import com.waptee.web.auth.credentials.CredentialManager;

/**
 * Credential manager to get, save, delete user credentials.
 *
 * @author jbd@google.com (Burcu Dogan)
 * @author salomao.marcos@google.com (Burcu Dogan)
 */
public class GoogleCredentialManager implements CredentialManager {

  /**
   * Client secrets object.
   */
  private GoogleClientSecrets clientSecrets;

  /**
   * Transport layer for OAuth2 client.
   */
  private static final HttpTransport TRANSPORT = new NetHttpTransport();

  /**
   * Default JSON factory for Google Apis Java client.
   */
  protected static final JsonFactory JSON_FACTORY = new JacksonFactory();
  
  /**
   * Path component under war/ to locate client_secrets.json file.
   */
  public static final String CLIENT_SECRETS_FILE_PATH
      = "/WEB-INF/auth/client_secrets.json";

  /**
   * Path component under war/ to locate localhost_client_secrets.json file.
   */
  public static final String LOCALHOST_CLIENT_SECRETS_FILE_PATH
      = "/WEB-INF/auth/localhost_client_secrets.json";
  
  /**
   * Scopes for which to request access from the user.
   */
  public static final List<String> SCOPES = Arrays.asList(
      // Required to access and manipulate files.
      "https://www.googleapis.com/auth/drive.file",
      // Required to identify the user in our data store.
      "https://www.googleapis.com/auth/userinfo.email",
      "https://www.googleapis.com/auth/userinfo.profile");

  /**
   * Credential store to get, save, delete user credentials.
   */
  private static AppEngineCredentialStore credentialStore =
      new AppEngineCredentialStore();

  /**
   * Credential Manager constructor.
   */
  public GoogleCredentialManager(Parameters parameters) {
    this.clientSecrets = getClientSecrets(parameters);
  }

  /**
   * Builds an empty credential object.
   * @return An empty credential object.
   */
  public Credential buildEmpty() {
    return new GoogleCredential.Builder()
        .setClientSecrets(this.clientSecrets)
        .setTransport(TRANSPORT)
        .setJsonFactory(JSON_FACTORY)
        .build();
  }

  /**
   * Returns credentials of the given user, returns null if there are none.
   * @param userId The id of the user.
   * @return A credential object or null.
   */
  public Credential get(String userId) {
    Credential credential = buildEmpty();

    /*
     * TODO Migrate DataStore
     * Use AppEngineDataStoreFactory with StoredCredential instead, 
     * optionally using migrateTo(AppEngineDataStoreFactory) 
     * or migrateTo(DataStore) to migrating 
     * an existing AppEngineCredentialStore. 
     */
    if (credentialStore.load(userId, credential)) {
      return credential;
    }
    return null;
  }

  /**
   * Saves credentials of the given user.
   * @param userId The id of the user.
   * @param credential A credential object to save.
   */
  public void save(String userId, Credential credential) {
    
    /*
     * TODO Migrate DataStore
     * Use AppEngineDataStoreFactory with StoredCredential instead, 
     * optionally using migrateTo(AppEngineDataStoreFactory) 
     * or migrateTo(DataStore) to migrating 
     * an existing AppEngineCredentialStore. 
     */
    credentialStore.store(userId, credential);
  }

  /**
   * Deletes credentials of the given user.
   * @param userId The id of the user.
   */
  public void delete(String userId) {
    
    /*
     * TODO Migrate DataStore
     * Use AppEngineDataStoreFactory with StoredCredential instead, 
     * optionally using migrateTo(AppEngineDataStoreFactory) 
     * or migrateTo(DataStore) to migrating 
     * an existing AppEngineCredentialStore. 
     */
    credentialStore.delete(userId, get(userId));
  }

  /**
   * Generates a consent page url.
   * @return A consent page url string for user redirection.
   */
  public String getAuthorizationUrl() {
    GoogleAuthorizationCodeRequestUrl urlBuilder =
        new GoogleAuthorizationCodeRequestUrl(
        clientSecrets.getWeb().getClientId(),
        clientSecrets.getWeb().getRedirectUris().get(0),
        SCOPES).setApprovalPrompt("force");
	  return urlBuilder.build();
  }

  /**
   * Retrieves a new access token by exchanging the given code with OAuth2
   * end-points.
   * @param code Exchange code.
   * @return A credential object.
   */
  public Credential retrieve(String code) {
    try {
      GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(
          TRANSPORT,
          JSON_FACTORY,
          clientSecrets.getWeb().getClientId(),
          clientSecrets.getWeb().getClientSecret(),
          code,
          clientSecrets.getWeb().getRedirectUris().get(0)).execute();
      return buildEmpty().setAccessToken(response.getAccessToken());
    } catch (IOException e) {
      new RuntimeException("An unknown problem occured while retrieving token");
    }
    return null;
  }
  
  /**
   * Reads client_secrets.json and creates a GoogleClientSecrets object.
   * @return A GoogleClientsSecrets object.
   */
  private GoogleClientSecrets getClientSecrets(Parameters parameters) {
    
    // TODO: do not read on each request
    InputStream stream = null;
        
    if (SystemProperty.environment.value() == 
        SystemProperty.Environment.Value.Development) {
      stream = parameters.get(LOCALHOST_CLIENT_SECRETS_FILE_PATH);
    } else {
      stream = parameters.get(CLIENT_SECRETS_FILE_PATH);
    }
    
    try {
      return GoogleClientSecrets.load(
          JSON_FACTORY, new InputStreamReader(stream));
    } catch (IOException e) {
      throw new RuntimeException("No client_secrets.json found");
    }
  }

  @Override
  public User retrieveAndSave(String code) {

    try {

      // retrieve new credentials with code
      Credential credential = this.retrieve(code);

      // request userinfo
      Oauth2 service = getOauth2Service(credential);
      
      Userinfoplus about = service.userinfo().get().execute();
      
      String id = about.getId();
      
      this.save(id, credential);

      return new User().setId(about.getEmail());
      
    } catch (IOException e) {
      throw new RuntimeException("Can't handle the OAuth2 callback, "
          + "make sure that code is valid.");
    }
    
  }
  
  /**
   * Build and return an Oauth2 service object based on given request parameters.
   * @param credential User credentials.
   * @return Drive service object that is ready to make requests, or null if
   *         there was a problem.
   */
  protected Oauth2 getOauth2Service(Credential credential) {
    return new Oauth2.Builder(TRANSPORT, JSON_FACTORY, credential).build();
  }
  
}

