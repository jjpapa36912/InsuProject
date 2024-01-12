package com.insu.bootakhae.business.order.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  OrderRepository orderRepository;
  MemberRepository memberRepository;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public OrderService(OrderRepository orderRepository,
      MemberRepository memberRepository) {
    this.orderRepository = orderRepository;
    this.memberRepository = memberRepository;
  }

  public void save(OrderEntity orderEntity) {
    orderRepository.save(orderEntity);
  }

  public void update(OrderEntity orderEntity) {

    Optional<OrderEntity> foundedByOrderEntityIdOptional =
        orderRepository.findById(orderEntity.getId());

    if (foundedByOrderEntityIdOptional.isPresent()) {
      OrderEntity foundedByOrderEntityId = foundedByOrderEntityIdOptional.get();
      Optional.ofNullable(orderEntity.getOrderTitle()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderTitle(orderEntity.getOrderTitle()));
      Optional.ofNullable(orderEntity.getOrderContents()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderContents(orderEntity.getOrderContents()));
      Optional.ofNullable(orderEntity.getOrderAcceptor()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderAcceptor(orderEntity.getOrderAcceptor()));
      Optional.ofNullable(orderEntity.getOrderTime()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderTime(orderEntity.getOrderTime()));
      Optional.ofNullable(orderEntity.getOrderStatus()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderStatus(orderEntity.getOrderStatus()));
      Optional.ofNullable(orderEntity.getOrderPaymentStatus()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderPaymentStatus(orderEntity.getOrderPaymentStatus()));
      Optional.ofNullable(orderEntity.getOrderPrice()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderPrice(orderEntity.getOrderPrice()));
      Optional.ofNullable(orderEntity.getOrderNumber()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderNumber(orderEntity.getOrderNumber()));
      Optional.ofNullable(orderEntity.getOrderTotalPrice()).ifPresent(
          c -> foundedByOrderEntityId
              .setOrderTotalPrice(orderEntity.getOrderTotalPrice()));
    }
  }

  public List<OrderEntity> findAll() {
    return orderRepository.findAll();
  }

  public Optional<OrderEntity> findById(Long id) {
    return orderRepository.findById(id);
  }

  public List<MemberEntity> findAllJoinMemberEntity() {
    return orderRepository.findAllJoinMemberEntity();
  }

  public void delete(OrderEntity orderEntity) {
    orderRepository.delete(orderEntity);
  }

  public void registerOrder(Order order) {
    Optional<MemberEntity> memberEntity = memberRepository.findById(
        order.getId());
    if (memberEntity.isEmpty()) {
      throw new NullPointerException("Fill out the order form!");
    }
    OrderEntity orderEntity = objectMapper.convertValue(order,
        OrderEntity.class);
    orderEntity.setMemberEntity(memberEntity.get());
    orderEntity.setOrderTime(LocalDateTime.now());
    orderRepository.save(orderEntity);

  }
}
