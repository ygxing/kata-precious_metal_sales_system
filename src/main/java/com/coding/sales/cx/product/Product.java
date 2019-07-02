package com.coding.sales.cx.product;

import java.util.List;

public class Product {

    public String proName;

    public String proNo;

    public String unit;

    public float price;

    public int discountNo;// 0代表无打折，1代表9折，2代表95折

    public float discountValue = 0.00f;// 打折金额

    public List<Integer> promotionNoList;// 0代表无促销

    public int finalPromotionFlag = 0;// 标示最终优惠编号

    public float promotionValue = 0.00f; // 优惠金额



}
