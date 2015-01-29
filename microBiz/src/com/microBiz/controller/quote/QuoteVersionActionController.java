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
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteItem;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.MicroBizService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;
import com.microBiz.service.QuoteVersionService;

public class QuoteVersionActionController extends BaseController{

    private QuoteService quoteService;
    private QuoteVersionService quoteVersionService;
    private QuoteVersionMeta metaQuoteVersion;
    private ProductService productService;
    private MicroBizService microBizService;
    
    public QuoteVersionActionController(){
        super();
        quoteService = new QuoteService();
        quoteVersionService = new QuoteVersionService();
        productService = new ProductService();
        microBizService = new MicroBizService();
        metaQuoteVersion = new QuoteVersionMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        //must have quote version key,  
        QuoteVersion qv = quoteVersionService.get(asKey(metaQuoteVersion.key));
        // get discount, taxRate, total from UI
        BeanUtil.copy(request, qv);
        //  need reuse with createAction
        // assemble quote item
        String[] items = paramValues("items");
        String[] rates = paramValues("rates");
        String[] descs = paramValues("descs");
        String[] quantities = paramValues("qtys");
        // product should not be empty, remove -1 at the end
        int arrLength = items.length - 1;
        List<QuoteItem> qiList = new ArrayList<QuoteItem>();
        for ( int i = 0 ; i < arrLength; i ++ ) {
            // get product REF
            Product product = productService.get(Datastore.stringToKey(items[i]));
            QuoteItem qi = new QuoteItem();
            qi.getProductRef().setModel(product);
            qi.setDesc(descs[i]);
            qi.setRate(Double.valueOf(rates[i]));
            qi.setQty(Double.valueOf(quantities[i]));
            qiList.add(qi);
        }
        //set order item list for save
        qv.setItemList(qiList);
        
        Quote quote = qv.getQuoteRef().getModel();
        // for save and saveAs
        //String actionName = asString("actionName");
        String saveOption = asString("saveOption");
        System.out.println("saveOption: " + saveOption);
        Key quoteVersionKey = qv.getKey();
        if ( saveOption.equals("final") ) {
            // for final, change status and create new invoice
            quote.setStatus("final");
            
        }else if ( saveOption.equals("saveAs") ) {
            // set name increase one, also need to update quote
            int quoteVersionCount = quote.getCount();
            quoteVersionCount ++ ;
            qv.setName(MicroBizUtil.getQuoteVersionName(quoteVersionCount));
            qv.setCreateDate(new Date());
            qv.setKey(null);
            // increase one
            quote.setCount(quoteVersionCount);
            quote.setQuoteVersion(qv);
            // change selected quoteVersionKey to new created
            quoteVersionKey = microBizService.createQuoteVersion(quote);
            System.out.println("save as qvKey: " + quoteVersionKey);
        }else{
            // for save , keep key same, delete all orderItem, then insert again
            // should compare for create, update and delete
            microBizService.updateQuoteVersion(qv);
        }
        // to edit mode, get data for edit page, use forward not working, src param empty
        qv = quoteVersionService.get(quoteVersionKey);
        quote = qv.getQuoteRef().getModel();
        requestScope("quote", quote);
        List<QuoteVersion> quoteVersionList = quote.getQuoteVersionsRef().getModelList();
        System.out.println("get qv list: " + quoteVersionList.size());
        // quote version key could be empty
        // from edit, set selected
        
        requestScope("selectedKey", quoteVersionKey);
        // the first one is selected
        requestScope("quoteVersions", quoteVersionList);
        List<? extends OrderItem> orderItemList = qv.getQuoteItemsRef().getModelList();
        // set qv sub total
        qv.setSubTotal(MicroBizCalculator.getSubTotal(orderItemList));
        //System.out.println("action cal qv sub total: " + qv.getSubTotal()); 
        BeanUtil.copy(qv, request);
        // !!!! for save as, some times showing order items only new created items, 
        // no old, after reload then OK
        requestScope("orderItems", orderItemList);
        System.out.println("get orderItems list: after creating version: " + orderItemList.size());
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        
        requestScope("txRates", txRates);
        
        
        return forward("quote-details.jsp");
        
        // cross-group transaction need to be explicitly specified, 
        // see TransactionOptions.Builder.withXGfound both Element
        
        //return redirect("/quote/quoteDetails?quoteKey=" + Datastore.keyToString(quote.getKey()) 
                                     //+ "&quoteVersionKey=" + Datastore.keyToString(quoteVersionKey));
    }
}
