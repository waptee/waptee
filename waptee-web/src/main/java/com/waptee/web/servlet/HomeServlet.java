/* Waptee 2014 - 2014
 * This file is part of Waptee project. All Rights Reserved.
 * 
 * All information contained herein is, and remains
 * the property of {company name} and its suppliers, if any.
 * The intellectual and technical concepts contained herein 
 * are proprietary to {company name} and its suppliers.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 *
 * This file is subject to the terms and conditions defined in
 * file LICENSE, which is part of this source code package.
 */
package com.waptee.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet da tela principal.
 *
 * @author salomao.marcos@gmail.com
 */
@SuppressWarnings("serial")
public class HomeServlet extends OAuthServlet {
  
  /**
   * Ensure that the user is authorized, and setup the required values for
   * index.jsp.
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    
    // handle OAuth2 callback
    handleCallbackIfRequired(req, resp);
   
    // Making sure that we have user credentials
    loginIfRequired(req, resp);
      
    // redirect
    req.getRequestDispatcher("/public/home.html").forward(req, resp);
  
  }

}
