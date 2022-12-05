package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_IMG")
@Getter
@Setter
public class ItemImg extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_IMG_ID")
    private Long id;

    @Column(name = "IMG_NAME")
    private String imgName;

    @Column(name = "ORIGINAL_IMG_NAME")
    private String oriImgName;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "REP_IMG_YN")
    private String repImgYn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
