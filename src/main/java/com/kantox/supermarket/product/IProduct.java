package com.kantox.supermarket.product;

import com.kantox.supermarket.constants.ProductCode;

import java.math.BigDecimal;

public interface IProduct {
    ProductCode getProductCode();
    String getProductName();
    BigDecimal getPrice();
}
