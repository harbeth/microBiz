package com.microBiz.controller.invoice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;


public class InvoiceController extends InvoiceSearchBaseController {
   
    public InvoiceController(){
        super();
  
    }
 
    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoiceList;
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("sales")){
            invoiceList = invoiceService.getInvoicesBySales((String)session.getAttribute("userName"));
        }else{
            invoiceList = invoiceService.getAll();
        }
        return invoiceList;
    }

    @Override
    public String getReturnedJsp() {
        return "invoice.jsp";
    }
}
