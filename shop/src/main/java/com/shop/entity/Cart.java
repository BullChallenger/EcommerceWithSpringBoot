package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "CART")
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
