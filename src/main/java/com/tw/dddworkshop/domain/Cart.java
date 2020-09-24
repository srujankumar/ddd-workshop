package com.tw.dddworkshop.domain;

import com.tw.dddworkshop.domain.events.DomainEvent;
import com.tw.dddworkshop.domain.events.ItemAddedToCartEvent;
import com.tw.dddworkshop.domain.events.ItemRemovedFromCartEvent;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> items;
    private List<DomainEvent> events;

    public Cart() {
        this.items = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        events.add(new ItemAddedToCartEvent(item));
    }

    public void removeItem(Item item) {
        items.remove(item);
        events.add(new ItemRemovedFromCartEvent(item));
    }

    public List<Item> getItems() {
        return items;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", events=" + events +
                '}';
    }
}