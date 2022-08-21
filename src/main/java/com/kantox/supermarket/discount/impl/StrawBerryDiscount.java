package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public class StrawBerryDiscount implements IDiscount {
    BigDecimal discountedPrice = BigDecimal.valueOf(4.5);
    @Override
    public BigDecimal getDiscountedPrice(IProduct product, int count) {

        BigDecimal multiplicand = new BigDecimal(count);
        if (count >= 3){
            return discountedPrice.multiply(multiplicand);
        }
        return product.getPrice().multiply(multiplicand);
    }
}
