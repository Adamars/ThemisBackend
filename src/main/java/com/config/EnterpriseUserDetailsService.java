package com.config;

import com.dao.UserDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseUserDetailsService implements UserDetailsService {

  @Autowired
  UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    com.dto.User user = userDao.getUserByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("UserNotFound");
    }

    List<GrantedAuthority> authorities =
        buildUserAuthority(user.getRole().name());

    return buildUserForAuthentication(user, authorities);
  }

  private User buildUserForAuthentication(com.dto.User user,
      List<GrantedAuthority> authorities) {
    return new User(user.getEmail(), "password",
        true, true, true, true, authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(String userRole) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    setAuths.add(new SimpleGrantedAuthority(userRole));

    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }

}
