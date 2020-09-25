package com.tw.dddworkshop.domain.factory;

import com.tw.dddworkshop.domain.Cart;
import com.tw.dddworkshop.domain.Item;
import com.tw.dddworkshop.domain.Order;
import com.tw.dddworkshop.domain.Product;
import com.tw.dddworkshop.domain.ShippingInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {

    public static Order create(Cart cart, ShippingInformation shippingInformation) {
        List<Item> items = cart.getItems();

        List<Product> allItems = items.stream().map(item -> {
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < item.getQuantity(); i++) {
                products.add(item.getProduct());
            }
            return products;
        }).flatMap(List::stream).collect(Collectors.toList());

        return new Order(allItems, shippingInformation);
    }
}
