package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public class GreenTeaDiscount implements IDiscount {
    @Override
    public BigDecimal getDiscountedPrice(IProduct product, int count) {

        BigDecimal multiplicand = new BigDecimal(count);
        if (count >= 2) {
            int numbersTobeDiscounted = count / 2;
            int remainingCount = count % 2;
            BigDecimal discountedPrice = product.getPrice().multiply(BigDecimal.valueOf(numbersTobeDiscounted));
            BigDecimal nonDiscountedPrice = product.getPrice().multiply(BigDecimal.valueOf(remainingCount));
            return discountedPrice.add(nonDiscountedPrice);
        }
        return product.getPrice().multiply(multiplicand);
    }
}
