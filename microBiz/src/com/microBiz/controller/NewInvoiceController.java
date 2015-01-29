package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.CustomerContactLoadController;
import com.microBiz.model.Invoice;

public class NewInvoiceController extends CustomerContactLoadController {

    // for customer drop down, put it super class
    
    public NewInvoiceController(){
        super();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // call directly from new Invoice from customer
        
        // list at the top, details panel hidden first
        Invoice invoice = new Invoice();
        BeanUtil.copy(invoice, request);
        // get all customer move to super class
        // show contact DIV flag
        setCustomerContactData(null, null);
        return forward("invoice/invoice-new-wrapper.jsp");
    }
}
