package com.coding.sales.cx.product;

import com.coding.sales.cx.promotion.PromotionConstant;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;

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

    // 获取订单详情
    public List<OrderItemRepresentation> getOrderDetailInfoList(List<OrderItemCommand> orderItemCommandList) {
        if (orderItemCommandList == null) {
            return null;
        }
        List<OrderItemRepresentation> orderDetailList = new ArrayList<OrderItemRepresentation>();
        for (OrderItemCommand orderItem : orderItemCommandList) {
            Product product = getProduct(orderItem.getProduct());
            BigDecimal bigDecimal = new BigDecimal(product.price * orderItem.getAmount().intValue());
            OrderItemRepresentation orderDetail = new OrderItemRepresentation(product.proNo, product.proName, new BigDecimal(product.price), orderItem.getAmount(), bigDecimal);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    // 根据产品编号获取对应产品
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

    // 获取所有产品的最终价格
    public float getAllProductsMoney(List<OrderItemCommand> orderItemCommandList, List<String> discountList) {
        if (orderItemCommandList == null) {
            return 0;
        }
        float result = 0.00f;
        for (OrderItemCommand orderItem : orderItemCommandList) {
            float productMoney = getSingleProductLastMoney(orderItem.getProduct(), orderItem.getAmount().intValue(), discountList);
            result += productMoney;
        }
        return result;
    }

    // 获取单个产品的最优价格
    public float getSingleProductLastMoney(String prdNo, int count, List<String> discountList) {
        float discountMoney = getDiscountMoney(prdNo, count, discountList);
        float promotionMoney = getSingleProductPromotion(prdNo, count);

        return discountMoney > promotionMoney ? promotionMoney : discountMoney;
    }


    // 获取单个产品的打折金额
    public float getDiscountMoney(String prdNo, int count, List<String> discountList) {
        Product product = getProduct(prdNo);
        float result = product.price * count;
        if (product.discountNo != 0) {
            for (String discount : discountList) {
                if (discount.equals("9折券") && product.discountNo == 1) {
                    product.discountValue = result * 0.10f;
                    return result * 0.90f;
                } else if (discount.equals("95折券") && product.discountNo == 2) {
                    product.discountValue = result * 0.05f;
                    return result * 0.95f;
                }
            }
        }
        return result;
    }


    // 获取单个产品的满减金额
    public float getSingleProductPromotion(String prdNo, int count) {
        Product product = getProduct(prdNo);
        float result = product.price * count;
        if (product.promotionNoList == null || product.promotionNoList.size() == 0) {
            return result;
        }
        float promotionMoney = result;
        for (Integer promotion : product.promotionNoList) {
            if (promotion == PromotionConstant.PROMOTION_FLAG3000) {
                float promotion3000 = result - 350;
                if (promotionMoney > promotion3000) {
                    promotionMoney = promotion3000;
                    product.finalPromotionFlag = PromotionConstant.PROMOTION_FLAG3000;
                    product.promotionValue = 350;
                }
            } else if (promotion == PromotionConstant.PROMOTION_FLAG2000) {
                if (result >= 2000) {
                    float promotion2000 = result - 30;
                    if (promotionMoney > promotion2000) {
                        promotionMoney = promotion2000;
                        product.finalPromotionFlag = PromotionConstant.PROMOTION_FLAG2000;
                        product.promotionValue = 30;
                    }
                }
            } else if (promotion == PromotionConstant.PROMOTION_FLAG1000) {
                if (result >= 1000) {
                    float promotion1000 = result - 10;
                    if (promotionMoney > promotion1000) {
                        promotionMoney = promotion1000;
                        product.finalPromotionFlag = PromotionConstant.PROMOTION_FLAG1000;
                        product.promotionValue = 10;
                    }
                }
            } else if (promotion == PromotionConstant.PROMOTION_FLAG3HALF) {
                if (count >= 3) {
                    float promotion3Half = result - product.price * 0.5f;
                    if (promotionMoney > promotion3Half) {
                        promotionMoney = promotion3Half;
                        product.finalPromotionFlag = PromotionConstant.PROMOTION_FLAG3HALF;
                        product.promotionValue = product.price * 0.5f;
                    }
                }
            } else if (promotion == PromotionConstant.PROMOTION_FLAG3SEND1) {
                if ( count >= 4) {
                    float promotion4 = result - product.price;
                    if (promotionMoney > promotion4) {
                        promotionMoney = promotion4;
                        product.finalPromotionFlag = PromotionConstant.PROMOTION_FLAG3SEND1;
                        product.promotionValue = product.price;
                    }
                }
            }
        }
        return promotionMoney;
    }

    // 获取优惠明细列表
    public List<DiscountItemRepresentation> getDiscountInfoList(List<OrderItemCommand> orderItemCommandList) {
        if (orderItemCommandList == null) {
            return null;
        }
        List<DiscountItemRepresentation> discountItemRepresentationList = new ArrayList<DiscountItemRepresentation>();
        for (OrderItemCommand orderItem : orderItemCommandList) {
            Product product = getProduct(orderItem.getProduct());
            if (product.discountNo == 0 && product.finalPromotionFlag == 0) {
                continue;
            }
            float disValue = 0.00f;
            if (product.promotionValue > product.discountValue) {
                disValue = product.promotionValue;
            } else {
                disValue = product.discountValue;
            }
            DiscountItemRepresentation discountDetail = new DiscountItemRepresentation(product.proNo, product.proName, new BigDecimal(disValue));
            discountItemRepresentationList.add(discountDetail);
        }
        return discountItemRepresentationList;
    }


}
