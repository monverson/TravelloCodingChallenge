package com.travello.rule;

import com.travello.entity.ItemProvider;

public class NetPriceCalculator implements BasePriceCalculator {

    /**
     *
     * Calculates the total price when there is no discount.
     *
     * @param itemProvider
     * @param quantityOfItems
     * @return TotalPrice
     */


    @Override
    public Double calculateTotalPrice(ItemProvider itemProvider, Long quantityOfItems) {
        return quantityOfItems * itemProvider.getPrice();
    }
}
