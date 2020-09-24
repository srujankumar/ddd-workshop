package com.tw.dddworkshop.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class CartTest {

    @Test
    public void shouldAddProductToTheCart() {
        Cart cart = new Cart();
        Product product = new Product("IPad Pro");
        Item item = new Item(product, 1);

        cart.addItem(item);

        assertThat(cart.toString(), containsString("IPad Pro"));
    }

    @Test
    public void shouldAddSecondProductToTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("IPad Pro");
        cart.addItem(new Item(product1, 1));
        Product product2 = new Product("Hero Ink Pen");

        cart.addItem(new Item(product2, 1));

        assertThat(cart.toString(), containsString("IPad Pro"));
        assertThat(cart.toString(), containsString("Hero Ink Pen"));
    }

    @Test
    public void shouldAddAProductWithQuantityMoreThanOne() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat");

        cart.addItem(new Item(product, 2));

        assertThat(cart.toString(), containsString("GM Cricket Bat"));
        assertThat(cart.toString(), containsString("quantity=2"));
    }

}
