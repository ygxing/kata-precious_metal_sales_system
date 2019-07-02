package com.coding.sales.cx.user;

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
        tUser.userIntegral = new UserIntegral(9860);
        userList.add(tUser);

        User tUser1 = new User();
        tUser1.userName = "王立";
        tUser1.cardNo = "6630009999";
        tUser1.userIntegral = new UserIntegral(48860);
        userList.add(tUser1);

        User tUser2 = new User();
        tUser2.userName = "李想";
        tUser2.cardNo = "8230009999";
        tUser2.userIntegral = new UserIntegral(98860);
        userList.add(tUser2);

        User tUser3 = new User();
        tUser3.userName = "张三";
        tUser3.cardNo = "9230009999";
        tUser3.userIntegral = new UserIntegral(198860);
        userList.add(tUser3);
    }

    public User verifyUserInfo(String cardNo) {
        for (User o : userList) {
            if(o.cardNo.equals(cardNo))
                return o;
        }
        return null;
    }



}
