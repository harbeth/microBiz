package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.meta.OrderMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceOrder;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;



public class InvoiceService {
    
    private InvoiceMeta i = new InvoiceMeta();
    private OrderMeta o = new OrderMeta();
 
    
    public List<Invoice> getAll() {
        return Datastore.query(i).asList();
    }
    
    public List<Invoice> getCustomerInvoice(Key customerKey) {
        return Datastore.query(i).asList();
    }
    
    public Order getInvoiceOrder(Key orderKey) {
        return Datastore.get(o, orderKey);
    }
    
    public Invoice get(Key key) {
        return Datastore.get(i, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Invoice i) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, i);
        tx.commit();
        return key;
    }
    
    //orders and itemList already have a parent (quote), so have to create new orders and Items for the new invoice
    public void convertQuoteToInvoice(Invoice invoice, Order order) {
       Transaction tx = Datastore.beginTransaction();
       List<OrderItem> oiList = order.getOrderItemList();
       System.out.println("going to save invoice");
       Key invoiceKey = Datastore.put(tx, invoice);
       System.out.println("have saved invoice");
       Order newOrder = new Order();
       InvoiceOrder invoiceOrder = new InvoiceOrder();
       BeanUtil.copy(order, newOrder);
       Key invoiceOrderKey = Datastore.allocateId(invoiceKey, InvoiceOrder.class);
       Key orderKey = Datastore.allocateId(invoiceOrderKey, Order.class);

       newOrder.setKey(orderKey);
       invoiceOrder.setKey(invoiceOrderKey);
       invoiceOrder.getInvoiceRef().setModel(invoice);
       invoiceOrder.getOrderRef().setModel(newOrder);
       
        Datastore.put(tx, invoice);
        Datastore.put(tx, invoiceOrder);
        Datastore.put(tx, newOrder);
        Iterator<OrderItem> itemsI= oiList.iterator();
        List<OrderItem> newList = new ArrayList<OrderItem>();
        while(itemsI.hasNext()) {
            OrderItem oneItem = (OrderItem)itemsI.next();
            Key itemKey = Datastore.allocateId(orderKey, OrderItem.class);
            OrderItem newItem = new OrderItem();
            BeanUtil.copy(oneItem, newItem);
            newItem.getProductRef().setModel(oneItem.getProductRef().getModel());
            newItem.setKey(itemKey);
            newItem.getOrderRef().setModel(newOrder);
            newList.add(newItem);

        }
        Datastore.put(tx, newList);
        tx.commit();
    }
    
    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }

    //return invoice key
    public Key saveNewInvoice(Invoice invoice, Order order) {
        
       Transaction tx = Datastore.beginTransaction();
       List<OrderItem> oiList = order.getOrderItemList();
       Key invoiceKey = Datastore.put(tx, invoice);
       System.out.println("have saved invoice");
       
       InvoiceOrder invoiceOrder = new InvoiceOrder();
       Key invoiceOrderKey = Datastore.allocateId(invoiceKey, InvoiceOrder.class);
       Key orderKey = Datastore.allocateId(invoiceOrderKey, Order.class);
       order.setKey(orderKey);
       
       invoiceOrder.setKey(invoiceOrderKey);
       invoiceOrder.getInvoiceRef().setModel(invoice);
       invoiceOrder.getOrderRef().setModel(order);
       
       Datastore.put(tx, invoice);
       Datastore.put(tx, invoiceOrder);
       Datastore.put(tx, order);
       
       setItemParent(orderKey, order, oiList);
       Datastore.put(tx, oiList);
       tx.commit();
       return invoice.getKey();
    }
    
    // just update that order, return new order key for get
    public Key updateOrder(Invoice invoice, Order order) {
        Transaction tx = Datastore.beginTransaction();
        List<OrderItem> oiList = order.getOrderItemList();
        // should only has one
        InvoiceOrder invoiceOrder = invoice.getInvoiceOrderRef().getModelList().get(0);
        //delete old version
        Datastore.deleteAll(tx, invoiceOrder.getKey());
        InvoiceOrder newIO = new InvoiceOrder();
        newIO.getInvoiceRef().setModel(invoice);
        // get new invoice order key
        Key invoiceOrderKey = Datastore.allocateId(invoice.getKey(), InvoiceOrder.class);
        newIO.setKey(invoiceOrderKey);
        // create new invoice order
        Datastore.put(tx, newIO);
        
        Key orderKey = Datastore.allocateId(invoiceOrderKey, Order.class);
        order.setKey(orderKey);
        // create order
        Datastore.put(tx, order);
        // update new created invoice order
        newIO.getOrderRef().setModel(order);
        Datastore.put(tx, newIO);
        // set order key to order item
        setItemParent(invoiceOrderKey, order, oiList);
        // create order item list
        Datastore.put(tx, oiList);
        tx.commit();
        return orderKey;
    }
    /*
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

        setItemParent(ordersKey,order, oiList);
    }
     
     */
    
    private void setItemParent(Key orderKey, Order order, List<OrderItem> items) {
        Iterator<OrderItem> itemsI= items.iterator();
        while(itemsI.hasNext()) {
            OrderItem oneItem = (OrderItem)itemsI.next();
            Key itemKey = Datastore.allocateId(orderKey, OrderItem.class);
            oneItem.setKey(itemKey);
            oneItem.getOrderRef().setModel(order);
        }
    }
}
