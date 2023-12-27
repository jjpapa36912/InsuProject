package com.insu.bootakhae.business.order.domain;

import com.insu.bootakhae.business.login.domain.MemberEntity;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Order {

  private Long id;
  private String orderUser;
  private String orderTitle;
  private String orderContents;
  private String orderAcceptor;
  private LocalDateTime orderTime;
  private String orderStatus;
  private String orderPaymentStatus;
  private Integer orderPrice;
  private Integer orderNumber;
  private Integer orderTotalPrice;
  private MemberEntity memberEntity;

}
