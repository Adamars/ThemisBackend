package com.controllers;

import com.dto.User;
import com.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService<User> userService;

  @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
  public List getUsers() {
    return userService.getAll();
  }

  @RequestMapping(value = "/{email:(?:^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$?)}", method = RequestMethod.GET)
  public User getUserByEmail(@PathVariable String email) {
    return userService.getByEmail(email);
  }

  @RequestMapping(value = "/{id:(?:^[1-9])\\d*$}", method = RequestMethod.GET)
  public User getUserById(@PathVariable Integer id) {
    return userService.getById(id);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public User createUser(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable Integer id) {
    userService.delete(id);
  }
}
