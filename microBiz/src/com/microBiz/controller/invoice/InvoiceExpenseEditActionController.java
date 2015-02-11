package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.InvoiceExpenseMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceExpense;
import com.microBiz.service.InvoiceExpenseService;
import com.microBiz.service.InvoiceService;


// from AJAX call, on the submit form of jab details form
public class InvoiceExpenseEditActionController extends BaseController{

    private InvoiceExpenseService expenseService;
    private InvoiceService invoiceService;
    private InvoiceExpenseMeta metaExpense;
    
    public InvoiceExpenseEditActionController(){
        super();
        expenseService = new InvoiceExpenseService();
        invoiceService = new InvoiceService();
        metaExpense = new InvoiceExpenseMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        InvoiceExpense expense = new InvoiceExpense();
        if ( asKey(metaExpense.key) != null ) {
            expense = expenseService.get(asKey(metaExpense.key));
        }else{
            expense = new InvoiceExpense();
        }

        BeanUtil.copy(request, expense);
        Key invoiceKey = Datastore.stringToKey(expense.getInvoiceKey());
        Invoice invoice = invoiceService.get(invoiceKey);
        expense.setReportDate();
        expense.getInvoiceRef().setModel(invoice);
        expenseService.save(expense);
        
        invoice = invoiceService.get(invoiceKey);
        requestScope("expenses", invoice.getExpenseListRef().getModelList()); 
        requestScope("invoice", invoice); 
        // whole page refresh
        return forward("invoice-expenses.jsp");
    }
}
