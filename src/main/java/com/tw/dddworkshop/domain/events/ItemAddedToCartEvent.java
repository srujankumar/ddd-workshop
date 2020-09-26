package com.tw.dddworkshop.domain.events;

import com.tw.dddworkshop.domain.Price;

public class ItemAddedToCartEvent implements DomainEvent {

    final private String productName;
    final private Price price;
    final private int quantity;

    public ItemAddedToCartEvent(String productName, Price price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Price getPrice() {
        return price;
    }
}
