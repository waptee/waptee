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

import com.waptee.entity.ValueObject;

/**
 * TODO insert here the comments.
 * 
 * {@link http://en.wikipedia.org/wiki/Language_code}
 *
 * @author {email}
 */
public class LabelVO implements ValueObject {
  
  /**
   * TODO insert here the comments.
   */
  private String lang;
  
  /**
   * TODO insert here the comments.
   */
  private String value;
  
  /**
   * TODO insert here the comments.
   */
  public String getLang() {
    return lang;
  }

  /**
   * TODO insert here the comments.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * TODO insert here the comments.
   */
  public String getValue() {
    return value;
  }

  /**
   * TODO insert here the comments.
   */
  public void setValue(String value) {
    this.value = value;
  }

}
