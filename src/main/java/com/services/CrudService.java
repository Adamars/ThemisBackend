package com.services;

import java.util.List;

public interface CrudService<T> {

  void save(T entity);

  List<T> getAll();

  T getById(Integer id);

  void delete(Integer id);
}
