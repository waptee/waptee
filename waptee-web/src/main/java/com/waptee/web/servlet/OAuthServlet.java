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
import com.waptee.entity.user.User;
import com.waptee.service.user.UserService;
import com.waptee.service.user.impl.UserServiceImpl;
import com.waptee.web.auth.credentials.CredentialManager;
import com.waptee.web.auth.credentials.impl.GoogleCredentialManager;

import static com.waptee.web.constants.HttpConstants.KEY_SESSION_USERID;

/**
 * Abstract servlet to OAuth methods.
 *
 * @author salomao.marcos@gmail.com
 */
@SuppressWarnings("serial")
public abstract class OAuthServlet extends HttpServlet {
  
  /**
   * User service.
   */
  private UserService service;

  /**
   * Gerenciado para CRUD de credenciais.
   */
  private CredentialManager credentialManager = null;

  /**
   * Método que inicializa o ciclo de vida do servlet.
   */
  @Override
  public void init() throws ServletException {

    // init super method.
    super.init();

    // instantiate UserService default implementation
    // TODO IoD
    this.service = new UserServiceImpl();

    // instantiate CredentialManager google implementation
    // TODO IoD
    this.credentialManager = new GoogleCredentialManager(new Parameters() {
      @SuppressWarnings("unchecked")
      public <T> T get(String name) {
        return (T) getServletContext().getResourceAsStream(name);
      }
    });

  }
  
  /**
  * Overrided method to Oauth concerns, handling Callback and login.<br/>
  * <br/>
  * Receives standard HTTP requests from the public
  * <code>service</code> method and dispatches
  * them to the <code>do</code><i>XXX</i> methods defined in 
  * this class.
  */
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    // handle OAuth2 callback
    handleCallbackIfRequired(request, response);
   
    // Making sure that we have user credentials
    loginIfRequired(request, response);
    
    // execute the request
    super.service(request, response);
    
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
  protected void handleCallbackIfRequired(
      HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String code = request.getParameter("code");

    if (code != null) {

      User user = this.credentialManager.retrieveAndSave(code);

      // save the user in order it does not exists
      this.service.save(user);
      
      request.getSession().setAttribute(KEY_SESSION_USERID, user.getId());
      
      response.sendRedirect("/home");
      
    }
  
  }
  
  /**
   * Redirects to OAuth2 consent page if user is not logged in.
   * @param request   Request object.
   * @param response  Response object.
   */
  protected void loginIfRequired(
      HttpServletRequest request,
      HttpServletResponse response) {
    
    Credential credential = 
        getCredential(request, response);
    
    if (credential == null) {
      // redirect to authorization url
      try {
        
        response.sendRedirect(
            this.credentialManager.getAuthorizationUrl());
        
      } catch (IOException e) {
        throw new RuntimeException("Can't redirect to auth page");
      }
    }
    
  }
  
  /**
   * Returns the credentials of the user in the session. If user is not in the
   * session, returns null.
   * 
   * @param request   Request object.
   * @param request  Response object.
   * @return      Credential object of the user in session or null.
   */
  protected Credential getCredential(
      HttpServletRequest request,
      HttpServletResponse response) {
    
    // get user id from session
    String userId = 
        (String) request.getSession().getAttribute(KEY_SESSION_USERID);
    
    // verify id there is a valid id
    if (userId != null) {
      
      // get credential by id
      return this.credentialManager.get(userId);
      
    }
    
    // otherwise, return null
    return null;
  }
  
}
