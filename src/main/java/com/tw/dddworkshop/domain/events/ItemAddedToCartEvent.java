package com.tw.dddworkshop.domain.events;

import com.tw.dddworkshop.domain.Item;

public class ItemAddedToCartEvent implements DomainEvent {

    private Item item;

    public ItemAddedToCartEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
