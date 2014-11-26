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
public interface CRUDService<E, K> {
  
  /**
   * TODO insert here the comments.
   *
   * @param e
   * @return
   */
  public E save(E e);

  /**
   * TODO insert here the comments.
   *
   * @param key
   * @return
   */
  public E retrieve(K key);
  
  /**
   * TODO insert here the comments.
   *
   * @param key
   */
  public void delete(K key);

}