package com.insu.bootakhae.business.order.domain;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import com.insu.bootakhae.business.login.domain.MemberRepository;
import java.util.ArrayList;
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
    if (orderEntity.getId() == null) {
      orderEntity.setMemberEntity(
          memberRepository.findMemberEntityByUserIdEquals(
              orderEntity.getOrderUser()));
      orderRepository.save(orderEntity);
    } else {
      Optional<OrderEntity> foundedOrderEntityById =
          orderRepository.findById(orderEntity.getId());
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
    Optional.ofNullable(orderEntity.getOrderAcceptor()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderAcceptor(orderEntity.getOrderAcceptor()));
    Optional.ofNullable(orderEntity.getOrderTime()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderTime(orderEntity.getOrderTime()));
    Optional.ofNullable(orderEntity.getOrderStatus()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderStatus(orderEntity.getOrderStatus()));
    Optional.ofNullable(orderEntity.getOrderPaymentStatus()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderPaymentStatus(orderEntity.getOrderPaymentStatus()));
    Optional.ofNullable(orderEntity.getOrderPrice()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderPrice(orderEntity.getOrderPrice()));
    Optional.ofNullable(orderEntity.getOrderNumber()).ifPresent(
        c -> foundedOrderEntityById.get()
            .setOrderNumber(orderEntity.getOrderNumber()));
    Optional.ofNullable(orderEntity.getOrderTotalPrice()).ifPresent(
        c -> foundedOrderEntityById.get()
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
