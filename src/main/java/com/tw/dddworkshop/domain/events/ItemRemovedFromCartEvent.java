package com.tw.dddworkshop.domain.events;

public class ItemRemovedFromCartEvent implements DomainEvent {

    final private String productName;

    public ItemRemovedFromCartEvent(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
