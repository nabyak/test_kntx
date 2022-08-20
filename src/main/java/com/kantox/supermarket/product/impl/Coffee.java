package com.kantox.supermarket.product.impl;

import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.product.AbstractProduct;

import java.math.BigDecimal;

public class Coffee extends AbstractProduct {
    public Coffee(ProductCode productCode, String productName, BigDecimal price) {
        super(productCode, productName, price);
    }
}
