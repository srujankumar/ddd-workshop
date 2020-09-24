package com.tw.dddworkshop.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartTest {

    @Test
    public void shouldAddProductToTheCart() {
        Cart cart = new Cart();
        Product product = new Product("IPad Pro");
        Item item = new Item(product, 1);

        cart.addItem(item);

        assertTrue(cart.toString().contains("IPad Pro"));
    }

    @Test
    public void shouldAddSecondProductToTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("IPad Pro");
        cart.addItem(new Item(product1, 1));
        Product product2 = new Product("Hero Ink Pen");

        cart.addItem(new Item(product2, 1));

        assertTrue(cart.toString().contains("IPad Pro"));
        assertTrue(cart.toString().contains("Hero Ink Pen"));
    }

    @Test
    public void shouldAddAProductWithQuantityMoreThanOne() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat");

        cart.addItem(new Item(product, 2));

        assertTrue(cart.toString().contains("GM Cricket Bat"));
        assertTrue(cart.toString().contains("quantity=2"));
    }

    @Test
    public void shouldRemoveItem() {
        Cart cart = new Cart();
        Product product1 = new Product("Hero Ink Pen");
        cart.addItem(new Item(product1, 1));
        Product product2 = new Product("GM Cricket Bat");
        cart.addItem(new Item(product2, 2));

        Item itemToBeRemoved = new Item(new Product("Hero Ink Pen"), 1);
        cart.removeItem(itemToBeRemoved);

        assertTrue(cart.toString().contains("GM Cricket Bat"));
        assertTrue(cart.toString().contains("quantity=2"));
        assertFalse(cart.toString().contains("Hero Ink Pen"));
    }

}
