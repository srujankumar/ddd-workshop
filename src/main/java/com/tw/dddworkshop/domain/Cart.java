package com.tw.dddworkshop.domain;

import com.tw.dddworkshop.domain.events.CartCheckedOutEvent;
import com.tw.dddworkshop.domain.events.DomainEvent;
import com.tw.dddworkshop.domain.events.ItemAddedToCartEvent;
import com.tw.dddworkshop.domain.events.ItemRemovedFromCartEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart implements Entity<Cart> {

    private final UUID id;
    private List<Item> items;
    private List<DomainEvent> events;
    private Status status;

    public Cart() {
        this.id = UUID.randomUUID();
        this.items = new ArrayList<>();
        this.events = new ArrayList<>();
        this.status = Status.AVAILABLE;
    }

    public void addItem(Item item) {
        this.apply(new ItemAddedToCartEvent(item.getProduct().getName(), item.getProduct().getPrice(), item.getQuantity()));
    }

    public void removeItem(Item item) {
        this.apply(new ItemRemovedFromCartEvent(item.getProduct().getName()));
    }

    private void apply(ItemAddedToCartEvent event) {
        events.add(event);
        this.items.add(new Item(new Product(event.getProductName(), event.getPrice()), event.getQuantity()));
    }

    private void apply(ItemRemovedFromCartEvent event) {
        events.add(event);
        this.items.remove(this.items.stream().filter(item -> item.getProduct().getName().equals(event.getProductName())).findFirst().get());
    }

    public void checkOut() {
        this.status = Status.CHECKED_OUT;
        events.add(new CartCheckedOutEvent(items));
    }

    public List<Item> getItems() {
        return items;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean sameIdentityAs(Cart other) {
        return other != null && id.equals(other.id);
    }

    public enum Status {
        CHECKED_OUT,
        AVAILABLE
    }
}