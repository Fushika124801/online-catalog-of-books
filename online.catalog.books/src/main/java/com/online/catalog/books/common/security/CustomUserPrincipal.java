package com.online.catalog.books.common.security;

import com.online.catalog.books.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserPrincipal implements UserDetails {

  private static final int ONE_USER_ONE_ROLE_CAPACITY = 1;
  private final User user;

  public CustomUserPrincipal(final User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    final List<SimpleGrantedAuthority> authorities = new ArrayList<>(ONE_USER_ONE_ROLE_CAPACITY);
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    return authorities;
  }

  public Long getId() {
    return user.getId();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
