package com.microBiz.controller;

import org.slim3.controller.Navigation;

import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerDetailsController extends BaseController {

    private CustomerService s;
    private CustomerMeta metaP;

    public CustomerDetailsController() {
        super();
        s = new CustomerService();
        metaP = new CustomerMeta();

    }

    @Override
    public Navigation run() throws Exception {

        Customer p = null;

        p = s.get(asKey(metaP.key));

        requestScope("customer", p);
        requestScope("contacts", p.getContactListRef().getModelList());
        requestScope("quotes", p.getQuoteListRef().getModelList());
        return forward("customerDetails.jsp");

    }

}
