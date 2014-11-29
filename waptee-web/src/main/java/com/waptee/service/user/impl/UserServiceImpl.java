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
package com.waptee.service.user.impl;

import com.waptee.dao.user.UserDAO;
import com.waptee.dao.user.impl.UserDaoImpl;
import com.waptee.entity.user.User;
import com.waptee.service.user.UserService;

/**
 * TODO insert here the comments.
 *
 * @author salomao.marcos@gmail.com
 */
public class UserServiceImpl implements UserService {
  
  /**
   * TODO insert here the comments.
   */
  private UserDAO userDao;
  
  /**
   * TODO insert here the comments.
   */
  public UserServiceImpl() {
    this.userDao = new UserDaoImpl();
  }
  
  /**
   * TODO insert here the comments.
   */
  public void save(User user) {
    this.userDao.save(user);
  }

  /**
   * TODO insert here the comments.
   */
  public User retrieve(String id) {
    return this.userDao.retrieve(id);
  }
  
  /**
   * TODO insert here the comments.
   */
  public void delete(User user) {
    this.userDao.delete(user);
  }

}
