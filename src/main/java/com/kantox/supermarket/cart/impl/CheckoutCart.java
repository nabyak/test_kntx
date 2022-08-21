package com.kantox.supermarket.cart.impl;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CheckoutCart implements ICheckoutCart {

    private Map<ProductCode, Integer> items;
    private BigDecimal actualPrice;
    private BigDecimal discountedPrice;

    private IDiscountHandler discountHandler;

    public CheckoutCart() {
        items = new HashMap<>();
        actualPrice = BigDecimal.ZERO;
        discountedPrice = BigDecimal.ZERO;

        actualPrice.setScale(2, RoundingMode.HALF_EVEN);
    }


    @Override
    public Map<ProductCode, Integer> getItems() {
        return items;
    }

    @Override
    public BigDecimal getActualPrice() {
        // Rounding off to two decimal places
        return actualPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal getDiscountedPrice() {
        // Rounding off to two decimal places
        return discountedPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void addProduct(IProduct product) {
        Integer itemCount = items.getOrDefault(product.getProductCode(), 0);
        itemCount++;
        actualPrice = actualPrice.add(product.getPrice());
        IDiscount discount = discountHandler.getDiscount(product);
        if (discount != null) {
            //Reduce the previously added price for the item
            discountedPrice = discountedPrice.subtract(discount.getDiscountedPrice(product, itemCount - 1));
            // Then add the discounted price for the new count of items
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
