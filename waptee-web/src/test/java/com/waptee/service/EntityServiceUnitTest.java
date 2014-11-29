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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.logging.Logger;

import org.junit.Test;

import com.waptee.entity.Entity;
import com.waptee.entity.EntityUnitTest;
import com.waptee.entity.PersistenceUnitTest;
import com.waptee.service.EntityService;

/**
 * TODO insert here the comments.
 *
 * @author {email}
 */
public abstract class EntityServiceUnitTest<E extends Entity, S extends EntityService<E>> 
        extends PersistenceUnitTest implements EntityUnitTest<E> {

  /**
   * Logger.
   */
  private static Logger logger = 
      Logger.getLogger(EntityServiceUnitTest.class.getName());
  
  /**
   * TODO insert here the comments.
   */
  public abstract S getEntityService();
  
  /**
   * TODO insert here the comments.
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testEntity() {
    
    // Simple create an user
    Entity entity = getEntity();
    
    logger.info(String.format("Entity created:\n%s", entity));

    EntityService<E> entityService = getEntityService();

    // save sync entity
    entityService.save((E) entity);

    // get id entity saved
    String id = entity.getId();

    // verify id
    assertNotEquals(id, null);
    
    // Get it back
    Entity loadedEntity = entityService.retrieve(id);
    
    logger.info(String.format("Entity loaded:\n%s", entity));
 
    // Load verification
    assertEquals(entity, loadedEntity);
    
    // Change some data and write it
    entityService.save((E) entity);
 
    // Delete it
    entityService.delete((E) entity);
    
  }
  
}
