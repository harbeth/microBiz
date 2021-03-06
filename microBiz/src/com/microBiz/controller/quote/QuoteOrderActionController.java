package com.microBiz.controller.quote;

import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;
import com.microBiz.PropertyHelper;
import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.MiLog;
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
        String invoiceKeyStr = null;
        boolean toInvoice = false;
        if ( saveOption.equals("convertToInvoice") ) {
            // for final, change status and create new invoice
            quote.setStatus(MicroBizConst.CODE_STATUS_WON);
            quoteService.save(quote);
            Invoice invoice = new Invoice();
            invoice.setAddress(quote.getAddress());
            invoice.setSignDate(new Date());
            String userName = (String)request.getSession().getAttribute("userName");
            invoice.setSales(userName);
            invoice.getCustomerRef().setModel(quote.getCustomerRef().getModel());
            invoice.setInvoiceNumber(MicroBizUtil.generateInvoiceNumber());
            invoice.setNote(quote.getNote());
            invoice.setSignDate(new Date());
            if(quote.getContactRef()!=null){
                invoice.getContactRef().setModel(quote.getContactRef().getModel());
            }
            Key orderKey = orderService.updateOrderAsNew(order);
            invoice.getOrderRef().setKey(orderKey);
            invoiceService.save(invoice);
            invoiceKeyStr = Datastore.keyToString(invoice.getKey());
            
            MiLog milog1 = new MiLog();
            milog1.setNote("[sys] quote version  " + quoteOrder.getName() + " was converted to invoice");
            quoteService.saveLogEvent(milog1,quote.getKey());
            
            MiLog milog2 = new MiLog();
            milog2.setNote("[sys] converted to invoice from quote version " + quoteOrder.getName());
            invoiceService.saveLogEvent(milog2,invoice.getKey());
            
            toInvoice = true;
            //return redirect("/invoice/invoiceDetails?invoiceKey=" + Datastore.keyToString(invoice.getKey()));
        }else if ( saveOption.equals("save") ) {
            //save as current version, keep same version number
            Key orderKey = orderService.updateOrder(order);
            quoteOrder.getOrderRef().setKey(orderKey);
            quoteService.saveQuoteOrder(quoteOrder);

        }else{// save as new version
            Key orderKey = orderService.updateOrderAsNew(order);
            
            int quoteVersionCount = quote.getCount();
            quoteVersionCount ++ ;
            quote.setCount(quoteVersionCount);
            quoteService.save(quote);
            QuoteOrder qo = new QuoteOrder();
 
            qo.setName(MicroBizUtil.getQuoteVersionName(quoteVersionCount));

            qo.getOrderRef().setKey(orderKey);
            qo.getQuoteRef().setModel(quote);
            
            quoteService.saveQuoteOrder(qo);
        }
        
        if ( toInvoice ) {
            response.getWriter().write(invoiceKeyStr);
            // error handling, return "error:Cannot convert quotation to invoice: "
            return null;
        }else{
            // get order list
            Quote quoteNew = quoteService.get(quote.getKey());
            //based on quote key, get data for edit page
            // get quote version list, the first is selected one, get quote item list
            // ?? some times
            List<QuoteOrder> quoteOrderList = quoteNew.getQuoteOrderRef().getModelList();
            System.out.println("get quote order list: " + quoteOrderList);
            // quote order key could be empty
            requestScope("quoteOrders", quoteOrderList);
            
            // get first quote version  quoteOrderList.size() should > 0
            setOrderFromList(quoteOrderList);
            
            // only refresh the current tab
            return forward("quote-order-list.jsp");
        }
    }
}
