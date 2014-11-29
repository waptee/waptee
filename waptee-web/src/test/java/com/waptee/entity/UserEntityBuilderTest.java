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
package com.waptee.entity;

import com.waptee.entity.domain.LabelVO;
import com.waptee.entity.domain.TopicElement;
import com.waptee.entity.user.User;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class UserEntityBuilderTest {
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  public User getEntity() {

    LabelVO label = new LabelVO();
    
    label.setLang("en");
    label.setValue("Dummy");
    
    TopicElement topic = new TopicElement().addLabel(label).generateId();
    
    return new User().addTopic(topic).generateId();
    
  }
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  public Class<User> getEntityClass() {
    return User.class;
  }

}
