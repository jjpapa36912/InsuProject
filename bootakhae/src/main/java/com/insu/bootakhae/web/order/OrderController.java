package com.insu.bootakhae.web.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insu.bootakhae.business.order.domain.Order;
import com.insu.bootakhae.business.order.domain.OrderEntity;
import com.insu.bootakhae.business.order.domain.OrderService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(
      OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/boardList")
  public ResponseEntity<List<OrderEntity>> getBoardList() {
    List<OrderEntity> orderEntities = orderService.findAll();
    return ResponseEntity.ok(orderEntities);
  }

  @PostMapping("/boardItem/{id}")
  public ResponseEntity<OrderEntity> getBoardItemById(
      @PathVariable("id") Optional<Long> id) {
    Optional<OrderEntity> orderEntity = orderService.findById(id.get());
    return ResponseEntity.ok(orderEntity.get());
  }

  @PostMapping("/saveEditedOrder")
  public ResponseEntity<String> saveEditedOrder(@RequestBody Order order) {
    ObjectMapper objectMapper = new ObjectMapper();
    OrderEntity orderEntity = objectMapper.convertValue(order,
        OrderEntity.class);
    orderService.save(orderEntity);
    return ResponseEntity.ok("Succeeded to save edited order.");
  }


}
