package com.kantox.supermarket.cart.impl;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;
import java.util.Map;

public class CheckoutCart implements ICheckoutCart {

    private Map<ProductCode, Integer> items;
    private BigDecimal actualPrice;
    private BigDecimal discountedPrice;

    private IDiscountHandler discountHandler;


    @Override
    public Map<ProductCode, Integer> getItems() {
        return items;
    }

    @Override
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    @Override
    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    @Override
    public void addProduct(IProduct product) {
        Integer itemCount = items.getOrDefault(product.getProductCode(), 0);
        itemCount++;
        actualPrice = actualPrice.add(product.getPrice());
        IDiscount discount = discountHandler.getDiscount(product);
        if (discount != null) {
            discountedPrice = discountedPrice.add(discount.getDiscountedPrice(product, itemCount));
        } else {
            discountedPrice = actualPrice;
        }
        items.put(product.getProductCode(), itemCount);
    }

    @Override
    public void setDiscountHandler(IDiscountHandler discountHandler) {
        this.discountHandler = discountHandler;
    }
}
