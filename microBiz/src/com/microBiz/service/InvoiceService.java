package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Item;
import com.microBiz.model.Orders;
import com.microBiz.model.Quote;



public class InvoiceService {
    
    private InvoiceMeta i = new InvoiceMeta();
 
    
    public List<Invoice> getAll() {
        return Datastore.query(i).asList();
    }
    
    public List<Invoice> getCustomerInvoice(Key customerKey) {
        return Datastore.query(i).asList();
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
    public void convertQuoteToInvoice(Invoice invoice, Orders orders,
            List<Item> qiList) {
        Transaction tx = Datastore.beginTransaction();
        System.out.println("going to save invoice");
       Key invoiceKey = Datastore.put(tx, invoice);
       System.out.println("have saved invoice");
       Orders newOrders = new Orders();
       BeanUtil.copy(orders, newOrders);
       Key ordersKey = Datastore.allocateId(invoiceKey, Orders.class);

       newOrders.setKey(ordersKey);
       newOrders.getInvoiceRef().setModel(invoice);
        invoice.getOrdersRef().setModel(newOrders);
       
        Datastore.put(tx, invoice);
        Datastore.put(tx, newOrders);
        Iterator<Item> itemsI= qiList.iterator();
        List<Item> newList = new ArrayList<Item>();
        while(itemsI.hasNext()) {
            Item oneItem = (Item)itemsI.next();
            Key itemKey = Datastore.allocateId(ordersKey, Item.class);
            Item newItem = new Item();
            BeanUtil.copy(oneItem, newItem);
            newItem.getProductRef().setModel(oneItem.getProductRef().getModel());
            newItem.setKey(itemKey);
            newItem.getOrdersRef().setModel(newOrders);
            newList.add(newItem);

        }
        Datastore.put(tx, newList);

        
        tx.commit();
        
    }
    
    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }
}
