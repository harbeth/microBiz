package com.microBiz.controller.invoice;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

public class InvoiceValidateController extends Controller {

    protected InvoiceService invoiceService;
    // for customer drop down move to super class
    
    public InvoiceValidateController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
    	
        // list at the top, details panel hidden first
        String invoiceNumber = asString("invoiceNumber");
        List<Invoice> returnList = invoiceService.getByInvoiceNumber(invoiceNumber);
        if ( returnList == null || returnList.size() == 0 ) {
            response.getWriter().write("success");
        }else{
            response.getWriter().write("The invoice number " + invoiceNumber + " already exists, please input another one.");
        }
        return null;
    }
}
