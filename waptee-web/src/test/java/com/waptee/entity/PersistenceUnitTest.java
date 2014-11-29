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

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public class PersistenceUnitTest {
  
  /**
   * Helper to local test.
   */
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
  
  /**
   * @see https://groups.google.com/forum/#!topic/objectify-appengine/O4FHC_i7EGk
   */
  private Closeable session;

  /**
   * TODO insert here the comments.
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    this.helper.setUp();
    this.session = ObjectifyService.begin();
  }
 
  /**
   * TODO insert here the comments.
   *
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    this.session.close();
    this.helper.tearDown();
  }

}
