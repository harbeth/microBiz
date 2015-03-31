package com.microBiz.controller.invoice;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.controller.common.PaginatingController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;


public abstract class InvoiceSearchBaseController extends PaginatingController {

    // for customer drop down change
    protected InvoiceService invoiceService;
    
    public InvoiceSearchBaseController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        List<Invoice> invoiceList = getInvoices();
        int counts =  invoiceList.size();
        setPaginatingValue(counts);
        Integer pageNo = asInteger("pageNo");
        Integer start;
        Integer end;
        if(counts<=MicroBizConst.ENTRIES_PER_PAGE){
            requestScope("invoices", invoiceList);
 
        }else{
            
            if(pageNo == null){
                pageNo = new Integer(1);
            }
            start = (pageNo-1)*MicroBizConst.ENTRIES_PER_PAGE;
            end = pageNo*MicroBizConst.ENTRIES_PER_PAGE;
            if(end>counts){
                end = counts;
            }
            
            requestScope("invoices", invoiceList.subList(start.intValue(), end.intValue()));
 
        }
        requestScope("invoiceStatus",invoiceStatus);
        return forward(getReturnedJsp());
    }
    
    public abstract List<Invoice> getInvoices();
    public abstract String getReturnedJsp();
    
}

