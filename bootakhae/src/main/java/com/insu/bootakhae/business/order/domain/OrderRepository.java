package com.insu.bootakhae.business.order.domain;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query("select m from member m join fetch m.orderEntities o")
  List<MemberEntity> findAllJoinMemberEntity();
}
