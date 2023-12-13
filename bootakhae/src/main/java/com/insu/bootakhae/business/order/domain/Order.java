package com.insu.bootakhae.business.order.domain;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Order {

  private Long id;
  private String userId;
  private String orderAcceptor;
  private LocalDateTime orderTime;
  private String orderStatus;
  private String orderPaymentStatus;
  private Integer orderPrice;
  private Integer orderNumber;
  private Integer orderTotalPrice;

}
