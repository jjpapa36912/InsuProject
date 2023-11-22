package com.insu.bootakhae.web.login;


import com.insu.bootakhae.domain.information.member.MemberEntity;
import com.insu.bootakhae.domain.login.LoginService;
import com.insu.bootakhae.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request,
                            @RequestParam(defaultValue = "/login") String redirectURL) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        log.info("========form.getLoginId()======{}", form.getLoginId());
        log.info("========form.getPassword()======{}", form.getPassword());
        MemberEntity loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            log.info("===========if (loginMember == null)===============");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }
        //세션이 있으면 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("redirectURL = ", redirectURL);

        return "redirect:" + redirectURL;
    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);//세션이 없으면 새로 만들지 않음
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
}
