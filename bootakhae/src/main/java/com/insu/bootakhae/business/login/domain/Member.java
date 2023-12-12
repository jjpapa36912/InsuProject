package com.insu.bootakhae.business.login.domain;

import lombok.Data;

@Data
public class Member {

  private Long id;

  private String loginId;

  private String name;

  private String password;
}
