package com.tw.dddworkshop.domain.domainservice;

import com.tw.dddworkshop.domain.Address;
import com.tw.dddworkshop.domain.Order;
import com.tw.dddworkshop.domain.Product;

public class Shipper {

    public static void ship(Order order) {
        order.getProducts().forEach(product -> {
            shipPackageToAddress(product, order.getShippingInformation().getAddress());
        });
    }

    private static void shipPackageToAddress(Product product, Address address) {
        // Does some stuff around creating shipments with carrier
    }
}
