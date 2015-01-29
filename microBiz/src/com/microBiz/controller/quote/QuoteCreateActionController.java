package com.microBiz.controller.quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteItem;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.MicroBizService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;

// command controller for new/edit save
public class QuoteCreateActionController extends BaseController{

    private QuoteService quoteService;
    private CustomerService customerService;
    private ContactService contactService;
    private ProductService productService;
    private MicroBizService microBizService;
    
    public QuoteCreateActionController(){
        super();
        quoteService = new QuoteService();
        customerService = new CustomerService();
        contactService = new ContactService();
        productService = new ProductService();
        microBizService = new MicroBizService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Quote quote = new Quote();
        BeanUtil.copy(request, quote); 
        // for new, not from UI
        quote.setCount(1);
        
        if(asString("active")==null){
           // i.setActive("");
        }
        // set customer reference if has selection
        String customerKey = quote.getCustomerKey();
        if ( !"-1".equals(customerKey) ) { 
            quote.getCustomerRef().setModel(customerService
                          .get(Datastore.stringToKey(customerKey)));
        }
        // set contact reference if has selection
        String contactKey = quote.getContactKey();
        if ( !"-1".equals(contactKey) ) { 
            quote.getContactRef().setModel(contactService
                           .get(Datastore.stringToKey(quote.getContactKey())));
        }
        
        // assemble quote item
        // remove -1 for product
        String[] items = paramValues("items");
        String[] rates = paramValues("rates");
        String[] descs = paramValues("descs");
        String[] quantities = paramValues("qtys");
        // product should not be empty
        int arrLength = items.length - 1;
        List<QuoteItem> qiList = new ArrayList<QuoteItem>();
        for ( int i = 0 ; i < arrLength; i ++ ) {
            // get product ref
            Product product = productService.get(Datastore.stringToKey(items[i]));
            QuoteItem qi = new QuoteItem();
            qi.getProductRef().setModel(product);
            qi.setDesc(descs[i]);
            qi.setRate(Double.valueOf(rates[i]));
            qi.setQty(Double.valueOf(quantities[i]));
            qiList.add(qi);
        }
        
        // assemble quote version
        QuoteVersion qv = new QuoteVersion();
        qv.setName(MicroBizUtil. getQuoteVersionName(quote.getCount()));
        qv.setCreateDate(new Date());
        qv.setTaxRate(quote.getTaxRate());
        qv.setDiscount(quote.getDiscount());
        // calculate at the front end
        qv.setTotal(quote.getTotal());
        //set order item list for save
        qv.setItemList(qiList);
        
        quote.setQuoteVersion(qv);
        // save in one TXN
        Key quoteKey = microBizService.createQuote(quote);
        quote = quoteService.get(quoteKey);
        // to edit mode
        return redirect("/quote/quoteDetails?quoteKey=" + Datastore.keyToString(quote.getKey()));
    }
}
