package com.coding.sales.cx.pay;

import com.coding.sales.cx.product.ProductAction;
import com.coding.sales.cx.user.User;
import com.coding.sales.cx.user.UserAction;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 支付类
 */
public class PayAction {


    public static OrderRepresentation pay(OrderCommand command, UserAction userAction, ProductAction productAction) {
        if (command == null || userAction == null || productAction == null) {
            return null;
        }
        // 获取应付金额
        float receivables = productAction.getAllProductsMoney(command.getItems(), command.getDiscounts());
        BigDecimal receivablesBD = new BigDecimal(receivables);
        // 获取订单明细列表
        List<OrderItemRepresentation> orderDetailList = productAction.getOrderDetailInfoList(command.getItems());
        // 获取原始金额
        float previousTotal = 0.00f;
        for (OrderItemRepresentation orderItemRepresentation : orderDetailList) {
            previousTotal += orderItemRepresentation.getSubTotal().floatValue();
        }
        BigDecimal preTotal = new BigDecimal(previousTotal);

        // 积分和等级
        User user = userAction.verifyUserInfo(command.getMemberId());
        String oldMemberType = user.userIntegral.cardType;
        user.userIntegral.findUserIntegral(receivables);

        // 优惠金额
        List<DiscountItemRepresentation> discountItemRepresentationList = productAction.getDiscountInfoList(command.getItems());
        float discountTotalF = 0.00f;
        for (DiscountItemRepresentation discountItemRepresentation : discountItemRepresentationList) {
            discountTotalF += discountItemRepresentation.getDiscount().floatValue();
        }
        BigDecimal discountTotal = new BigDecimal(discountTotalF);

        // 支付信息
        List<PaymentRepresentation> payments = new ArrayList<PaymentRepresentation>();
        PaymentRepresentation payment = new PaymentRepresentation("余额支付", receivablesBD);
        payments.add(payment);

        SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = s.parse(command.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        OrderRepresentation orderRepresentation = new OrderRepresentation(
                command.getOrderId(),
                date,
                command.getMemberId(),
                user.userName,
                oldMemberType,
                user.userIntegral.cardType,
                user.userIntegral.increaseIntegral,
                user.userIntegral.integeral,
                orderDetailList,
                preTotal,
                discountItemRepresentationList,
                discountTotal,
                receivablesBD,
                payments,
                command.getDiscounts()

        );

        return orderRepresentation;
    }
}
