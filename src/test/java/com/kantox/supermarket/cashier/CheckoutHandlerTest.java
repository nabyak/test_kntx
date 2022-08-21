package com.kantox.supermarket.cashier;

import com.kantox.supermarket.cart.ICheckoutCart;
import com.kantox.supermarket.constants.ProductCode;
import com.kantox.supermarket.discount.IDiscountHandler;
import com.kantox.supermarket.discount.impl.CoffeeDiscount;
import com.kantox.supermarket.discount.impl.DiscountHandler;
import com.kantox.supermarket.discount.impl.GreenTeaDiscount;
import com.kantox.supermarket.discount.impl.StrawBerryDiscount;
import com.kantox.supermarket.product.IProduct;
import com.kantox.supermarket.product.impl.Coffee;
import com.kantox.supermarket.product.impl.GreenTea;
import com.kantox.supermarket.product.impl.StrawBerries;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutHandlerTest {

    private CheckoutHandler checkoutHandler = CheckoutHandler.getCheckoutHandler();
    private final IProduct strawberry = new StrawBerries(ProductCode.SR1, "Strawberries", BigDecimal.valueOf(5.00));
    private final IProduct greenTea = new GreenTea(ProductCode.GR1, "Green tea", BigDecimal.valueOf(3.11));
    private final IProduct coffee = new Coffee(ProductCode.CF1, "Coffee", BigDecimal.valueOf(11.23));

    @Test
    void getCheckoutHandler() {
        assertNotNull(checkoutHandler);
        assertEquals(checkoutHandler, CheckoutHandler.getCheckoutHandler());
    }

    @Test
    void getCurrentCartWithoutPrepareTill() {
        checkoutHandler.invalidateCart();
        assertNull(checkoutHandler.getCurrentCart());
        checkoutHandler.addProductToCart(strawberry);
        assertNotNull(checkoutHandler.getCurrentCart());
    }

    @Test
    void prepareTill() {
        checkoutHandler.prepareTill();
        assertNotNull(checkoutHandler.getCurrentCart());
    }

    @Test
    void addProductToCart() {
        checkoutHandler.invalidateCart();
        checkoutHandler.addDiscountHandler(new DiscountHandler());
        checkoutHandler.addProductToCart(strawberry);
        ICheckoutCart currentCart = checkoutHandler.getCurrentCart();
        assertEquals(1, currentCart.getItems().get(ProductCode.SR1));
        assertEquals(5.00, currentCart.getActualPrice().doubleValue());
    }

    @Test
    void productionTestCase1() {
        DiscountHandler handler = new DiscountHandler();
        handler.addDiscounts(ProductCode.SR1, new StrawBerryDiscount());
        handler.addDiscounts(ProductCode.GR1, new GreenTeaDiscount());
        handler.addDiscounts(ProductCode.CF1, new CoffeeDiscount());
        checkoutHandler.addDiscountHandler(handler);
        checkoutHandler.addProductToCart(greenTea);
        checkoutHandler.addProductToCart(strawberry);
        checkoutHandler.addProductToCart(greenTea);
        checkoutHandler.addProductToCart(greenTea);
        checkoutHandler.addProductToCart(coffee);
        ICheckoutCart currentCart = checkoutHandler.getCurrentCart();
        assertEquals(3, currentCart.getItems().get(ProductCode.GR1));
        assertEquals(1, currentCart.getItems().get(ProductCode.SR1));
        assertEquals(1, currentCart.getItems().get(ProductCode.CF1));
        assertEquals(22.45, currentCart.getDiscountedPrice().doubleValue());
        assertEquals(25.56, currentCart.getActualPrice().doubleValue());
    }


    @Test
    void productionTestCase2() {
        DiscountHandler handler = new DiscountHandler();
        checkoutHandler.prepareTill();
        handler.addDiscounts(ProductCode.SR1, new StrawBerryDiscount());
        handler.addDiscounts(ProductCode.GR1, new GreenTeaDiscount());
        handler.addDiscounts(ProductCode.CF1, new CoffeeDiscount());
        checkoutHandler.addDiscountHandler(handler);
        checkoutHandler.addProductToCart(greenTea);
        checkoutHandler.addProductToCart(greenTea);
        ICheckoutCart currentCart = checkoutHandler.getCurrentCart();
        assertEquals(2, currentCart.getItems().get(ProductCode.GR1));
        assertNull(currentCart.getItems().get(ProductCode.SR1));
        assertNull(currentCart.getItems().get(ProductCode.CF1));
        assertEquals(3.11, currentCart.getDiscountedPrice().doubleValue());
        assertEquals(6.22, currentCart.getActualPrice().doubleValue());
    }

    @Test
    void productionTestCase3() {
        DiscountHandler handler = new DiscountHandler();
        handler.addDiscounts(ProductCode.SR1, new StrawBerryDiscount());
        handler.addDiscounts(ProductCode.GR1, new GreenTeaDiscount());
        handler.addDiscounts(ProductCode.CF1, new CoffeeDiscount());
        checkoutHandler.addDiscountHandler(handler);
        checkoutHandler.prepareTill();
        checkoutHandler.addProductToCart(strawberry);
        checkoutHandler.addProductToCart(strawberry);
        checkoutHandler.addProductToCart(strawberry);
        checkoutHandler.addProductToCart(greenTea);
        ICheckoutCart currentCart = checkoutHandler.getCurrentCart();
        assertEquals(1, currentCart.getItems().get(ProductCode.GR1));
        assertEquals(3, currentCart.getItems().get(ProductCode.SR1));
        assertNull(currentCart.getItems().get(ProductCode.CF1));
        assertEquals(16.61, currentCart.getDiscountedPrice().doubleValue());
        assertEquals(18.11, currentCart.getActualPrice().doubleValue());
    }

    @Test
    void productionTestCase4() {
        checkoutHandler.prepareTill();
        DiscountHandler handler = new DiscountHandler();
        handler.addDiscounts(ProductCode.SR1, new StrawBerryDiscount());
        handler.addDiscounts(ProductCode.GR1, new GreenTeaDiscount());
        handler.addDiscounts(ProductCode.CF1, new CoffeeDiscount());
        checkoutHandler.addDiscountHandler(handler);
        checkoutHandler.addProductToCart(greenTea);
        checkoutHandler.addProductToCart(coffee);
        checkoutHandler.addProductToCart(strawberry);
        checkoutHandler.addProductToCart(coffee);
        checkoutHandler.addProductToCart(coffee);
        ICheckoutCart currentCart = checkoutHandler.getCurrentCart();
        assertEquals(1, currentCart.getItems().get(ProductCode.GR1));
        assertEquals(1, currentCart.getItems().get(ProductCode.SR1));
        assertEquals(3, currentCart.getItems().get(ProductCode.CF1));
        assertEquals(30.57, currentCart.getDiscountedPrice().doubleValue());
        assertEquals(41.80, currentCart.getActualPrice().doubleValue());
    }

    @Test
    void addDiscountHandler() {
        final DiscountHandler discountHandler = new DiscountHandler();
        checkoutHandler.addDiscountHandler(discountHandler);
        IDiscountHandler discountHandlerActual = checkoutHandler.getDiscountHandler();
        assertNotNull(discountHandlerActual);
        assertEquals(discountHandler, discountHandlerActual);
    }

    @Test
    void getCurrentCart() {
        checkoutHandler.prepareTill();
        assertNotNull(checkoutHandler.getCurrentCart());
    }

    @Test
    void getDiscountHandler() {
        final DiscountHandler discountHandler = new DiscountHandler();
        checkoutHandler.addDiscountHandler(discountHandler);
        IDiscountHandler discountHandlerActual = checkoutHandler.getDiscountHandler();
        assertNotNull(discountHandlerActual);
        assertEquals(discountHandler, discountHandlerActual);
    }
}