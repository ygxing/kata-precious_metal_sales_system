package com.coding.sales.cx.product;

public class Product {

    public String proName;

    public String proNo;

    public String unit;

    public double price;

    public int discountNo;// 0代表无打折，1代表9折，2代表95折

    public int promotionNo;// 0代表无促销

    public double afterPromotionAmount; // 促销后的金额

    public double afterDiscountAmount; // 打折后的金额
}
