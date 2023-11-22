package com.insu.bootakhae.domain.login;

import com.insu.bootakhae.domain.information.member.MemberEntity;
import com.insu.bootakhae.domain.information.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

//    @Autowired
//    private final ModelMapper modelMapper;


    /**
     * return null 이면 로그인 실패
     //     * @param loginId
     //     * @param password
     * @return
     */
    public MemberEntity login(String loginId, String password) {

        MemberEntity memberEntity = memberService.findMemberEntityByLoginId(loginId);


        log.info("memberEntity.get()>> ", memberEntity);


//        Member member = modelMapper.map(memberEntity, Member.class);
        if (memberEntity == null) {
            return null;
        }else if (memberEntity.getPassword().equals(password)) {
            return memberEntity;
        } else {
            return null;
        }
    }
}

