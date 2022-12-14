package com.shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int count;

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);

        orderItem.setOrderPrice(item.getPrice());
        item.removeStock(count);

        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() { this.getItem().addStock(count); }
}
