package com.travello.rule;

import com.travello.entity.ItemProvider;


public class DiscountPriceCalculator implements BasePriceCalculator {

    /**
     *
     * Calculates the total price when there is any discount.
     *
     * @param itemProvider
     * @param quantityOfItems
     * @return TotalPrice
     */
    @Override
    public Double calculateTotalPrice(ItemProvider itemProvider, Long quantityOfItems) {
        var discountedPrice = itemProvider.getDiscountPrice() * quantityOfItems / itemProvider.getDiscountQuantity();
        var netPrice = itemProvider.getPrice() * (quantityOfItems % itemProvider.getDiscountQuantity());
        return discountedPrice + netPrice;
    }
}
