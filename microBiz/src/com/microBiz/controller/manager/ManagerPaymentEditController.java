package com.microBiz.controller.manager;

import com.microBiz.controller.common.PaymentEditController;

// from the link to refresh whole page

public class ManagerPaymentEditController extends PaymentEditController {

    public String getReturnJsp(){
        return "manager-payment-edit.jsp";
    }
}
