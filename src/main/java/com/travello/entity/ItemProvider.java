package com.travello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ItemProvider {

    private Double price;
    private Double discountPrice;
    private Long discountQuantity;

    public ItemProvider ruleForWithoutDiscount(Double price) {
        return new ItemProvider(price);
    }

    public ItemProvider ruleForDiscount(Double price, Double discountPrice, Long quantity) {
        return new ItemProvider(price, discountPrice, quantity);
    }

    public ItemProvider(Double price) {
        this.price = price;
    }

    public ItemProvider(Double price, Double discountPrice, Long discountQuantity) {
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountQuantity = discountQuantity;
    }

}
