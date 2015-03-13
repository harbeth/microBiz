package com.microBiz.controller.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceExpense;
import com.microBiz.model.SalesCommissionRecord;
import com.microBiz.service.InvoiceExpenseService;
import com.microBiz.service.InvoiceService;

// no use any more, remove later
public class SalesCommissionActionController extends BaseController {

    private InvoiceService invoiceService;
    private InvoiceExpenseService ieService;
     
    public SalesCommissionActionController(){
        super();
        invoiceService = new InvoiceService();
        ieService = new InvoiceExpenseService();
       
    }
    
    @Override
    public Navigation run() throws Exception {
        
       
        String[] invoiceKeys = paramValues("invoiceKey");
        String[] amts = paramValues("amt");
        String[] notes = paramValues("note");
        List<SalesCommissionRecord> result = new ArrayList();
        String salesName = null;
        
        
        
        for (int i=0; i < invoiceKeys.length; i++){
            System.out.println("index is " + i);
            System.out.println("key is " + invoiceKeys[i]);
            System.out.println("amount is " + amts[i]);
            System.out.println("note is " + notes[i]);
            if(!amts[i].equals("0")){
                Key invoiceKey = Datastore.stringToKey(invoiceKeys[i]);
                Invoice invoice = invoiceService.get(invoiceKey);
                if(salesName==null){
                    salesName = invoice.getSales();
                }
                Double amt = Double.valueOf(amts[i]);
                InvoiceExpense ie = new InvoiceExpense();
                ie.setExpense(amt);
                ie.setNote(notes[i]);
                ie.getInvoiceRef().setModel(invoice);
                ie.setForSalesCommission("on");
                ieService.save(ie);
                SalesCommissionRecord s = new SalesCommissionRecord();
                s.setAddress(invoice.getAddress());
                s.setInvoiceNumber(invoice.getInvoiceNumber());
                s.setClosedDate(invoice.getStatusChangeDateStr());
                s.setSignedDate(invoice.getSignDateStr());
                s.setNote(notes[i]);
                s.setAmount(amt.toString());
                result.add(s);
            }
        }
        requestScope("salesName", salesName);
        requestScope("salesCommissionRecords", result);
        requestScope("createdDate", MicroBizUtil.parseDateToStr(new Date()));
        return forward("sales-commission-record.jsp");
    }
}
