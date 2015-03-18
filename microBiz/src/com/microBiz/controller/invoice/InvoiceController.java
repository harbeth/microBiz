package com.microBiz.controller.invoice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;


public class InvoiceController extends BaseController {

    private InvoiceService invoiceService;
    
    public InvoiceController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        List<Invoice> invoiceList;
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("sales")){
            invoiceList = invoiceService.getInvoicesBySales((String)session.getAttribute("userName"));
        }else{
            invoiceList = invoiceService.getAll();
        }
        requestScope("invoices", invoiceList);
        return forward("invoice.jsp");
    }
}
