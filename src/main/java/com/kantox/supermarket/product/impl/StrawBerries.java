package com.kantox.supermarket.product.impl;

import com.kantox.supermarket.product.AbstractProduct;

import java.math.BigDecimal;

public class StrawBerries extends AbstractProduct {
    public StrawBerries(String productCode, String productName, BigDecimal price) {
        super(productCode, productName, price);
    }
}
