package com.microBiz.controller.common;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Payment;
import com.microBiz.model.MiLog;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;
import com.microBiz.service.QuoteService;

// from the link to refresh whole page

public class LogEventController extends BaseController {

    private QuoteService quoteService;
    private InvoiceService invoiceService;
    
    public LogEventController(){
        super();
        quoteService = new QuoteService();
        invoiceService = new InvoiceService();
    }

    @Override
    public Navigation run() throws Exception {
        Key invoiceKey = asKey("invoiceKey");
        Key quoteKey = asKey("quoteKey");
        if(invoiceKey!=null){
            if(forAction()){
                MiLog milog = new MiLog();
                milog.setNote(asString("note"));
                invoiceService.saveLogEvent(milog,invoiceKey);
            }
            
            List<MiLog> miLogs = invoiceService.getMiLogs(invoiceKey);
            requestScope("miLogs", miLogs);
            requestScope("invoiceKey", asString("invoiceKey"));
            
        }
        
        if(quoteKey!=null){
            if(forAction()){
                MiLog milog = new MiLog();
                milog.setNote(asString("note"));
                quoteService.saveLogEvent(milog,quoteKey);
            }
            
            List<MiLog> miLogs = quoteService.getMiLogs(quoteKey);
            requestScope("miLogs", miLogs);
            requestScope("quoteKey", asString("quoteKey"));
            
        }
        
  
        return forward("milog.jsp");
    }
    
    protected boolean forAction(){
        return false;
    }
    
    
    
 
}
