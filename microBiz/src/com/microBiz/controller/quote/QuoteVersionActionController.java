package com.microBiz.controller.quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizCalculator;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Order;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;


public class QuoteVersionActionController extends BaseController{

    private QuoteService quoteService;
    private InvoiceService invoiceService;

    private ProductService productService;

    
    public QuoteVersionActionController(){
        super();
        quoteService = new QuoteService();
        productService = new ProductService();
        invoiceService = new InvoiceService();

    }
    
    @Override
    public Navigation run() throws Exception {
        //must have quote order key, 

        
        QuoteOrder qv = quoteService.getQuoteOrder(asKey("quoteOrderKey"));
        // get discount, taxRate, total from UI
        Order order = new Order();
        BeanUtil.copy(request, order);
        //  need reuse with createAction
        // assemble quote item
        String[] items = paramValues("items");
        String[] rates = paramValues("rates");
        String[] descs = paramValues("descs");
        String[] quantities = paramValues("qtys");
        // product should not be empty, remove -1 at the end
        int arrLength = items.length - 1;
        List<OrderItem> qiList = new ArrayList<OrderItem>();
        for ( int i = 0 ; i < arrLength; i ++ ) {
            // get product REF
            Product product = productService.get(Datastore.stringToKey(items[i]));
            OrderItem qi = new OrderItem();
            qi.getProductRef().setModel(product);
            qi.setDesc(descs[i]);
            qi.setRate(Double.valueOf(rates[i]));
            qi.setQty(Double.valueOf(quantities[i]));
            qiList.add(qi);
        }

        Quote quote = qv.getQuoteRef().getModel();
        // for save and saveAs
        //String actionName = asString("actionName");
        String saveOption = asString("saveOption");
        System.out.println("saveOption: " + saveOption);
        Key quoteOrderKey = null;
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
            invoiceService.convertQuoteToInvoice(invoice,order,qiList);
            
        }else if ( saveOption.equals("saveAs") ) {
            quoteService.saveAsNewVersion(qv,order, qiList);

        }else{
            quoteService.saveAsCurrentVersion(qv,order,qiList);

        }
        return redirect("/quote/quoteDetails?quoteKey=" + Datastore.keyToString(quote.getKey()));
    }
}
