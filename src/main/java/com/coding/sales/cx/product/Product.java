package com.coding.sales.cx.product;

import java.math.BigDecimal;
import java.util.List;

public class Product {

    public String proName;

    public String proNo;

    public String unit;

    public float price;

    public int discountNo;// 0代表无打折，1代表9折，2代表95折

    public List<Integer> promotionNoList;// 0代表无促销

}
