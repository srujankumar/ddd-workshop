package com.tw.dddworkshop.domain.factory;

import com.tw.dddworkshop.domain.Address;
import com.tw.dddworkshop.domain.Cart;
import com.tw.dddworkshop.domain.Item;
import com.tw.dddworkshop.domain.Order;
import com.tw.dddworkshop.domain.Price;
import com.tw.dddworkshop.domain.Product;
import com.tw.dddworkshop.domain.ShippingInformation;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class OrderFactoryTest {

    @Test
    public void shouldCreateOrderWithAllItemsFromTheCart() {
        Cart cart = new Cart();
        Product product1 = new Product("Hero Ink Pen", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item1 = new Item(product1, 1);
        cart.addItem(item1);
        Product product2 = new Product("GM Cricket Bat", new Price(new BigDecimal(100), Currency.getInstance("INR")));
        Item item2 = new Item(product2, 2);
        cart.addItem(item2);
        ShippingInformation shippingInformation = new ShippingInformation(new Address("street", "city"));

        Order order = OrderFactory.create(cart, shippingInformation);

        assertEquals(order.getProducts().size(), 3);
        assertEquals(order.getProducts().get(0).getName(), "Hero Ink Pen");
        assertEquals(order.getProducts().get(1).getName(), "GM Cricket Bat");
        assertEquals(order.getProducts().get(1).getName(), "GM Cricket Bat");
    }

}