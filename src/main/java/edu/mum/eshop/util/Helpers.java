package edu.mum.eshop.util;

import edu.mum.eshop.domain.order.OrderStatus;

public class Helpers {
    public static String beautifyCardNumber(String number){
        if (number.length() != 16) return number;

        return  number.substring(0, 3) + " " +number.substring(4, 7) + " " +number.substring(8, 11) + " " + number.substring(12, 15);
    }

    public static String getOrderStatusText(OrderStatus status) {
        switch (status){
            case PLACED:
                return "Order placed";
            case SHIPPED:
                return "Item shipped";
            case CANCELLED:
                return "Order cancelled";
            case DELIVERED:
                return "Delivered";
            case RETURNED:
                return "Item returned";
        }
        return status.name();
    }
}
