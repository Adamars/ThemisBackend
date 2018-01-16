package com.dao.impl;

import com.dao.UserDaoCustom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDaoCustom {

  @PersistenceContext
  private EntityManager entityManager;

//  @Override
//  public Object wqewqe(String email) {
//    //entityManager.getReference(UserDaoCustom.class, email);
//    return entityManager.createNativeQuery("select * from users where id = 2").getSingleResult();
//  }
}
