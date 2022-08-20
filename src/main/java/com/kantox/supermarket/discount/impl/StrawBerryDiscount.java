package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public class StrawBerryDiscount implements IDiscount {

    @Override
    public BigDecimal getDiscountedPrice(IProduct product, int count) {
        return null;
    }
}
