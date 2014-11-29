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
package com.waptee.dao.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.waptee.entity.user.User;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class OfyService {

  /**
   * TODO insert here the comments.
   */
  static {
    factory().register(User.class);
  }

  /**
   * TODO insert here the comments.
   */
  public static Objectify ofy() {
    return ObjectifyService.ofy();
  }

  /**
   * TODO insert here the comments.
   */
  public static ObjectifyFactory factory() {
    return ObjectifyService.factory();
  }

}
