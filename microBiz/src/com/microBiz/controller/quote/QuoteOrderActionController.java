package com.microBiz.controller.quote;

import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.MicroBizUtil;
import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.QuoteService;


public class QuoteOrderActionController extends OrderLoadActionController {

    private QuoteService quoteService;
    private InvoiceService invoiceService;

    public QuoteOrderActionController(){
        super();
        quoteService = new QuoteService();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        QuoteOrder quoteOrder = quoteService.getQuoteOrder(asKey("quoteOrderKey"));
        // get order key, discount, taxRate, total from UI
        Order order = getOrderData();

        Quote quote = quoteOrder.getQuoteRef().getModel();
        // for save and saveAs
        //String actionName = asString("actionName");
        String saveOption = asString("saveOption");
        System.out.println("saveOption: " + saveOption);
        if ( saveOption.equals("convertToInvoice") ) {
            // for final, change status and create new invoice
            quote.setStatus("won");
            quoteService.save(quote);
            Invoice invoice = new Invoice();
            invoice.setAddress(quote.getAddress());
            invoice.setSignDate(new Date());
            invoice.getCustomerRef().setModel(quote.getCustomerRef().getModel());
            invoice.setInvoiceNumber(MicroBizUtil.generateInvoiceNumber());
            if(quote.getContactRef()!=null){
                invoice.getContactRef().setModel(quote.getContactRef().getModel());
            }
            invoiceService.convertQuoteToInvoice(invoice, order);
            
        }else if ( saveOption.equals("saveAs") ) {
            quoteService.saveAsNewVersion(quoteOrder, order);

        }else{
            quoteService.saveAsCurrentVersion(quoteOrder, order);

        }
        // get order list
        Quote quoteNew = quoteService.get(quote.getKey());
        //based on quote key, get data for edit page
        // get quote version list, the first is selected one, get quote item list
        // ?? some times
        List<QuoteOrder> quoteOrderList = quoteNew.getQuoteOrderRef().getModelList();
        System.out.println("get quote order list: " + quoteOrderList);
        // quote order key could be empty
        requestScope("quoteOrders", quoteOrderList);
        // only refresh the current tab
        return forward("quote-order-list.jsp");
    }
}
