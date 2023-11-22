package com.insu.bootakhae.domain.information.member;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  MemberEntity findMemberEntityByLoginId(String loginId);

  List<MemberEntity> findAll();
}
