package com.travello.rule;

import com.travello.entity.ItemProvider;

public interface BasePriceCalculator {
    Double calculateTotalPrice(ItemProvider itemProvider, Long quantityOfItems);
}
