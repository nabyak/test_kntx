package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DiscountHandler implements IDiscountHandler {
    Map<ProductCode, IDiscount> discountMap;


    public DiscountHandler(){
        discountMap = new HashMap<>();
    }

    @Override
    public BigDecimal applyDiscount(ICheckoutCart checkoutCart) {
        return BigDecimal.ZERO;
    }

    @Override
    public void addDiscounts(IProduct product, IDiscount discount) {
        discountMap.put(product.getProductCode(), discount);
    }
}
