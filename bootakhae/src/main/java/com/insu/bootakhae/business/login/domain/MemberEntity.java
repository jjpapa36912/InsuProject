package com.insu.bootakhae.business.login.domain;

import com.insu.bootakhae.business.order.domain.OrderEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity(name = "member")
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"userId", "name", "password"})
public class MemberEntity {

  public MemberEntity(String userId, String name, String password) {
    this.userId = userId;
    this.name = name;
    this.password = password;
  }

  public MemberEntity(Long id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "user_id")
  private String userId; // 로그인 id
  @Column(name = "name")
  private String name; // 사용자 이름
  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "memberEntity")
  private List<OrderEntity> orderEntities = new ArrayList<>();
}
