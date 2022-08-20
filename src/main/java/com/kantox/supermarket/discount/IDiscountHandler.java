package com.kantox.supermarket.discount;

import com.kantox.supermarket.product.IProduct;

public interface IDiscountHandler {

    IDiscount getDiscount(IProduct product);

    void addDiscounts(IProduct product, IDiscount discount);
}
