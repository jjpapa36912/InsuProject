package com.insu.bootakhae.web.login;


import com.insu.bootakhae.business.login.domain.LoginForm;
import com.insu.bootakhae.business.login.domain.Member;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberResponse;
import com.insu.bootakhae.business.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {


  private final LoginService loginService;
  private static final String LOGIN_FORM_HTML = "login/loginForm";

  @CrossOrigin()
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Member loginRequest) {
    MemberEntity memberEntity = loginService.login(loginRequest.getLoginId(), loginRequest.getPassword());

    if (memberEntity != null) {
      if (memberEntity.getPassword().equals(loginRequest.getPassword())) {
        return ResponseEntity.ok("Login successful");
      }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  @ResponseBody
  @GetMapping("/api/hello")
  public String testAPI() {
    return "react Test";
  }

  @CrossOrigin()
  @GetMapping("/api/login")
  public Map<String, String> loginForm(@ModelAttribute("loginForm") LoginForm form) {
    Map<String, String> objectObjectHashMap = new HashMap<>();
    objectObjectHashMap.put("111", "222");
    return objectObjectHashMap;
  }

//  @Operation(summary = "로그인 요청", description = "로그인 요청", tags = {"LoginController"})
//  @ApiResponses({
//      @ApiResponse(responseCode = "200", description = "OK",
//          content = @Content(schema = @Schema(implementation = MemberResponse.class))),
//  })
//  @PostMapping("/login")
//  public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
//      HttpServletRequest request,
//      @RequestParam(defaultValue = "/login") String redirectURL) {
//
//    if (bindingResult.hasErrors()) {
//      return LOGIN_FORM_HTML;
//    }
//    log.info("========form.getLoginId()======{}", form.getLoginId());
//    log.info("========form.getPassword()======{}", form.getPassword());
//    MemberEntity loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//    if (loginMember == null) {
//      log.info("===========if (loginMember == null)===============");
//      bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
//      return LOGIN_FORM_HTML;
//    }
//    //세션이 있으면 세션 반환, 없으면 신규 세션을 생성
//    HttpSession session = request.getSession();
//    //세션에 로그인 회원 정보 보관
//    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//    log.info("redirectURL = ", redirectURL);
//
//    return "redirect:" + redirectURL;
//  }

  @Operation(summary = "로그아웃 요청", description = "로그아웃 요청", tags = {"LoginController"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(schema = @Schema(implementation = MemberResponse.class))),
  })
  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);//세션이 없으면 새로 만들지 않음
    if (session != null) {
      session.invalidate();
    }
    return "redirect:/login";
  }
}
