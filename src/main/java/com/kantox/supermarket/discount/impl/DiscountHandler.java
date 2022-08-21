package com.kantox.supermarket.discount.impl;

import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.util.HashMap;
import java.util.Map;

public class DiscountHandler implements IDiscountHandler {
    Map<ProductCode, IDiscount> discountMap;


    public DiscountHandler() {
        discountMap = new HashMap<>();
    }

    @Override
    public IDiscount getDiscount(IProduct product) {
        return discountMap.get(product.getProductCode());
    }

    @Override
    public void addDiscounts(ProductCode productCode, IDiscount discount) {
        discountMap.put(productCode, discount);
    }
}
