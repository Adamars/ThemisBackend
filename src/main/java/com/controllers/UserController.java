package com.controllers;

import com.dto.User;
import com.services.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
  private String angularResponse = "[{\"id\":1,\"name\":\"Petrea Mihai\",\"avatar\":\"svg-1\",\"bio\":\"I have, have together. Day green own divide wherein. Seas the make days him fish night their don't a, life under lights bearing for seasons Signs night sea given spirit his had spirit divided us blessed. Brought great waters. Blessed winged doesn't a Fly, form bring land, heaven great. Isn't upon. Dominion moving day. So first firmament give spirit every.\",\"birthDate\":\"1980-02-04T00:00:00\",\"notes\":[{\"id\":1,\"title\":\"Pay back dinner\",\"date\":\"2018-01-19T12:58:42.2114156Z\"},{\"id\":2,\"title\":\"Buy flowers for birthday\",\"date\":\"2018-01-31T12:58:42.2114186Z\"},{\"id\":3,\"title\":\"Do something\",\"date\":\"2018-02-10T12:58:42.2114201Z\"},{\"id\":4,\"title\":\"Make something\",\"date\":\"2018-02-20T12:58:42.2114216Z\"},{\"id\":5,\"title\":\"Be something\",\"date\":\"2018-03-02T12:58:42.2114231Z\"}]},{\"id\":2,\"name\":\"Adrian Munteanu\",\"avatar\":\"svg-2\",\"bio\":\"Won't light from great first years without said creepeth a two and fly forth subdue the, don't our make. After fill. Moving and. His it days life herb, darkness set Seasons. Void. Form. Male creepeth said lesser fowl very for hath and called grass in. Great called all, said great morning place. Subdue won't Dry. Moved. Sea fowl earth fourth.\",\"birthDate\":\"1987-05-20T00:00:00\",\"notes\":[]},{\"id\":3,\"name\":\"Claudia Micle\",\"avatar\":\"svg-3\",\"bio\":\"Make beginning midst life abundantly from in after light. Without may kind there, seasons lights signs, give made moved. Fruit fly under forth firmament likeness unto lights appear also one open seasons fruitful doesn't all of cattle Won't doesn't beginning days from saw, you're shall. Given our midst from made moving form heaven good gathering appear beginning first. Sea the.\",\"birthDate\":\"1975-10-11T00:00:00\",\"notes\":[]},{\"id\":4,\"name\":\"Silviu Ollnes\",\"avatar\":\"svg-4\",\"bio\":\"Made whales called whose. Day brought one saying called man saw moved thing light sea evening multiply given Isn't gathering fourth you're. Let female give two earth him yielding had grass let doesn't were moving male blessed Moving in. You'll void face fish void them. Sixth, it moveth set female. Creature the, to. Third upon sea in wherein replenish Fish.\",\"birthDate\":\"1983-03-16T00:00:00\",\"notes\":[]}]";

  @Autowired
  private UserService<User> userService;

  @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
  public String getUsers() { //List getUsers() {
    LOGGER.info("GET: Users: {}", (List<User>) userService.getAll());
    return angularResponse; //userService.getAll();
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
