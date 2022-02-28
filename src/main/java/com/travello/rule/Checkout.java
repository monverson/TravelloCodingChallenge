package com.travello.rule;

import com.travello.entity.Item;
import com.travello.entity.ItemProvider;
import com.travello.exception.NoPriceFoundException;


import java.util.*;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toMap;


public class Checkout {

    private List<Item> itemList;
    private static final NetPriceCalculator netPriceCalculator = new NetPriceCalculator();
    private static final DiscountPriceCalculator discountPriceCalculator = new DiscountPriceCalculator();
    private static final Map<Item, ItemProvider> ITEM_EVALUATION_HASH_MAP = new HashMap<>();
    private static ItemProvider itemInformation = new ItemProvider();
    private static final String NO_PRICE_FOUND = "There is no price information available";

    public Checkout() {
        this.itemList = new ArrayList<>();
    }

    public void scan(Item item) {
        this.itemList.add(item);
    }

    public void add(Item itemName, ItemProvider itemInformation) {
        ITEM_EVALUATION_HASH_MAP.put(itemName, itemInformation);
    }

    public static BasePriceCalculator getPriceCalculator(Item item) {
        itemInformation = ITEM_EVALUATION_HASH_MAP.get(item);
        if (itemInformation == null) {
            throw new NoPriceFoundException(NO_PRICE_FOUND);
        }
        return itemInformation.getDiscountPrice() == null ? netPriceCalculator : discountPriceCalculator;

    }

    public String calculateTotalPrice() {
        Map<Item, Long> groupByItemAndQuantity = this.itemList.stream().filter(Objects::nonNull).collect(toMap(item -> item, item -> 1L, Long::sum));
        Double sum = groupByItemAndQuantity.entrySet().stream().mapToDouble(entry ->
                getPriceCalculator(entry.getKey()).calculateTotalPrice(itemInformation, entry.getValue())).sum();
        return valueOf(sum);
    }

}
