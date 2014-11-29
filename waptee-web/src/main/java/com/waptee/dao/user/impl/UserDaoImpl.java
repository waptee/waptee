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
package com.waptee.dao.user.impl;

import static com.waptee.dao.ofy.OfyService.ofy;

import com.waptee.dao.user.UserDAO;
import com.waptee.entity.user.User;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class UserDaoImpl implements UserDAO {

  /**
   * TODO insert here the comments.
   */
  @Override
  public void save(User user) {
    ofy().save().entity(user).now();
  }

  /**
   * TODO insert here the comments.
   */
  @Override
  public User retrieve(String id) {
    return ofy().load().type(User.class).id(id).now();
  }

  /**
   * TODO insert here the comments.
   */
  @Override
  public void delete(User user) {
    ofy().delete().entity(user).now();
  }

}
