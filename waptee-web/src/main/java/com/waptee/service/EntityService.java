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
package com.waptee.service;

/**
 * TODO insert here the comments.
 *
 * @author salomao.marcos@gmail.com
 */
public interface EntityService<E> {
  
  /**
   * TODO insert here the comments.
   *
   * @param e
   */
  public void save(E e);

  /**
   * TODO insert here the comments.
   *
   * @param key
   * @return
   */
  public E retrieve(String key);
  
  /**
   * TODO insert here the comments.
   *
   * @param key
   */
  public void delete(E e);

}
