package com.insu.bootakhae.business.order.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Test
  void save() {
    OrderEntity orderEntity1 = new OrderEntity(1l, "djk", "title1", "contents1",
        "jjj", LocalDateTime.now(), "주문완료", "결제대기", 100, 3, 300,
        new MemberEntity(1L));
    OrderEntity orderEntity2 = new OrderEntity(2l, "djk1", "title2",
        "contents2",
        "jjj1", LocalDateTime.now(), "주문대기", "결제대기", 200, 4, 800,
        new MemberEntity(2L));
    OrderEntity orderEntity3 = new OrderEntity(3l, "djk2", "title3",
        "contents3",
        "jjj2", LocalDateTime.now(), "주문완료", "결제완료", 200, 5, 1000,
        new MemberEntity(3L));

    orderService.save(orderEntity1);
    orderService.save(orderEntity2);
    orderService.save(orderEntity3);

  }

}