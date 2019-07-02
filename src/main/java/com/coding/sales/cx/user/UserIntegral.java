package com.coding.sales.cx.user;

/*
* 普卡：1倍基准积分，累计积分1万以下
金卡：1.5倍基准积分，累计积分1万（含）-5万（不含）
白金卡：1.8倍基准积分，累计积分5万（含）-10万（不含）
钻石卡：2倍基准积分，累计积分10万以上*/
public class UserIntegral {
    public String cardType;
    public int integeral;
    public UserIntegral(int tIntegeral) {
        integeral = tIntegeral;
        if(integeral <10000)
        {
            cardType="普卡";
        }
        else if (integeral >=10000 && integeral <50000)
        {
            cardType="金卡";
        }
        else if (integeral >=50000 && integeral <100000)
        {
            cardType="白金卡";
        }
        else if (integeral>=10000)
        {
            cardType="钻石卡";
        }
    }
    public UserIntegral finalUserIntegral(UserIntegral userIntegral,float iMoney)
    {
        float increaseIntegral = 0;
        if(userIntegral.integeral <10000)
        {
            increaseIntegral = iMoney;
        }
        else if (userIntegral.integeral >=10000 && userIntegral.integeral <50000)
        {
            increaseIntegral = iMoney * 1.5f;
        }
        else if (userIntegral.integeral >=50000 && userIntegral.integeral <100000)
        {
            increaseIntegral = iMoney * 1.8f;
        }
        else if (userIntegral.integeral>=10000)
        {
            increaseIntegral = iMoney * 2f;
        }

        UserIntegral fUserIntegral = new UserIntegral(userIntegral.integeral + (int)increaseIntegral);
        return fUserIntegral;
    }
}
