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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.waptee.closure.Parameters;
import com.waptee.web.auth.credentials.CredentialManager;
import com.waptee.web.auth.credentials.impl.GoogleCredentialManager;

/**
 * Servlet abstrato para configurar credenciais 
 * e prover métodos generalizados.
 *
 * @author salomao.marcos@gmail.com
 */
@SuppressWarnings("serial")
public abstract class WapteeServlet extends HttpServlet {
  
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
    credentialManager = new GoogleCredentialManager(new Parameters() {
      @SuppressWarnings("unchecked")
      public <T> T get(String name) {
        return (T) getServletContext().getResourceAsStream(name);
      }
    });
    
  }

}
