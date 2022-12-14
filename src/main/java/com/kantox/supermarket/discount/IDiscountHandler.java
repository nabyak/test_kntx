package com.kantox.supermarket.discount;

import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.product.IProduct;

public interface IDiscountHandler {

    IDiscount getDiscount(IProduct product);

    void addDiscounts(ProductCode productCode, IDiscount discount);
}
