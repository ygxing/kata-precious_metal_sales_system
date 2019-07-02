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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayAction {


    public static OrderRepresentation pay(OrderCommand command, UserAction userAction, ProductAction productAction) {
        if (command == null || userAction == null || productAction == null) {
            return null;
        }
        float receivables = productAction.getAllProductsMoney(command.getItems(), command.getDiscounts());
        List<OrderItemRepresentation> orderDetailList = productAction.getOrderDetailInfo(command.getItems());
        BigDecimal preTotal = new BigDecimal(0);
        for (OrderItemRepresentation orderItemRepresentation : orderDetailList) {
            preTotal.add(orderItemRepresentation.getSubTotal());
        }

        float resultMoney = productAction.getAllProductsMoney(command.getItems(), command.getDiscounts());
        User user = userAction.verifyUserInfo(command.getMemberId());
        String oldMemberType = user.userIntegral.cardType;
        user.userIntegral.finalUserIntegral(resultMoney);

        List<DiscountItemRepresentation> discountItemRepresentationList = productAction.getDiscountInfoList(command.getItems());
        BigDecimal discountTotal = new BigDecimal(0);
        for (DiscountItemRepresentation discountItemRepresentation : discountItemRepresentationList) {
            discountTotal.add(discountItemRepresentation.getDiscount());
        }

        List<PaymentRepresentation> payments = new ArrayList<PaymentRepresentation>();
        PaymentRepresentation payment = new PaymentRepresentation("余额支付", new BigDecimal(receivables));
        payments.add(payment);

        OrderRepresentation orderRepresentation = new OrderRepresentation(
                command.getOrderId(),
                new Date(command.getCreateTime()),
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
                new BigDecimal(receivables),
                payments,
                command.getDiscounts()

        );

        return orderRepresentation;
    }
}
