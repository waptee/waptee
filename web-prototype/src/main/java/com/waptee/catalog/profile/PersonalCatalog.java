/**
 * 
 */
package com.waptee.catalog.profile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author salomax
 *
 */
@XmlRootElement(name="personal")
public class PersonalCatalog {
  
  
  private String firstName;
  
  private String lastName;
  
  private String nickname;
  
  private String birthday;
  
  public String getFirstName() {
    return firstName;
  }

  @XmlElement
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @XmlElement
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getBirthday() {
    return birthday;
  }

  @XmlElement
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

}
