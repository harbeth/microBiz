package com.microBiz.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.MicroBizUtil;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.meta.QuoteOrderMeta;
import com.microBiz.model.Customer;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;

public class QuoteService {
    
    private QuoteMeta p = new QuoteMeta();
    private QuoteOrderMeta quoteOrderMeta = new QuoteOrderMeta();
 
    
    public List<Quote> getAll() {
        return Datastore.query(p).asList();
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

    public void saveQuoteVersion(QuoteOrder f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
   
    public void save(Key parentKey, Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Quote.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

    //here for insert new quote, return quote key
    public Key createQuote(Quote quote, QuoteOrder qv, Order order, List<OrderItem> qiList) {

        Transaction tx = Datastore.beginTransaction();
        Key parentKey = Datastore.put(tx, quote);
        System.out.println("Quote key is " + Datastore.keyToString(parentKey));
        Key quoteVersionKey = Datastore.allocateId(parentKey, QuoteOrder.class);
        qv.setKey(quoteVersionKey);
        qv.getQuoteRef().setModel(quote);
        
        Key ordersKey = Datastore.allocateId(quoteVersionKey, Order.class);
        order.setKey(ordersKey);
        qv.getOrderRef().setModel(order);

        Datastore.put(tx, qv);
        Datastore.put(tx,order);
        setItemParent(ordersKey,order, qiList);
        Datastore.put(tx,qiList);
        tx.commit();
        return parentKey;
    }

    // return quote key
    public Key saveAsNewVersion(QuoteOrder qv, Order order) {
        Transaction tx = Datastore.beginTransaction();
        List<OrderItem> oiList = order.getOrderItemList();
        Quote quote = qv.getQuoteRef().getModel();
        int quoteVersionCount = quote.getCount();
        quoteVersionCount ++ ;
        quote.setCount(quoteVersionCount);
        Datastore.put(tx, quote);
        Key quoteVersionKey = Datastore.allocateId(quote.getKey(), QuoteOrder.class);
        qv.setName(MicroBizUtil.getQuoteVersionName(quoteVersionCount));
        qv.setCreateAt(new Date());
        qv.setKey(quoteVersionKey);

        Key ordersKey = Datastore.allocateId(quoteVersionKey, Order.class);
        order.setKey(ordersKey);
        qv.getOrderRef().setModel(order);
        Datastore.put(tx, qv);
        Datastore.put(tx, order);
        setItemParent(ordersKey,order, oiList);
        Datastore.put(tx, oiList);
        tx.commit();
        return quote.getKey();
    }
    
    public void saveAsCurrentVersion(QuoteOrder quoteOrder, Order order) {
        Transaction tx = Datastore.beginTransaction();
        List<OrderItem> oiList = order.getOrderItemList();
        Quote quote = quoteOrder.getQuoteRef().getModel();
        QuoteOrder newQv = new QuoteOrder();
        newQv.setName(quoteOrder.getName());
        newQv.getQuoteRef().setModel(quote);
        newQv.setCreateAt(new Date());
        
        Key quoteVersionKey = Datastore.allocateId(quote.getKey(), QuoteOrder.class);
        newQv.setKey(quoteVersionKey);
        Datastore.put(tx, newQv);
        //delete old version
        Datastore.deleteAll(tx, quoteOrder.getKey());

        Key ordersKey = Datastore.allocateId(quoteVersionKey, Order.class);
        order.setKey(ordersKey);
        newQv.getOrderRef().setModel(order);
        Datastore.put(tx, newQv);
        Datastore.put(tx, order);

        setItemParent(ordersKey,order, oiList);
        Datastore.put(tx, oiList);
        tx.commit();
    }
    
    private void setItemParent(Key ordersKey,Order orders, List<OrderItem> items){
        Iterator<OrderItem> itemsI= items.iterator();
        while(itemsI.hasNext()) {
            OrderItem oneItem = (OrderItem)itemsI.next();
            Key itemKey = Datastore.allocateId(ordersKey, OrderItem.class);
            oneItem.setKey(itemKey);
            oneItem.getOrderRef().setModel(orders);
        }
    }

    public QuoteOrder getQuoteOrder(Key key) {
        return Datastore.get(quoteOrderMeta, key);
    }
}
