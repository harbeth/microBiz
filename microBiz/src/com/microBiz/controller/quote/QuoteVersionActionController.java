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
import com.microBiz.meta.QuoteVersionMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Item;
import com.microBiz.model.Orders;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;
import com.microBiz.service.QuoteVersionService;

public class QuoteVersionActionController extends BaseController{

    private QuoteService quoteService;
    private QuoteVersionService quoteVersionService;
    private InvoiceService invoiceService;

    private ProductService productService;

    
    public QuoteVersionActionController(){
        super();
        quoteService = new QuoteService();
        quoteVersionService = new QuoteVersionService();
        productService = new ProductService();
        invoiceService = new InvoiceService();

    }
    
    @Override
    public Navigation run() throws Exception {
        //must have quote version key, 

        System.out.println("quote version key is" + asKey("quoteVersionKey"));
        QuoteVersion qv = quoteVersionService.get(asKey("quoteVersionKey"));
        // get discount, taxRate, total from UI
        Orders orders = new Orders();
        BeanUtil.copy(request, orders);
        //  need reuse with createAction
        // assemble quote item
        String[] items = paramValues("items");
        String[] rates = paramValues("rates");
        String[] descs = paramValues("descs");
        String[] quantities = paramValues("qtys");
        // product should not be empty, remove -1 at the end
        int arrLength = items.length - 1;
        List<Item> qiList = new ArrayList<Item>();
        for ( int i = 0 ; i < arrLength; i ++ ) {
            // get product REF
            Product product = productService.get(Datastore.stringToKey(items[i]));
            Item qi = new Item();
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
        Key quoteVersionKey = null;
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
            invoiceService.convertQuoteToInvoice(invoice,orders,qiList);
            
        }else if ( saveOption.equals("saveAs") ) {
            quoteService.saveAsNewVersion(qv,orders, qiList);

        }else{
            quoteService.saveAsCurrentVersion(qv,orders,qiList);

        }
        return redirect("/quote/quoteDetails?quoteKey=" + Datastore.keyToString(quote.getKey()));
    }
}
