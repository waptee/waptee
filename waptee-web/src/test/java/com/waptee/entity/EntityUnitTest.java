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


/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public interface EntityUnitTest<E extends Entity> {
  
  /**
   * TODO insert here the comments.
   *
   * @return
   */
  E getEntity();

  /**
   * TODO insert here the comments.
   *
   * @return
   */
  Class<E> getEntityClass();
  
  /**
   * TODO insert here the comments.
   */
  void testEntity();

}