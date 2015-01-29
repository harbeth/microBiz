package com.microBiz.controller.customer;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;

public class CustomerInvoiceController extends BaseController{

    private CustomerService customerService;
    private InvoiceService invoiceService;
    
    public CustomerInvoiceController(){
        super();
        customerService = new CustomerService();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // list at the top, details panel hidden first
        Customer customer = customerService.get(asKey("customerKey"));  
        BeanUtil.copy(customer, request);
        //List<Quote> quotes = invoiceService.getCustomerInvoice(customer.getKey());
        // get data for invoice tab
        List<Invoice> invoices = customer.getInvoiceListRef().getModelList();
        requestScope("invoices", invoices);
        
        requestScope("cxTypes", cxTypes);
        
        return forward("customer-invoices.jsp");
    }
}
