package com.insu.bootakhae.business.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insu.bootakhae.business.login.domain.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;
  @Column(name = "order_user")
  private String orderUser;
  @Column(name = "order_title")
  private String orderTitle;
  @Column(name = "order_contents")
  private String orderContents;
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

  @NotNull
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private MemberEntity memberEntity;

}
