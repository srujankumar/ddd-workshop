package com.tw.dddworkshop.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class CartTest {

    @Test
    public void shouldAddProductToTheCart() {
        Cart cart = new Cart();
        Product product = new Product("IPad Pro");

        cart.addProduct(product);

        assertThat(cart.toString(), containsString("IPad Pro"));
    }

    @Test
    public void shouldAddSecondProductToTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("IPad Pro");
        cart.addProduct(product1);
        Product product2 = new Product("Hero Ink Pen");

        cart.addProduct(product2);

        assertThat(cart.toString(), containsString("IPad Pro"));
        assertThat(cart.toString(), containsString("Hero Ink Pen"));
    }

}
