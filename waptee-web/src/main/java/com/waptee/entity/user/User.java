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

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.waptee.entity.Element;
import com.waptee.entity.domain.TopicElement;

/**
 * TODO insert here the comments.
 *
 * @author salomao.marcos@gmail.com
 */
@Entity
public class User extends Element {
  
  /**
   * TODO insert here the comments.
   */
  private List<TopicElement> topics = new ArrayList<TopicElement>();
  
  /**
   * TODO insert here the comments.
   */
  public User addTopic(final TopicElement topic) {
    this.topics.add(topic);
    return (User) this;
  }
  
  /**
   * TODO insert here the comments.
   */
  public TopicElement[] getTopics() {
    return this.topics.toArray(new TopicElement[]{});
  }
  
  /**
   * TODO insert here the comments.
   */
  public User removeTopic(final TopicElement topic) {
    this.topics.remove(topic);
    return (User) this;
  }  

}
