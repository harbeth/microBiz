package com.microBiz;

import java.util.List;

import com.microBiz.model.OrderItem;

public class MicroBizCalculator {
    
    private MicroBizCalculator() {}
    
    private static Double getOrderItemTotal(Double rate, Double qty) {
        double ret = 0d;
        if ( rate != null && qty != null ) {
            ret = MicroBizUtil.roundTo2Demcial(rate*qty);
        }
        return ret;
    }
    
    public static Double getSubTotal(List<? extends OrderItem> orderItemList) {
        double ret = 0d;
        if ( orderItemList != null && orderItemList.size() > 0 ) {
            for ( OrderItem oi : orderItemList ) {
                // set order item total
                double total = getOrderItemTotal(oi.getRate(), oi.getQty());
                oi.setTotal(total);
                ret += total;
            }
            ret = MicroBizUtil.roundTo2Demcial(ret);
        }
        return ret;
    }
}
