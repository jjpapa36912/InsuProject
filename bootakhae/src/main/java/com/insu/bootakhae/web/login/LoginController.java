package com.insu.bootakhae.web.login;


import com.insu.bootakhae.business.login.domain.Member;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.service.LoginService;
import com.insu.bootakhae.web.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody Member loginRequest,
      HttpServletRequest request) {
    MemberEntity memberEntity = loginService.login(loginRequest.getLoginId(),
        loginRequest.getPassword());

    if (memberEntity != null && memberEntity.getPassword().equals(loginRequest.getPassword())) {
      HttpSession session = request.getSession();
      session.setAttribute(SessionConst.LOGIN_MEMBER, memberEntity);
      return ResponseEntity.ok("Login successful");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  @Operation(summary = "로그아웃 요청", description = "로그아웃 요청", tags = {"LoginController"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(schema = @Schema(implementation = MemberEntity.class))),
  })
  @GetMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);//세션이 없으면 새로 만들지 않음
    if (session != null) {
      session.invalidate();
    }
    return ResponseEntity.ok("Logout successful");
  }
}
