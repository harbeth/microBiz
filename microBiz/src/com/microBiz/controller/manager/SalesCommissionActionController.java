package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceExpense;
import com.microBiz.model.InvoiceReport;
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
        
        for (int i=0; i < invoiceKeys.length; i++){
            System.out.println("index is " + i);
            System.out.println("key is " + invoiceKeys[i]);
            System.out.println("amount is " + amts[i]);
            System.out.println("note is " + notes[i]);
            if(!amts[i].equals("0")){
                Key invoiceKey = Datastore.stringToKey(invoiceKeys[i]);
                Invoice invoice = invoiceService.get(invoiceKey);
                Double amt = Double.valueOf(amts[i]);
                InvoiceReport ir = invoice.getInvoiceReportRef().getModel();
                invoiceService.saveInvoiceReport(ir);
                InvoiceExpense ie = new InvoiceExpense();
                ie.setExpense(amt);
                ie.setNote(notes[i]);
                ie.getInvoiceRef().setModel(invoice);
                ie.setForSalesCommission("on");
                ieService.save(ie);
            }
        }
        // error handling later
        String msg = "Save sales commission successfully";
        response.getWriter().print(msg);
        return null;
    }
}
