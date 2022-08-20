package com.kantox.supermarket.product;

import com.kantox.supermarket.constants.ProductCode;

import java.math.BigDecimal;

public class AbstractProduct implements IProduct{

    private ProductCode productCode;
    private String productName;
    private BigDecimal price;

    public AbstractProduct(ProductCode productCode, String productName, BigDecimal price) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
    }


    @Override
    public ProductCode getProductCode() {
        return productCode;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
