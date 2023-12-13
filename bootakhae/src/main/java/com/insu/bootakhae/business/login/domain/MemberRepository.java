package com.insu.bootakhae.business.login.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  MemberEntity findMemberEntityByUserIdEquals(String loginId);

  List<MemberEntity> findAll();

}
