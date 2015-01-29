package com.microBiz.controller.customer;

import com.microBiz.model.Customer;

//after save for edit, keep the same tab and update info DIV

public class CustomerEditActionController extends CustomerSaveActionController {

    public Customer getCustomer() {
        // no for new
        return customerService.get(asKey(metaCustomer.key));
    }
    
    public String getReturnJsp() {
        // should refresh whole page
        return "customer-detail-info.jsp";
    }
}
