package com.insu.bootakhae.web.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.order.domain.Order;
import com.insu.bootakhae.business.order.domain.OrderEntity;
import com.insu.bootakhae.business.order.domain.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

  private final OrderService orderService;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public OrderController(
      OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/orderList")
  public ResponseEntity<List<MemberEntity>> getBoardList() {
    List<MemberEntity> memberEntities =
        orderService.findAllJoinMemberEntity();
    return ResponseEntity.status(HttpStatus.OK).body(memberEntities);

  }

  @PostMapping("/registerOrder")
  public ResponseEntity<String> registerOrder(
      @RequestBody Order order) {
    orderService.registerOrder(order);
    return ResponseEntity.ok("Succeed save order.");
  }

  @PostMapping("/orderDetail/{id}")
  public ResponseEntity<OrderEntity> getBoardItemById(
      @PathVariable("id") Optional<Long> id) {
    Optional<OrderEntity> orderEntity = orderService.findById(id.get());
    return ResponseEntity.ok(orderEntity.get());
  }

  @PostMapping("/updateOrder")
  public ResponseEntity<String> updateOrder(@RequestBody Order order) {
    if (order.getId() == null) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is Null");
    }
    OrderEntity orderEntity = objectMapper.convertValue(order,
        OrderEntity.class);
    orderService.update(orderEntity);
    return ResponseEntity.ok("Succeeded to save edited order.");
  }

  @PostMapping("/deleteOrder")
  public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
    OrderEntity orderEntity = objectMapper.convertValue(order,
        OrderEntity.class);
    orderService.delete(orderService.findById(orderEntity.getId()).get());
    return ResponseEntity.ok("Succeeded to delete order");
  }
}
