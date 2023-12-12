package com.insu.bootakhae.business.login.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {

  @NotNull(message = "비어 있을 수 없지롱")
  private String loginId;

  @NotEmpty
  private String password;
}
