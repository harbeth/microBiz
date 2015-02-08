package com.microBiz.controller.invoice;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.service.InvoiceService;

// on invoice edit link click, load job tab first
public class InvoiceDetailsController extends OrderLoadActionController {
    
    private InvoiceService invoiceService;
    
    public InvoiceDetailsController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        // get invoice key to get invoice order list
        System.out.println("get invoiceKey " + asString("invoiceKey")) ;
        requestScope("invoice", invoice); 
        
        Order invoiceOrder = invoice.getInvoiceOrderRef().getModelList().get(0).getOrderRef().getModel();
        setOrderData(invoiceOrder);
        return forward(getReturnJsp());
    }

    public String getReturnJsp() {
        return "invoice-details.jsp";
    }
}
