package com.insu.bootakhae.business.login.service;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberService memberService;

  /**
   * return null 이면 로그인 실패 //     * @param loginId //     * @param password
   *
   * @return
   */
  public MemberEntity login(String loginId, String password) {

    MemberEntity memberEntity = memberService.findMemberEntityByLoginIdEquals(
        loginId);

    log.info("memberEntity.get()>> ", memberEntity);

    if (memberEntity == null) {
      return null;
    } else if (memberEntity.getPassword().equals(password)) {
      return memberEntity;
    } else {
      return null;
    }
  }
}

