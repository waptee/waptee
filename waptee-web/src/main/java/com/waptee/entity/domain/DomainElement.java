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
package com.waptee.entity.domain;

import java.util.UUID;

import com.waptee.entity.Element;

/**
 * TODO insert here the comments.
 *
 * @author salomao.marcos@gmail.com
 */
public abstract class DomainElement extends Element {
  
  /**
   * TODO insert here the comments.
   */
  private String name;

  /**
   * TODO insert here the comments.
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * TODO insert here the comments.
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

}