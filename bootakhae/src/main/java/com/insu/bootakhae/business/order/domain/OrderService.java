package com.insu.bootakhae.business.order.domain;

import com.insu.bootakhae.business.login.domain.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public OrderEntity save(OrderEntity orderEntity) {
    return orderRepository.save(orderEntity);
  }

  public List<OrderEntity> findAll() {
    return orderRepository.findAll();
  }

  public Optional<OrderEntity> findById(Long id) {
    return orderRepository.findById(id);
  }

  public Optional<OrderEntity> findByUserId(String userId) {
    return orderRepository.findByUserId(userId);
  }
}
