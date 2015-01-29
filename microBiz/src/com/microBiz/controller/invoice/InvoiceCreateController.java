package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.CustomerContactLoadController;
import com.microBiz.model.Invoice;
import com.microBiz.service.MiUserService;

public class InvoiceCreateController extends CustomerContactLoadController {

    // for customer drop down, put it super class
    private MiUserService userService;
    public InvoiceCreateController(){
        super();
        userService = new MiUserService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        
        // list at the top, details panel hidden first
        Invoice invoice = new Invoice();
        BeanUtil.copy(invoice, request);
        // get all customer move to super class
        // show contact DIV flag
        requestScope("sales", userService.getSales());
        requestScope("paymentTypes", paymentTypes);
        setCustomerContactData(null, null);
        return forward("invoice-new.jsp");
    }
}
