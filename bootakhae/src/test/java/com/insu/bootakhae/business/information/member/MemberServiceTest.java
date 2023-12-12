package com.insu.bootakhae.business.information.member;

import static org.assertj.core.api.Assertions.*;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
//@Transactional
//@Rollback(value = false)
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Test
  void save() {
    MemberEntity memberEntity = new MemberEntity("djk", "djk", "1234");

    memberService.save(memberEntity);

    List<MemberEntity> all = memberService.findAll();

    assertThat(all.get(0).getLoginId()).isEqualTo("djk");
  }

}