package com.online.catalog.books.common.security.jwt.payload.response;

public class JwtResponse {

  private String type = "Bearer";
  private Long id;
  private String username;
  private String token;

  public JwtResponse(Long id, String token, String username) {
    this.id = id;
    this.token = token;
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
