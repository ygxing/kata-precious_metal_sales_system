package com.coding.sales.cx.pay;

import com.coding.sales.cx.product.OrderDetail;
import com.coding.sales.cx.product.ProductAction;
import com.coding.sales.cx.user.User;
import com.coding.sales.cx.user.UserAction;
import com.coding.sales.cx.user.UserIntegral;
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

public class PayAction {


    public static OrderRepresentation pay(OrderCommand command, UserAction userAction, ProductAction productAction) {
        if (command == null || userAction == null || productAction == null) {
            return null;
        }
        float receivables = productAction.getAllProductsMoney(command.getItems(), command.getDiscounts());
        BigDecimal receivablesBD = new BigDecimal(receivables);
        List<OrderItemRepresentation> orderDetailList = productAction.getOrderDetailInfo(command.getItems());
        float previousTotal = 0.00f;
        for (OrderItemRepresentation orderItemRepresentation : orderDetailList) {
            previousTotal += orderItemRepresentation.getSubTotal().floatValue();
        }
        BigDecimal preTotal = new BigDecimal(previousTotal);

        float resultMoney = productAction.getAllProductsMoney(command.getItems(), command.getDiscounts());
        User user = userAction.verifyUserInfo(command.getMemberId());
        String oldMemberType = user.userIntegral.cardType;
        user.userIntegral.finalUserIntegral(resultMoney);

        List<DiscountItemRepresentation> discountItemRepresentationList = productAction.getDiscountInfoList(command.getItems());
        float discountTotalF = 0.00f;
        for (DiscountItemRepresentation discountItemRepresentation : discountItemRepresentationList) {
            discountTotalF += discountItemRepresentation.getDiscount().floatValue();
        }
        BigDecimal discountTotal = new BigDecimal(discountTotalF);

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
