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
package com.waptee.entity.user;

import com.waptee.entity.UserEntityBuilderTest;
import com.waptee.entity.ofy.OfyUnitTest;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class UserUnitTest extends OfyUnitTest<User> {
  
  /**
   * TODO insert here the comments.
   */
  private UserEntityBuilderTest userBuilder = new UserEntityBuilderTest();
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  @Override
  public User getEntity() {
    return userBuilder.getEntity();
    
  }
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  @Override
  public Class<User> getEntityClass() {
    return userBuilder.getEntityClass();
  }

}
