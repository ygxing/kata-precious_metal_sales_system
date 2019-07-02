package com.coding.sales.cx.promotion;


/**
 * 满减（仅对满减商品使用）:
 * 每满3000元减350
 * 每满2000元减30
 * 每满1000元减10
 * 第3件半价（买3件及以上，其中1件半价）
 * 满3送1（买4件及以上，其中1件免费）
 */
public class PromotionConstant {

    // 每满3000元减350
    public static final int PROMOTION_FLAG3000 = 1;

    // 每满2000元减30
    public static final int PROMOTION_FLAG2000 = 2;

    // 每满1000元减10
    public static final int PROMOTION_FLAG1000 = 3;

    // 第3件半价（买3件及以上，其中1件半价）
    public static final int PROMOTION_FLAG3HALF = 4;

    // 满3送1（买4件及以上，其中1件免费）
    public  static final int PROMOTION_FLAG3SEND1 = 5;



}
