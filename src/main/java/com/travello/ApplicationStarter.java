package com.travello;

import com.travello.entity.Item;
import com.travello.entity.ItemProvider;
import com.travello.rule.Checkout;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ApplicationStarter {
    public static void main(String[] args) {


        final Checkout checkout = new Checkout();
        ItemProvider itemProvider = new ItemProvider();
        checkout.add(new Item("A"), itemProvider.ruleForDiscount(50D, 130D, 3L));
        checkout.add(new Item("B"), itemProvider.ruleForDiscount(30D, 45D, 2L));
        checkout.add(new Item("C"), itemProvider.ruleForWithoutDiscount(20D));
        checkout.add(new Item("D"), itemProvider.ruleForWithoutDiscount(15D));


        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("B"));
        checkout.scan(new Item("B"));
        checkout.scan(new Item("C"));
        checkout.scan(new Item("D"));

        String totalPrice = checkout.calculateTotalPrice();

        log.info("Total Price {}", totalPrice);


    }

}
