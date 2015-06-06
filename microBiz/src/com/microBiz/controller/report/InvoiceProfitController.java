package com.microBiz.controller.report;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;

public class InvoiceProfitController extends BaseController {

    private InvoiceService invoiceService;
    
    public InvoiceProfitController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        String signDateFromStr = asString("signDateFrom");
        String signDateToStr = asString("signDateTo");
        String fromClear = asString("onlyList");
        boolean validDate = true;
        Date from = MicroBizUtil.parseStrToDate(signDateFromStr);
        Date to = MicroBizUtil.parseStrToDate(signDateToStr);
        if(from ==null || to == null || from.after(to) ){
            to = new Date();
            Calendar c = Calendar.getInstance();    
            c.setTime(to);
            c.add(Calendar.DATE, -365);
            from = c.getTime();
            signDateFromStr = MicroBizUtil.parseDateToStr(from);
            signDateToStr = MicroBizUtil.parseDateToStr(to);
            
            
        }
        List<Invoice> closedInvoices = invoiceService.getClosedInvoicesBySignDateRange(from,to);
        double revenue = 0;
        double materialCost = 0;
        double laborCost = 0;
        double otherCost = 0;
        Iterator<Invoice> i = closedInvoices.iterator();
        while (i.hasNext()){
            Invoice inv = (Invoice)i.next();
            InvoiceReport ir = inv.getInvoiceReportRef().getModel();
            revenue = revenue + ir.getTotal();
            materialCost = materialCost + ir.getMaterialCost();
            laborCost = laborCost + ir.getLabourCost();
            otherCost = otherCost + ir.getOtherExpense();
            
            
        }
        double margin = (revenue-materialCost-laborCost-otherCost)/revenue;
        requestScope("invoices", closedInvoices);
        requestScope("signDateFrom", signDateFromStr);
        requestScope("signDateTo", signDateToStr);
        requestScope("revenue", revenue);
        requestScope("materialCost", materialCost);
        requestScope("laborCost", laborCost);
        requestScope("otherCost", otherCost);
        requestScope("margin", MicroBizUtil.roundTo2Demcial(margin)*100);
        
        if(fromClear!=null && fromClear.equals("true")){
            return forward("invoice-profit-list-div.jsp");
        }else{
            return forward("invoice-profit.jsp");
        }
    }
}

