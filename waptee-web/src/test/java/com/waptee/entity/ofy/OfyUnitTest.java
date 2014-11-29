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
package com.waptee.entity.ofy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.logging.Logger;

import org.junit.Test;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.waptee.entity.Entity;
import com.waptee.entity.EntityUnitTest;
import com.waptee.entity.PersistenceUnitTest;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public abstract class OfyUnitTest<E extends Entity> extends PersistenceUnitTest implements EntityUnitTest<E> {
  
  /**
   * Logger.
   */
  private static Logger logger = 
      Logger.getLogger(OfyUnitTest.class.getName());
  
  /**
   * TODO insert here the comments.
   */
  @Test
  public void testEntity() {
    
    // get Objectify
    final Objectify ofy = ObjectifyService.ofy();
    
    // register entity class
    ofy.factory().register(getEntityClass());
    
    // Simple create an user
    Entity entity = getEntity();

    logger.info(String.format("Entity created:\n%s", entity));
    
    // save sync entity
    ofy.save().entities(entity).now();

    // get id entity saved
    String id = entity.getId();

    // verify id
    assertNotEquals(id, null);
    
    // Get it back
    Entity loadedEntity = 
        ofy.load().type(getEntityClass()).id(id).now();
    
    logger.info(String.format("Entity loaded:\n%s", entity));
 
    // Load verification
    assertEquals(entity, loadedEntity);
    
    // Change some data and write it
    ofy.save().entities(entity).now();
 
    // Delete it
    ofy.delete().entity(entity).now();
    
  }

}
