package com.insu.bootakhae.domain.information.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {

  private Long id;

  @NotBlank(message = "필수 입력값입니다.")
  private String loginId;

  @NotBlank(message = "필수 입력값입니다.")
  private String name;

  @NotBlank(message = "필수 입력값입니다.")
  private String password;
}
