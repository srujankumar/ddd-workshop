package com.tw.dddworkshop.domain.events;

import com.tw.dddworkshop.domain.Item;

public class ItemRemovedFromCartEvent implements DomainEvent {

    private Item item;

    public ItemRemovedFromCartEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
