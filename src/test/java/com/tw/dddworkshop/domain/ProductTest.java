package com.tw.dddworkshop.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertTrue;


public class ProductTest {

    @Test
    public void shouldBeReplaceableWithProductSameAttributes() {
        Product product1 = new Product("Samsung Galaxy", new Price(new BigDecimal(1000), Currency.getInstance("INR")));
        Product product2 = new Product("Samsung Galaxy", new Price(new BigDecimal(1000), Currency.getInstance("INR")));

        assertTrue(product1.sameValueAs(product2));
    }

}