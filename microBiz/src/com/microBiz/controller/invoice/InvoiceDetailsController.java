package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
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
        String myRole = (String)request.getSession().getAttribute("myrole");
        boolean isSales = false;
        if(myRole.equals("sales")){
            isSales = true;
        }
        // get invoice key to get invoice order list
        System.out.println("get invoiceKey " + asString("invoiceKey")) ;
        requestScope("invoice", invoice); 
        requestScope("isSales", isSales); 
        
        Order invoiceOrder = invoice.getOrderRef().getModel();
        setOrderData(invoiceOrder);
        return forward(getReturnJsp());
    }

    public String getReturnJsp() {
        return "invoice-details.jsp";
    }
}
