package com.coding.sales.cx.product;

import com.coding.sales.cx.promotion.Promotion;
import com.coding.sales.cx.promotion.PromotionConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductAction {

    private List<Product> productList = new ArrayList<Product>();

    public ProductAction() {
        initProduct();
    }

    public void initProduct() {
        Product product = new Product();
        product.proName = "世园会五十国钱币册";
        product.proNo = "001001";
        product.unit = "册";
        product.price = 998.00f;
        productList.add(product);

        Product product1 = new Product();
        product1.proName = "2019北京世园会纪念银章大全40g";
        product1.proNo = "001002";
        product1.unit = "盒";
        product1.price = 1380.00f;
        product1.discountNo = 1;
        productList.add(product1);

        Product product2 = new Product();
        product2.proName = "招财进宝";
        product2.proNo = "003001";
        product2.unit = "条";
        product2.price = 1580.00f;
        product2.discountNo = 2;
        productList.add(product2);

        Product product3 = new Product();
        product3.proName = "水晶之恋";
        product3.proNo = "003002";
        product3.unit = "条";
        product3.price = 980.00f;
        product3.promotionNoList = new ArrayList<Integer>();
        product3.promotionNoList.add(PromotionConstant.PROMOTION_FLAG3HALF);
        productList.add(product3);

        Product product4 = new Product();
        product4.proName = "中国经典钱币套装";
        product4.proNo = "002002";
        product4.unit = "套";
        product4.price = 998.00f;
        product4.discountNo = 1;
        product4.promotionNoList  = new ArrayList<Integer>();
        product4.promotionNoList.add(PromotionConstant.PROMOTION_FLAG2000);
        product4.promotionNoList.add(PromotionConstant.PROMOTION_FLAG1000);
        productList.add(product4);


        Product product5 = new Product();
        product5.proName = "守扩之羽比翼双飞4.8g";
        product5.proNo = "002001";
        product5.unit = "条";
        product5.price = 1080.00f;
        product5.discountNo = 2;
        product5.promotionNoList = new ArrayList<Integer>();
        product5.promotionNoList.add(PromotionConstant.PROMOTION_FLAG3HALF);
        productList.add(product5);

        Product product6 = new Product();
        product6.proName = "中国银象棋12g";
        product6.proNo = "002003";
        product6.unit = "套";
        product6.price = 698.00f;
        product6.discountNo = 1;
        product6.promotionNoList = new ArrayList<Integer>();
        product6.promotionNoList.add(PromotionConstant.PROMOTION_FLAG3000);
        product6.promotionNoList.add(PromotionConstant.PROMOTION_FLAG2000);
        product6.promotionNoList.add(PromotionConstant.PROMOTION_FLAG1000);
        productList.add(product6);
    }

    /**
     * 获取产品
     * @param productNo 产品编号
     * @return
     */
    public Product getProduct(String productNo) {
        if (productNo == null || "".equals(productNo)) {
            return null;
        }
        for (Product product : productList) {
            if (productNo.equals(product.proNo)) {
                return product;
            }
        }
        return null;
    }

    // 获取单个产品的打折金额
    public float getDiscountMoney(String prdNo, int count, List<String> discountList) {
        Product product = getProduct(prdNo);
        float result = product.price * count;
        if (product.discountNo != 0) {
            for (String discount : discountList) {
                if (discount.equals("9折券") && product.discountNo == 1) {
                    return result * 0.9f;
                } else if (discount.equals("95折券") && product.discountNo == 2) {
                    return result * 0.95f;
                }
            }
        }
        return result;
    }


    // 获取单个产品的满减金额
    public float getPromotionMoney(String prdNo, int count) {
        Product product = getProduct(prdNo);
        float result = product.price * count;
        for (Integer promotionNo : product.promotionNoList) {

        }
    }




}
