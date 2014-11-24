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
package com.waptee.web.auth.credentials;

import com.google.api.client.auth.oauth2.Credential;
import com.waptee.entity.user.User;

/**
 * Gerenciado das credenciais.
 *
 * @author salomao.marcos@gmail.com
 */
public interface CredentialManager {

  /**
   * Retrieves a new access token by exchanging the given code with OAuth2
   * end-points.
   * @param code Exchange code.
   * @return A credential object.
   */
  Credential retrieve(String code);

  /**
   * Retrieves a new access token by exchanging the given code with OAuth2
   * end-points and store it.
   * @param code Exchange code.
   * @return A credential id.
   */
  User retrieveAndSave(String code);

  /**
   * Generates a consent page url.
   * @return A consent page url string for user redirection.
   */
  String getAuthorizationUrl();

  /**
   * Returns credentials of the given user, returns null if there are none.
   * @param userId The id of the user.
   * @return A credential object or null.
   */
  Credential get(String userId);

}
