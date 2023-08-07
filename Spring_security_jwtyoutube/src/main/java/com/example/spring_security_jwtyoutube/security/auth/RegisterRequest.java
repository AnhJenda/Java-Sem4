package com.example.spring_security_jwtyoutube.security.auth;

import com.example.spring_security_jwtyoutube.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String username;
  private String password;
  private String email;
  private String tel;
}
