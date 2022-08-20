package com.kantox.supermarket.cashier;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.cart.impl.CheckoutCart;
import com.kantox.supermarket.discount.IDiscount;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.product.IProduct;

import java.util.Map;

public class CheckoutHandler {

    private ICheckoutCart currentCart;
    private IDiscountHandler discountHandler;

    private static volatile CheckoutHandler handler;

    private CheckoutHandler() {

    }

    public static CheckoutHandler getCheckoutHandler() {
        if (handler == null) {
            synchronized (CheckoutHandler.class) {
                if (handler == null) {
                    handler = new CheckoutHandler();
                }
            }
        }
        return handler;
    }

    public void prepareTill() {
        currentCart =  new CheckoutCart();
        currentCart.setDiscountHandler(discountHandler);
    }

    public void addProductToCart(IProduct product) {
        if (currentCart == null) {
            currentCart = new CheckoutCart();
            currentCart.setDiscountHandler(discountHandler);
        }

        currentCart.addProduct(product);
    }

    public void addDiscountHandler(IDiscountHandler handler) {
        discountHandler = handler;
    }

    public ICheckoutCart getCurrentCart() {
        return currentCart;
    }

}
