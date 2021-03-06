package com.microBiz.controller.invoice;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.PropertyHelper;
import com.microBiz.controller.common.CustomerContactLoadController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.ProductService;

public class InvoiceCreateController extends CustomerContactLoadController {

    // for customer drop down, put it super class
    //private MiUserService userService;
    protected ProductService productService;

    public InvoiceCreateController(){
        super();
        //userService = new MiUserService();
        productService = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Invoice invoice = new Invoice();

        String forwardJsp = "";
        Customer c = null;
        if(asKey("customerkey")!=null){// from customer details page
            c= customerService.get(asKey("customerkey"));
            if(!c.isCommercial() && c.getAddress()!=null){//for residential cx, copy address
                invoice.setAddress(c.getAddress());
            }
            invoice.getCustomerRef().setModel(c);
            forwardJsp = "invoice-new-wrapper.jsp";
        }else{
            forwardJsp = "invoice-new.jsp";
        }
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("sales")){
            invoice.setSales((String)session.getAttribute("userName"));
        }
        // get all customer move to super class
        // show contact DIV flag
        BeanUtil.copy(invoice, request);
        requestScope("txRates", txRates);
        requestScope("salesNames", PropertyHelper.getInstance().getSales());
        requestScope("products", productService.getSellingPrds());
        requestScope("paymentTypes", paymentTypes);
        requestScope("invoiceStatus", invoiceStatus);
        setCustomerContactData(c, null);
        return forward(forwardJsp);
    }
}
