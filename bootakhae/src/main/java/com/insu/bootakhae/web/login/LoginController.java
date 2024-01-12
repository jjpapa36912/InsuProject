package com.insu.bootakhae.web.login;

import com.insu.bootakhae.business.login.domain.Member;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberService;
import com.insu.bootakhae.business.login.service.LoginService;
import com.insu.bootakhae.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {


  private final LoginService loginService;
  private final MemberService memberService;

  @PostMapping("/login")
  public ResponseEntity<MemberEntity> login(@RequestBody Member loginRequest,
      HttpServletRequest request) {
    MemberEntity memberEntity = loginService.login(loginRequest.getUserId(),
        loginRequest.getPassword());

    if (memberEntity != null && memberEntity.getPassword()
        .equals(loginRequest.getPassword())) {
      HttpSession session = request.getSession();
      session.setAttribute(SessionConst.LOGIN_MEMBER, memberEntity);
      return ResponseEntity.ok()
          .body(memberEntity);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(memberEntity);
  }

  @GetMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);//세션이 없으면 새로 만들지 않음
    if (session != null) {
      session.invalidate();
    }
    return ResponseEntity.ok("Logout successful");
  }

  @PostMapping("/memberRegister")
  public ResponseEntity<String> memberRegister(
      @RequestBody Member loginRequest) {
    boolean isExistMember = memberService.isExistMember(
        loginRequest.getUserId());

    if (!isExistMember) {
      memberService.save(new MemberEntity(loginRequest.getUserId(),
          loginRequest.getName(), loginRequest.getPassword()));
      return ResponseEntity.ok()
          .body("Succeeded to register member");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Failed to register member");
  }
}
