package com.kantox.supermarket.discount;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;

public interface IDiscountHandler {
    BigDecimal applyDiscount(ICheckoutCart checkoutCart);

    void addDiscounts(IProduct product, IDiscount discount);
}
