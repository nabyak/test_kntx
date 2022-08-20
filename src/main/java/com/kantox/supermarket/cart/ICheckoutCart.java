package com.kantox.supermarket.cart;

import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;
import java.util.Map;

public interface ICheckoutCart {
    Map<ProductCode, Integer> getItems();
    BigDecimal getActualPrice();
    BigDecimal getDiscountedPrice();
    void addProduct(IProduct product);
    void setDiscountHandler(IDiscountHandler discountHandler);
}
