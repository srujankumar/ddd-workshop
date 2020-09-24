package com.tw.dddworkshop.domain;

public interface ValueObject<T> {

    // Value objects compare by the values of their attributes, they don't have an identity
    boolean sameValueAs(T other);
}
