package com.services.impl;

import com.dao.UserDao;
import com.dao.UserDaoCustom;
import com.dto.User;
import com.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService<User> {

  @Autowired
  UserDao userDao;

  @Autowired
  UserDaoCustom userDaoCustom;

  @Override
  public void save(User entity) {
    userDao.save(entity);
  }

  @Override
  public List<User> getAll() {
    return (List<User>) userDao.findAll();
  }

  @Override
  public User getById(Integer id) {
    return userDao.findOne(id);
  }

  @Override
  public void delete(Integer id) {
    userDao.delete(id);
  }

  @Override
  public User getByEmail(String email) {
    return userDao.getUserByEmail(email);
  }
}
