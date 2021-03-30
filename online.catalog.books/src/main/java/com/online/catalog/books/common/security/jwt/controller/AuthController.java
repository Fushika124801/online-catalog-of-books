package com.online.catalog.books.common.security.jwt.controller;

import com.online.catalog.books.common.security.CustomUserPrincipal;
import com.online.catalog.books.common.security.jwt.JwtUtils;
import com.online.catalog.books.common.security.jwt.payload.request.LoginRequest;
import com.online.catalog.books.common.security.jwt.payload.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(
      @Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    CustomUserPrincipal userDetails = (CustomUserPrincipal) authentication.getPrincipal();
    JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt, userDetails.getUsername());

    return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
  }
}
