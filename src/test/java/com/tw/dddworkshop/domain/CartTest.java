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

}
