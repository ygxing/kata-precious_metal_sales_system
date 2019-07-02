package com.coding.sales.cx.product;

import java.util.List;

public class OrderDetail {
    public String productId;
    public String productName;
    public int count;
    public float productPrice;

    @Override
    public String toString() {
        return super.toString();
    }

    public void initOrderDetail(Product product, int count) {
        productId = product.proNo;
        productName = product.proName;
        productPrice = product.price;
        this.count = count;
    }
}
