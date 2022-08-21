package com.kantox.supermarket.cart;

import com.kantox.supermarket.cart.impl.CheckoutCart;
import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.discount.impl.DiscountHandler;
import com.kantox.supermarket.discount.impl.GreenTeaDiscount;
import com.kantox.supermarket.product.IProduct;
import com.kantox.supermarket.product.impl.Coffee;
import com.kantox.supermarket.product.impl.GreenTea;
import com.kantox.supermarket.product.impl.StrawBerries;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutCartTest {
    private final IProduct strawberry = new StrawBerries(ProductCode.SR1, "Strawberries", BigDecimal.valueOf(5.00));
    private final IProduct greenTea = new GreenTea(ProductCode.GR1, "Green tea", BigDecimal.valueOf(3.11));
    private final IProduct coffee = new Coffee(ProductCode.CF1, "Coffee", BigDecimal.valueOf(11.23));

    @Test
    void getItems() {
        CheckoutCart checkoutCart = new CheckoutCart();
        checkoutCart.addProduct(strawberry);
        checkoutCart.addProduct(greenTea);
        checkoutCart.addProduct(coffee);
        assertNotNull(coffee.getProductName());
        assertNotNull(greenTea.getProductName());
        assertEquals(3, checkoutCart.getItems().size());
        assertEquals(1, checkoutCart.getItems().get(strawberry.getProductCode()));
        assertEquals(1, checkoutCart.getItems().get(greenTea.getProductCode()));
        assertEquals(1, checkoutCart.getItems().get(coffee.getProductCode()));
    }

    @Test
    void getItemsEmptyCart() {
        CheckoutCart checkoutCart = new CheckoutCart();
        assertEquals(0, checkoutCart.getItems().size());
    }

    @Test
    void getActualPrice() {
        CheckoutCart checkoutCart = new CheckoutCart();
        checkoutCart.addProduct(strawberry);
        assertEquals(5.00, checkoutCart.getActualPrice().doubleValue());
        assertEquals(5.00, checkoutCart.getDiscountedPrice().doubleValue());
    }

    @Test
    void getDiscountedPrice1() {
        CheckoutCart checkoutCart = new CheckoutCart();
        IDiscountHandler discountHandler = new DiscountHandler();
        discountHandler.addDiscounts(greenTea.getProductCode(), new GreenTeaDiscount());
        checkoutCart.setDiscountHandler(discountHandler);
        checkoutCart.addProduct(greenTea);
        checkoutCart.addProduct(greenTea);
        assertEquals(3.11, checkoutCart.getDiscountedPrice().doubleValue());
        assertEquals(6.22, checkoutCart.getActualPrice().doubleValue());
    }

    @Test
    void getDiscountedPriceNoDiscount() {
        CheckoutCart checkoutCart = new CheckoutCart();
        IDiscountHandler discountHandler = new DiscountHandler();
        checkoutCart.setDiscountHandler(discountHandler);
        checkoutCart.addProduct(greenTea);
        checkoutCart.addProduct(greenTea);
        assertEquals(6.22, checkoutCart.getDiscountedPrice().doubleValue());
        assertEquals(6.22, checkoutCart.getActualPrice().doubleValue());
    }

    @Test
    void addProduct() {
        CheckoutCart checkoutCart = new CheckoutCart();
        assertEquals(0, checkoutCart.getItems().size());
        checkoutCart.addProduct(coffee);
        assertEquals(1, checkoutCart.getItems().size());
    }

    @Test
    void addProductNull() {
        CheckoutCart checkoutCart = new CheckoutCart();
        assertEquals(0, checkoutCart.getItems().size());
        checkoutCart.addProduct(null);
        assertEquals(0, checkoutCart.getItems().size());
    }


    @Test
    void setDiscountHandler() {
        CheckoutCart checkoutCart = new CheckoutCart();
        IDiscountHandler discountHandler = new DiscountHandler();
        discountHandler.addDiscounts(greenTea.getProductCode(), new GreenTeaDiscount());
        checkoutCart.setDiscountHandler(discountHandler);
        checkoutCart.addProduct(greenTea);
        checkoutCart.addProduct(greenTea);
        assertEquals(3.11, checkoutCart.getDiscountedPrice().doubleValue());
    }

    @Test
    void setDiscountHandlerNull() {
        CheckoutCart checkoutCart = new CheckoutCart();
        checkoutCart.setDiscountHandler(null);
        checkoutCart.addProduct(greenTea);
        checkoutCart.addProduct(greenTea);
        assertEquals(6.22, checkoutCart.getDiscountedPrice().doubleValue());
    }
}