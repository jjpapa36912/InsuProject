package com.insu.bootakhae.business.login.domain;

import static org.assertj.core.api.Assertions.*;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
//@Rollback(value = false)
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Test
  void save() {
    MemberEntity memberEntity = new MemberEntity("djk", "djk", "1234");
    MemberEntity memberEntity1 = new MemberEntity("djk1", "djk1", "1234");
    MemberEntity memberEntity2 = new MemberEntity("djk2", "djk2", "1234");

    memberService.save(memberEntity);
    memberService.save(memberEntity1);
    memberService.save(memberEntity2);

    List<MemberEntity> all = memberService.findAll();

    assertThat(all.get(0).getUserId()).isEqualTo("djk");
  }

}