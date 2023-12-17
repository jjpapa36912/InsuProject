package com.insu.bootakhae.business.order.domain;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void save(OrderEntity orderEntity) {
    Optional<OrderEntity> foundedOrderEntityById =
        orderRepository.findById(orderEntity.getId());
    if (foundedOrderEntityById == null) {
      orderRepository.save(orderEntity);
    } else {
      update(orderEntity, foundedOrderEntityById);
    }
  }

  public void update(OrderEntity orderEntity,
      Optional<OrderEntity> foundedOrderEntityById) {
    Optional.ofNullable(orderEntity.getOrderTitle()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderTitle(orderEntity.getOrderTitle()));
    Optional.ofNullable(orderEntity.getOrderContents()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderContents(orderEntity.getOrderContents()));
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
