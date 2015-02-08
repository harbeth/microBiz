package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.common.CustomerContactLoadController;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.MiUserService;

public class InvoiceEditController extends CustomerContactLoadController {

    private InvoiceService invoiceService;
    // for customer drop down move to super class
    private MiUserService userService;
    public InvoiceEditController(){
        super();
        invoiceService = new InvoiceService();
        userService = new MiUserService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // list at the top, details panel hidden first
        Invoice invoice = invoiceService.get(asKey("invoiceKey")); 
        BeanUtil.copy(invoice, request);
        Customer customer = invoice.getCustomerRef().getModel();
        Contact contact = invoice.getContactRef().getModel();
        // show contact DIV flag, move to super class
        setCustomerContactData(customer, contact);
        requestScope("sales", userService.getSales());
        requestScope("paymentTypes", paymentTypes);
        return forward("invoice-edit.jsp");
    }
}
