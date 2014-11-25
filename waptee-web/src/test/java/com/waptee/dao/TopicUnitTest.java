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
package com.waptee.dao;

import com.waptee.entity.Element;
import com.waptee.entity.ElementUnitTest;
import com.waptee.entity.domain.Topic;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class TopicUnitTest extends ElementUnitTest {
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  @Override
  protected <E extends Element> E getElement() {
    return new Topic().generateId();
  }
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  @Override
  protected <E extends Element> Class<E> getElementClass() {
    return (Class<E>) Topic.class;
  }
  

}
