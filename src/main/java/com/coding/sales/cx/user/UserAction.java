package com.coding.sales.cx.user;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;


public class UserAction {
    private List<User> userList = new ArrayList<User>();
    public UserAction() {
        initUser();
    }

    public void initUser() {
        User tUser = new User();

        tUser.userName = "马丁";
        tUser.cardNo = "6236609999";
        tUser.cardType = "普卡";
        tUser.integrarl = "9860";
        userList.add(tUser);

        tUser.userName = "王立";
        tUser.cardNo = "6630009999";
        tUser.cardType = "金卡";
        tUser.integrarl = "48860";
        userList.add(tUser);

        tUser.userName = "李想";
        tUser.cardNo = "8230009999";
        tUser.cardType = "白金卡";
        tUser.integrarl = "98860";
        userList.add(tUser);

        tUser.userName = "张三";
        tUser.cardNo = "9230009999";
        tUser.cardType = "钻石卡";
        tUser.integrarl = "198860";
        userList.add(tUser);
    }

    public User  verifyUserInfo(String custoMername, String cardNo)
    {
        for (User o : userList)
        {
            if(o.userName.equals(custoMername) && o.cardNo.equals(cardNo))
                return o;
        }
        return null;
    }
}
