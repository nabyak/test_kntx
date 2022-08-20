package com.kantox.supermarket.discount;

import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public interface IDiscount {
    BigDecimal getDiscountedPrice(IProduct product, int count);
}
