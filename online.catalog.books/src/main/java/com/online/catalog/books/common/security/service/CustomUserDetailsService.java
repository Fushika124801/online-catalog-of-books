package com.online.catalog.books.common.security.service;

import com.online.catalog.books.common.security.CustomUserPrincipal;
import com.online.catalog.books.user.model.User;
import com.online.catalog.books.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository repository) {
    this.userRepository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        "User with username:'" + username + "' not found"));
    return new CustomUserPrincipal(user);
  }
}
