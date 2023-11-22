package com.insu.bootakhae.domain.information.member;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Test
  public void save() {
    MemberEntity memberEntity = new MemberEntity("testId1", "testName1", "testPassword1");

    memberService.save(memberEntity);

    List<MemberEntity> all = memberService.findAll();

    assertThat(all.get(0).getLoginId()).isEqualTo("testId1");
  }

}