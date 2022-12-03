package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ITEM_NAME", nullable = false, length = 50)
    private String itemNm;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "STOCK_NUMBER", nullable = false)
    private int stockNumber;

    @Lob
    @Column(name = "ITEM_DETAIL", nullable = false)
    private String itemDetail;

    @Column
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
}
