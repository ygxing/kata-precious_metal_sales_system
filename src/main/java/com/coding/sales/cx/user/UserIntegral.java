package com.coding.sales.cx.user;

/**
 * 普卡：1倍基准积分，累计积分1万以下
 * 金卡：1.5倍基准积分，累计积分1万（含）-5万（不含）
 * 白金卡：1.8倍基准积分，累计积分5万（含）-10万（不含）
 * 钻石卡：2倍基准积分，累计积分10万以上
 */
public class UserIntegral {

    public String cardType;
    public int integeral;
    public int increaseIntegral;

    public UserIntegral(int tIntegeral) {
        integeral = tIntegeral;
        updateIntegral();
    }

    // 根据积分更新客户级别
    private void updateIntegral() {
        if (integeral < 10000) {
            cardType = "普卡";
        } else if (integeral >= 10000 && integeral < 50000) {
            cardType = "金卡";
        } else if (integeral >= 50000 && integeral < 100000) {
            cardType = "白金卡";
        } else if (integeral >= 10000) {
            cardType = "钻石卡";
        }
    }

    // 根据消费金额更新客户级别
    public void findUserIntegral(float iMoney) {
        if (integeral < 10000) {
            increaseIntegral = (int) iMoney;
        } else if (integeral >= 10000 && integeral < 50000) {
            increaseIntegral = (int) (iMoney * 1.5f);
        } else if (integeral >= 50000 && integeral < 100000) {
            increaseIntegral = (int) (iMoney * 1.8f);
        } else if (integeral >= 10000) {
            increaseIntegral = (int) (iMoney * 2f);
        }

        integeral += increaseIntegral;
        updateIntegral();
    }
}
