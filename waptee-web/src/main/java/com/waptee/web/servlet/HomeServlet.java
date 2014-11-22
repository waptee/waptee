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
 * Home page servlet.
 *
 * @author salomao.marcos@gmail.com
 */
@SuppressWarnings("serial")
public class HomeServlet extends OAuthServlet {
  
  /**
   * Initialize servlet life cycle method.
   */
  @Override
  public void init() throws ServletException {
    
    // super implementation.
    super.init();
  }
  
  /**
   * Ensure that the user is authorized, and setup the required values for
   * index.jsp.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    // redirect to home page.
    request.getRequestDispatcher(
            "/home.html").forward(request, response);
    
  }

}
