package com.microBiz.controller.common;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.MiUserService;

public abstract class InvoiceEditCommonController extends CustomerContactLoadController {

    protected InvoiceService invoiceService;
    // for customer drop down move to super class
    protected MiUserService userService;
    public InvoiceEditCommonController(){
        super();
        invoiceService = new InvoiceService();
        userService = new MiUserService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // list at the top, details panel hidden first
        Invoice invoice = invoiceService.get(asKey("invoiceKey")); 
        //InvoiceReport ir = invoice.getInvoiceReportRef().getModel(); 
        
        BeanUtil.copy(invoice, request);
        Customer customer = invoice.getCustomerRef().getModel();
        Contact contact = invoice.getContactRef().getModel();
        // show contact DIV flag, move to super class
        setCustomerContactData(customer, contact);
        requestScope("salesNames", userService.getSalesNames());
        requestScope("paymentTypes", paymentTypes);
        requestScope("invoiceStatus", invoiceStatus);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
}
