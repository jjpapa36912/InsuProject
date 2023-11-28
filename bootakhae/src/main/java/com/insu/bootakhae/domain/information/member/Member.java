package com.insu.bootakhae.domain.information.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class Member {

  private Long id;

  @NotBlank(message = "필수 입력값입니다.")
  private String loginId;

  @NotBlank(message = "필수 입력값입니다.")
  private String name;

  @NotBlank(message = "필수 입력값입니다.")
  private String password;

//    @Builder
//    public Member(Long id, String loginId, String name, String password) {
//        this.id = id;
//        this.loginId = loginId;
//        this.name = name;
//        this.password = password;
//    }

//    public MemberEntity toEntity() {
//        return MemberEntity.builder()
//                .id(id)
//                .loginId(loginId)
//                .name(name)
//                .password(password)
//                .build();
//
//    }
}
