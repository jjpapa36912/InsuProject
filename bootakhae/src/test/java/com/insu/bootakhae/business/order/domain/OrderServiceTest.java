package com.insu.bootakhae.business.order.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

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
    OrderEntity orderEntity1 = new OrderEntity("djk", "jjj",
        LocalDateTime.now(), "주문완료", "결제대기", 100, 3, 300);
    OrderEntity orderEntity2 = new OrderEntity("djk1", "jjj1",
        LocalDateTime.now(), "주문대기", "결제대기", 200, 4, 800);
    OrderEntity orderEntity3 = new OrderEntity("djk2", "jjj2",
        LocalDateTime.now(), "주문완료", "결제완료", 200, 5, 1000);

    orderService.save(orderEntity1);
    orderService.save(orderEntity2);
    orderService.save(orderEntity3);

    assertThat(
        orderService.findByUserId("djk").get().getOrderAcceptor()).isEqualTo(
        "jjj");
    assertThat(
        orderService.findByUserId("djk1").get().getOrderAcceptor()).isEqualTo(
        "jjj1");
    assertThat(
        orderService.findByUserId("djk2").get().getOrderAcceptor()).isEqualTo(
        "jjj2");
  }

  @Test
  void findByUserID() {
    Optional<OrderEntity> result = orderService.findByUserId("djk");

    assertThat(result.get().getUserId()).isEqualTo("djk");
  }

}