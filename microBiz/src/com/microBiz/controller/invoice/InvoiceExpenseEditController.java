package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.InvoiceExpense;
import com.microBiz.service.InvoiceExpenseService;

// from the link to refresh whole page

public class InvoiceExpenseEditController extends BaseController{

    private InvoiceExpenseService expenseService;
    
    public InvoiceExpenseEditController(){
        super();
        expenseService = new InvoiceExpenseService();
    }
    
    // new expense, create new job or edit job
    @Override
    public Navigation run() throws Exception {
        String invoiceKey = asString("invoiceKey");
        String expenseKey = asString("expenseKey");
        InvoiceExpense expense = null;
        if ( "-1".equals(expenseKey) ) {
            // new
            expense = new InvoiceExpense();
        }else{
            // edit
            expense = expenseService.get(asKey("expenseKey"));
        }
        expense.setInvoiceKey(invoiceKey);
        BeanUtil.copy(expense, request);
        return forward("invoice-expense-edit.jsp");
    }
}
