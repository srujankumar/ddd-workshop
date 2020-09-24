package com.tw.dddworkshop.domain;

import com.tw.dddworkshop.domain.events.ItemAddedToCartEvent;
import com.tw.dddworkshop.domain.events.ItemRemovedFromCartEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartTest {

    @Test
    public void shouldAddProductToTheCart() {
        Cart cart = new Cart();
        Product product = new Product("IPad Pro");
        Item item = new Item(product, 1);

        cart.addItem(item);

        assertTrue(cart.getItems().contains(item));
    }

    @Test
    public void shouldAddSecondProductToTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("IPad Pro");
        Item item1 = new Item(product1, 1);
        cart.addItem(item1);
        Product product2 = new Product("Hero Ink Pen");

        Item item2 = new Item(product2, 1);
        cart.addItem(item2);

        assertEquals(cart.getItems().size(), 2);
        assertTrue(cart.getItems().contains(item1));
        assertTrue(cart.getItems().contains(item2));
    }

    @Test
    public void shouldAddAProductWithQuantityMoreThanOne() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat");

        Item item = new Item(product, 2);
        cart.addItem(item);

        assertTrue(cart.getItems().get(0).getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
    }

    @Test
    public void shouldRemoveItem() {
        Cart cart = new Cart();
        Product product1 = new Product("Hero Ink Pen");
        Item item1 = new Item(product1, 1);
        cart.addItem(item1);
        Product product2 = new Product("GM Cricket Bat");
        Item item2 = new Item(product2, 2);
        cart.addItem(item2);

        Item itemToBeRemoved = new Item(new Product("Hero Ink Pen"), 1);
        cart.removeItem(itemToBeRemoved);

        assertEquals(cart.getItems().size(), 1);
        assertTrue(cart.getItems().get(0).getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
    }

    @Test
    public void shouldAddItemAddedToCartEventWhenItemIsAdded() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat");

        Item item = new Item(product, 2);
        cart.addItem(item);

        assertEquals(cart.getItems().size(), 1);
        assertTrue(cart.getItems().get(0).getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
        assertEquals(cart.getEvents().size(), 1);
        assertTrue(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getQuantity(), 2);
    }

    @Test
    public void shouldAddItemRemovedFromCartEventWhenItemIsRemoved() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat");
        Item item = new Item(product, 2);
        cart.addItem(item);

        cart.removeItem(item);

        System.out.println(cart);

        assertEquals(cart.getItems().size(), 0);
        assertEquals(cart.getEvents().size(), 2);
        assertTrue(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getQuantity(), 2);
        assertTrue(( (ItemRemovedFromCartEvent) cart.getEvents().get(1)).getItem().getProduct().toString().contains("GM Cricket Bat"));
        assertEquals(( (ItemRemovedFromCartEvent) cart.getEvents().get(1)).getItem().getQuantity(), 2);
    }

    @Test
    public void shouldDifferentiateCartsOfDifferentUsers() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Product product = new Product("GM Cricket Bat");
        Item item = new Item(product, 2);

        cart1.addItem(item);
        cart2.addItem(item);

        assertFalse(cart1.sameIdentityAs(cart2));
    }

}
