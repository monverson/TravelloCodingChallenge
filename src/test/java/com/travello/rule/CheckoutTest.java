package com.travello.rule;

import com.travello.entity.Item;
import com.travello.entity.ItemProvider;
import com.travello.exception.NoPriceFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckoutTest {

    private static final String NO_PRICE_FOUND = "There is no price information available";

    @Test
    public void test_calculateTotalPrice_withDiscount() {
        //given
        Checkout checkout = getCheckout();
        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("B"));
        checkout.scan(new Item("B"));

        //when
        String price = checkout.calculateTotalPrice();

        //then
        assertEquals("175.0", price);
    }

    @Test
    public void test_calculateTotalPrice_withoutDiscount() {
        //given
        Checkout checkout = getCheckout();
        checkout.scan(new Item("C"));

        //when
        String price = checkout.calculateTotalPrice();

        //then
        assertEquals("20.0", price);
    }

    @Test
    public void test_calculateTotalPrice_empty_value() {
        //given
        Checkout checkout = new Checkout();
        checkout.scan(new Item("F"));

        //when
        NoPriceFoundException thrown = Assertions
                .assertThrows(NoPriceFoundException.class, checkout::calculateTotalPrice);

        //then
        assertEquals(NO_PRICE_FOUND, thrown.getMessage());

    }

    private Checkout getCheckout() {
        final Checkout checkout = new Checkout();
        ItemProvider itemProvider = new ItemProvider();
        checkout.add(new Item("A"), itemProvider.ruleForDiscount(50D, 130D, 3L));
        checkout.add(new Item("B"), itemProvider.ruleForDiscount(30D, 45D, 2L));
        checkout.add(new Item("C"), itemProvider.ruleForWithoutDiscount(20D));
        checkout.add(new Item("D"), itemProvider.ruleForWithoutDiscount(15D));
        return checkout;
    }

}