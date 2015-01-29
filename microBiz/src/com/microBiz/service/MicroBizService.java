package com.microBiz.service;

import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteItem;
import com.microBiz.model.QuoteVersion;

// need to user more than one services in one TXN
public class MicroBizService {
    // save quote, quoteVersion, quoteItem
    public Key createQuote(Quote quote) {
        Transaction tx = Datastore.beginTransaction();
        QuoteService quoteService = new QuoteService();
        // save quote first to get quoteKey
        Key keyQuote = quoteService.save(quote);
        // set to quoteVersion
        QuoteVersion qv = quote.getQuoteVersion();
        List<QuoteItem> qiList = qv.getItemList();
        qv.getQuoteRef().setModel(quote);
        QuoteVersionService qvService = new QuoteVersionService();
        Key keyQV = qvService.save(qv);
        qv.setKey(keyQV);
        // set to quote items, should not be empty
        QuoteItemService qiService = new QuoteItemService();
        for ( QuoteItem qi : qiList ) {
            // save quote item
            qi.getQuoteVersionRef().setModel(qv);
            qiService.save(qi);
        }
        tx.commit();
        return keyQuote;
    }
    
    // return quote version key
    public Key createQuoteVersion(Quote quote) {
        Transaction tx = Datastore.beginTransaction();
        QuoteVersion quoteVersion = quote.getQuoteVersion();
        List<QuoteItem> qiList = quoteVersion.getItemList();
        // update quote first
        QuoteService quoteService = new QuoteService();
        quoteService.save(quote);
        quote = quoteService.get(quote.getKey());
        // create quote version
        quoteVersion.getQuoteRef().setModel(quote);
        QuoteVersionService qvService = new QuoteVersionService();
        Key keyQV = qvService.save(quoteVersion);
        quoteVersion.setKey(keyQV);
        // insert quote items
        QuoteItemService qiService = new QuoteItemService();
        for ( QuoteItem qi : qiList ) {
            // save quote item
            qi.getQuoteVersionRef().setModel(quoteVersion);
            qiService.save(qi);
        }
        tx.commit();
        return keyQV;
    }
    
    // just order item changed, need to clean up all order items and insert again
    public void updateQuoteVersion(QuoteVersion quoteVersion) {
       // Transaction tx = Datastore.beginTransaction();
        // need to update quote version for total, taxRate
        QuoteVersionService qvService = new QuoteVersionService();
        qvService.save(quoteVersion);
        QuoteItemService qiService = new QuoteItemService();
        List<QuoteItem> qiOriginalList = quoteVersion.getQuoteItemsRef().getModelList();
        List<Key> keys = new ArrayList<Key>();
        for ( QuoteItem qi : qiOriginalList ) {
            keys.add(qi.getKey());
        }
        qiService.deleteKeys(keys);
        // then insert all
        // set to quote items
        List<QuoteItem> qiList = quoteVersion.getItemList();
        // quote version should have key
        for ( QuoteItem qi : qiList ) {
            // save quote item
            qi.getQuoteVersionRef().setModel(quoteVersion);
            qiService.save(qi);
        }
        //tx.commit();
    }
}
