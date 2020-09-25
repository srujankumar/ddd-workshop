package com.tw.dddworkshop.domain;

import com.tw.dddworkshop.domain.events.CartCheckedOutEvent;
import com.tw.dddworkshop.domain.events.ItemAddedToCartEvent;
import com.tw.dddworkshop.domain.events.ItemRemovedFromCartEvent;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartTest {

    @Test
    public void shouldAddProductToTheCart() {
        Cart cart = new Cart();
        Product product = new Product("IPad Pro", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item = new Item(product, 1);

        cart.addItem(item);

        assertTrue(cart.getItems().contains(item));
    }

    @Test
    public void shouldAddSecondProductToTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("IPad Pro", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item1 = new Item(product1, 1);
        cart.addItem(item1);
        Product product2 = new Product("Hero Ink Pen", new Price(new BigDecimal(100), Currency.getInstance("INR")));

        Item item2 = new Item(product2, 1);
        cart.addItem(item2);

        assertEquals(cart.getItems().size(), 2);
        assertTrue(cart.getItems().contains(item1));
        assertTrue(cart.getItems().contains(item2));
    }

    @Test
    public void shouldAddAProductWithQuantityMoreThanOne() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));

        Item item = new Item(product, 2);
        cart.addItem(item);

        assertEquals(cart.getItems().get(0).getProduct().getName(),"GM Cricket Bat");
        assertEquals(cart.getItems().get(0).getProduct().getPrice(),new Price(new BigDecimal(100), Currency.getInstance("INR")));
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
    }

    @Test
    public void shouldRemoveItem() {
        Cart cart = new Cart();
        Product product1 = new Product("Hero Ink Pen", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item1 = new Item(product1, 1);
        cart.addItem(item1);
        Product product2 = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item2 = new Item(product2, 2);
        cart.addItem(item2);

        Item itemToBeRemoved = new Item(new Product("Hero Ink Pen", new Price(new BigDecimal(100), Currency.getInstance("INR"))), 1);
        cart.removeItem(itemToBeRemoved);

        assertEquals(cart.getItems().size(), 1);
        assertEquals(cart.getItems().get(0).getProduct().getName(), "GM Cricket Bat");
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
    }

    @Test
    public void shouldAddItemAddedToCartEventWhenItemIsAdded() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));

        Item item = new Item(product, 2);
        cart.addItem(item);

        assertEquals(cart.getItems().size(), 1);
        assertEquals(cart.getItems().get(0).getProduct().getName(), "GM Cricket Bat");
        assertEquals(cart.getItems().get(0).getQuantity(), 2);
        assertEquals(cart.getEvents().size(), 1);
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getProduct().getName(), "GM Cricket Bat");
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getQuantity(), 2);
    }

    @Test
    public void shouldAddItemRemovedFromCartEventWhenItemIsRemoved() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item = new Item(product, 2);
        cart.addItem(item);

        cart.removeItem(item);

        assertEquals(cart.getItems().size(), 0);
        assertEquals(cart.getEvents().size(), 2);
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getProduct().getName(), "GM Cricket Bat");
        assertEquals(( (ItemAddedToCartEvent) cart.getEvents().get(0)).getItem().getQuantity(), 2);
        assertEquals(( (ItemRemovedFromCartEvent) cart.getEvents().get(1)).getItem().getProduct().getName(), "GM Cricket Bat");
        assertEquals(( (ItemRemovedFromCartEvent) cart.getEvents().get(1)).getItem().getQuantity(), 2);
    }

    @Test
    public void shouldDifferentiateCartsOfDifferentUsers() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Product product = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item = new Item(product, 2);

        cart1.addItem(item);
        cart2.addItem(item);

        assertFalse(cart1.sameIdentityAs(cart2));
    }

    @Test
    public void shouldAddCartCheckedOutEventWithCurrentItemsWhenCartIsCheckedOut() {
        Cart cart = new Cart();
        Product product = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item = new Item(product, 2);
        cart.addItem(item);

        cart.checkOut();

        assertTrue(cart.getStatus().equals(Cart.Status.CHECKED_OUT));
        assertEquals(cart.getItems().size(), 1);
        assertEquals(cart.getEvents().size(), 2);
        assertEquals(( (CartCheckedOutEvent) cart.getEvents().get(1)).getItems().size(), 1);
        assertEquals(( (CartCheckedOutEvent) cart.getEvents().get(1)).getItems().get(0).getProduct().getName(), "GM Cricket Bat");
        assertEquals(( (CartCheckedOutEvent) cart.getEvents().get(1)).getItems().get(0).getQuantity(), 2);
    }

}
