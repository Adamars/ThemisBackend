package com.services;

public interface UserService<User> extends CrudService<User> {

  User getByEmail(String email);
}
