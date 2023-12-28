package com.insu.bootakhae.business.order.domain;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberRepository;
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

    OrderEntity foundedOrderEntityById =
        orderRepository.findById(orderEntity.getId()).get();

    Optional.ofNullable(orderEntity.getOrderTitle()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderTitle(orderEntity.getOrderTitle()));
    Optional.ofNullable(orderEntity.getOrderContents()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderContents(orderEntity.getOrderContents()));
    Optional.ofNullable(orderEntity.getOrderAcceptor()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderAcceptor(orderEntity.getOrderAcceptor()));
    Optional.ofNullable(orderEntity.getOrderTime()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderTime(orderEntity.getOrderTime()));
    Optional.ofNullable(orderEntity.getOrderStatus()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderStatus(orderEntity.getOrderStatus()));
    Optional.ofNullable(orderEntity.getOrderPaymentStatus()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderPaymentStatus(orderEntity.getOrderPaymentStatus()));
    Optional.ofNullable(orderEntity.getOrderPrice()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderPrice(orderEntity.getOrderPrice()));
    Optional.ofNullable(orderEntity.getOrderNumber()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderNumber(orderEntity.getOrderNumber()));
    Optional.ofNullable(orderEntity.getOrderTotalPrice()).ifPresent(
        c -> foundedOrderEntityById
            .setOrderTotalPrice(orderEntity.getOrderTotalPrice()));
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

  private Map<Long, MemberEntity> extractedMemberEntities(
      List<MemberEntity> allOrderEntityJoinMemberEntity) {
    return allOrderEntityJoinMemberEntity.stream()
        .collect(Collectors.toMap(MemberEntity::getId, Function.identity()));

  }
}
