package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public class CoffeeDiscount implements IDiscount {
    BigDecimal discountFactor = BigDecimal.valueOf(2.0 / 3.0);

    @Override
    public BigDecimal getDiscountedPrice(IProduct product, int count) {
        BigDecimal multiplicand = new BigDecimal(count);
        BigDecimal actualTotalPrice = product.getPrice().multiply(multiplicand);
        if (count >= 3) {
            return actualTotalPrice.multiply(discountFactor);
        }
        return actualTotalPrice;
    }
}
