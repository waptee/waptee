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

import java.util.ArrayList;
import java.util.List;

import com.waptee.entity.domain.LabelVO;

/**
 * TODO insert here the comments. 
 *
 * @author salomao.marcos@gmail.com
 */
public abstract class Element extends Entity {
  
  /**
   * TODO insert here the comments.
   */
  private String name;
  
  /**
   * TODO insert here the comments.
   */
  private List<LabelVO> labels = new ArrayList<LabelVO>();

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
  
  /**
   * TODO insert here the comments.
   */
  @SuppressWarnings("unchecked")
  public <E extends Element> E addLabel(final LabelVO label) {
    this.labels.add(label);
    return (E) this;
  }
  
  /**
   * TODO insert here the comments.
   */
  public LabelVO[] getLabels() {
    return this.labels.toArray(new LabelVO[]{});
  }
  
  /**
   * TODO insert here the comments.
   */
  @SuppressWarnings("unchecked")
  public <E extends Element> E removeTopic(final LabelVO label) {
    this.labels.remove(label);
    return (E) this;
  }  

}
