/*
 * Waptee 2014 - 2014 This file is part of Waptee project. All Rights Reserved.
 * 
 * All information contained herein is, and remains the property of {company name} and its
 * suppliers, if any. The intellectual and technical concepts contained herein are proprietary to
 * {company name} and its suppliers. Dissemination of this information or reproduction of this
 * material is strictly forbidden unless prior written permission is obtained from Adobe Systems
 * Incorporated.
 * 
 * This file is subject to the terms and conditions defined in file LICENSE, which is part of this
 * source code package.
 */
package com.waptee.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.Credential;
import com.waptee.closure.Parameters;
import com.waptee.web.auth.credentials.CredentialManager;
import com.waptee.web.auth.credentials.impl.GoogleCredentialManager;

/**
 * Servlet abstrato para configurar credenciais e prover métodos generalizados.
 *
 * @author salomao.marcos@gmail.com
 */
@SuppressWarnings("serial")
public abstract class OAuthServlet extends HttpServlet {
  
  /**
   * Key to get/set userId from and to the session.
   */
  public static final String KEY_SESSION_USERID = "user_id";

  /**
   * Gerenciado para CRUD de credenciais.
   */
  private CredentialManager credentialManager = null;

  /**
   * Método que inicializa o ciclo de vida do servlet.
   */
  @Override
  public void init() throws ServletException {

    // Inicializando super método.
    super.init();

    // Inicializando gerenciador de credenciais.
    this.credentialManager = new GoogleCredentialManager(new Parameters() {
      @SuppressWarnings("unchecked")
      public <T> T get(String name) {
        return (T) getServletContext().getResourceAsStream(name);
      }
    });

  }


  /**
   * If OAuth2 redirect callback is invoked and there is a code query param, retrieve user
   * credentials and profile. Then, redirect to the home page.
   * 
   * Se callback redirect do OAuth2 for invocado e houver um parametro
   * chamdo code na query, recupera-se as credenciais do usuário e seu perfil. 
   * Depois, redireciona para a página de home.
   * 
   * @param req Request object.
   * @param resp Response object.
   * @throws IOException
   */
  protected void handleCallbackIfRequired(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    String code = req.getParameter("code");

    if (code != null) {

      String id = this.credentialManager.retrieveAndSave(code);
      
      req.getSession().setAttribute(KEY_SESSION_USERID, id);
      
      resp.sendRedirect("/home");
    
    }
  
  }
  
  /**
   * Redirects to OAuth2 consent page if user is not logged in.
   * @param req   Request object.
   * @param resp  Response object.
   */
  protected void loginIfRequired(HttpServletRequest req,
      HttpServletResponse resp) {
    
    Credential credential = getCredential(req, resp);
    
    if (credential == null) {
      // redirect to authorization url
      try {
        
        resp.sendRedirect(this.credentialManager.getAuthorizationUrl());
        
      } catch (IOException e) {
        throw new RuntimeException("Can't redirect to auth page");
      }
    }
    
  }
  
  /**
   * Returns the credentials of the user in the session. If user is not in the
   * session, returns null.
   * @param req   Request object.
   * @param resp  Response object.
   * @return      Credential object of the user in session or null.
   */
  protected Credential getCredential(HttpServletRequest req,
      HttpServletResponse resp) {
    
    String userId = (String) req.getSession().getAttribute(KEY_SESSION_USERID);
    
    if (userId != null) {
      
      return credentialManager.get(userId);
      
    }
    
    return null;
  }
  
}
