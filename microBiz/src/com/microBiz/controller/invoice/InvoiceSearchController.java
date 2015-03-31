package com.microBiz.controller.invoice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;


public class InvoiceSearchController extends InvoiceSearchBaseController {

    public InvoiceSearchController(){
        super();
        
    }
    
 
    @Override
    public List<Invoice> getInvoices() {
        String searchNoStr = asString("searchInvByInvNo");
        String searchAddrStr = asString("searchInvByAddr");
        Integer status = asInteger("searchStatus");
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        
        if(searchNoStr==null && searchAddrStr==null){
            if(myRole.equals("sales")){
                if(status == null || status.intValue() == 0){
                    invoiceList = invoiceService.getInvoicesBySales((String)session.getAttribute("userName"));
                }else{
                    invoiceList = invoiceService.getInvoicesBySalesStatus((String)session.getAttribute("userName"),status);
                }
            }else{
                if(status == null || status.intValue() == 0){
                    invoiceList = invoiceService.getAll();
                }else{
                    invoiceList = invoiceService.getInvoicesByStatus(status);
                }
            }
            
        }else{
            if(searchNoStr!=null && !searchNoStr.trim().equals("")){
                if(myRole.equals("sales")){
                    if(status == null || status.intValue() == 0){
                        invoiceList = invoiceService.getInvoicesBySalesInvoiceNoStarts((String)session.getAttribute("userName"),searchNoStr);
                    }else{
                        invoiceList = invoiceService.getInvoicesBySalesStatusInvoiceNoStarts((String)session.getAttribute("userName"),searchNoStr,status);
                    }
                }else{
                    if(status == null || status.intValue() == 0){
                         invoiceList = invoiceService.searchInvoiceNoStartWith(searchNoStr);
                    }else{
                        invoiceList = invoiceService.searchStatusInvoiceNoStartWith(searchNoStr,status);
                    }
                }
            }else{
                if(myRole.equals("sales")){
                    if(status == null || status.intValue() == 0){
                        invoiceList = invoiceService.getInvoicesBySalesAddrStarts((String)session.getAttribute("userName"),searchAddrStr);
                    }else{
                        invoiceList = invoiceService.getInvoicesByStatusSalesAddrStarts((String)session.getAttribute("userName"),searchAddrStr,status);
                    }
                }else{
                    if(status == null || status.intValue() == 0){
                        invoiceList = invoiceService.searchInvoiceAddrStartWith(searchAddrStr);
                    }else{
                        invoiceList = invoiceService.searchInvoiceByStatusAddrStartWith(searchAddrStr,status); 
                    }
                }
            }
        }
        return invoiceList;
    }

    @Override
    public String getReturnedJsp() {
    
        return "invoice-list-div.jsp";
    }
}

