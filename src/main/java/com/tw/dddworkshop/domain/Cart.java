package com.tw.dddworkshop.domain;

import com.tw.dddworkshop.domain.events.DomainEvent;
import com.tw.dddworkshop.domain.events.ItemAddedToCartEvent;
import com.tw.dddworkshop.domain.events.ItemRemovedFromCartEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Cart {

    private final UUID id;
    private List<Item> items;
    private List<DomainEvent> events;

    public Cart() {
        this.id = UUID.randomUUID();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", events=" + events +
                '}';
    }
}