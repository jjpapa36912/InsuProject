package com.insu.bootakhae.business.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

  public OrderEntity(String userId, String orderAcceptor, LocalDateTime orderTime,
      String orderStatus, String orderPaymentStatus, Integer orderPrice,
      Integer orderNumber, Integer orderTotalPrice) {
    this.userId = userId;
    this.orderAcceptor = orderAcceptor;
    this.orderTime = orderTime;
    this.orderStatus = orderStatus;
    this.orderPaymentStatus = orderPaymentStatus;
    this.orderPrice = orderPrice;
    this.orderNumber = orderNumber;
    this.orderTotalPrice = orderTotalPrice;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "order_acceptor")
  private String orderAcceptor;
  @Column(name = "order_time")
  private LocalDateTime orderTime;
  @Column(name = "order_status")
  private String orderStatus;
  @Column(name = "order_payment_status")
  private String orderPaymentStatus;
  @Column(name = "order_price")
  private Integer orderPrice;
  @Column(name = "order_number")
  private Integer orderNumber;
  @Column(name = "order_total_price")
  private Integer orderTotalPrice;


}
