package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.MiLogMeta;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.meta.QuoteOrderMeta;
import com.microBiz.model.MiLog;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;

public class QuoteService {
    
    private QuoteMeta p = new QuoteMeta();
    private QuoteOrderMeta quoteOrderMeta = new QuoteOrderMeta();
 
    
    public List<Quote> getAll() {
         return Datastore.query(p).sort(p.createdAt.desc).asList();
        
    }
    
    public List<Quote> getQuotesByUser(String name) {
        return Datastore.query(p).filter(p.creatorName.equal(name)).sort(p.createdAt.desc).asList();
    }
    
    public List<Quote> searchQuoteAddrStartWith(String searchAddrStr) {
        return Datastore.query(p).filter(p.address.startsWith(searchAddrStr)).asList();
    }
    
    public List<Quote> searchQuoteByUserAddrStarts(String name,
        String searchAddrStr) {
        return Datastore.query(p).filter(p.creatorName.equal(name),p.address.startsWith(searchAddrStr)).asList();
    }
    
    public List<Quote> searchQuoteByStatusAddrStartWith(String searchAddrStr,
        Integer status) {
        return Datastore.query(p).filter(p.status.equal(status),p.address.startsWith(searchAddrStr)).asList();
}

    public List<Quote> searchQuoteByUserStatusAddrStarts(String name,
            String searchAddrStr, Integer status) {
        return Datastore.query(p).filter(p.creatorName.equal(name),p.status.equal(status)).filterInMemory(p.address.startsWith(searchAddrStr)).asList();
    }
    
    public List<Quote> getQuotesByStatus( Integer status) {
        return Datastore.query(p).filter(p.status.equal(status)).sort(p.createdAt.desc).asList();
    }
    
    public List<Quote> getQuotesByUserStatus(String name, Integer status) {
        return Datastore.query(p).filter(p.creatorName.equal(name),p.status.equal(status)).asList();
    }
    
    public List<Quote> getCustomerQuote(Key customerKey) {
        return Datastore.query(p).asList();
    }
    
    public Quote get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Quote f) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, f);
        tx.commit();
        return key;
    }
    
    public Key saveQuoteOrder(QuoteOrder qo) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, qo);
        tx.commit();
        return key;
    }



    public QuoteOrder getQuoteOrder(Key key) {
        return Datastore.get(quoteOrderMeta, key);
    }

    public List<MiLog> getMiLogs(Key quoteKey) {
        MiLogMeta ml = new MiLogMeta();
        return Datastore.query(ml, quoteKey).sort(ml.createdAt.desc).asList();
    }

    public void saveLogEvent(MiLog milog, Key quoteKey) {
        Key logKey = Datastore.allocateId(quoteKey, MiLog.class);
        milog.setKey(logKey);
        Datastore.put(milog);       
    }




}
