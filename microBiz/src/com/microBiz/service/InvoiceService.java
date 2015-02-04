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
import com.microBiz.model.InvoiceOrder;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Order;
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
    public void convertQuoteToInvoice(Invoice invoice, Order order,
            List<OrderItem> qiList) {
        Transaction tx = Datastore.beginTransaction();
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
        invoice.getInvoiceOrderRef().setModel(invoiceOrder);
       
        Datastore.put(tx, invoice);
        Datastore.put(tx, invoiceOrder);
        Datastore.put(tx, newOrder);
        Iterator<OrderItem> itemsI= qiList.iterator();
        List<OrderItem> newList = new ArrayList<OrderItem>();
        while(itemsI.hasNext()) {
            OrderItem oneItem = (OrderItem)itemsI.next();
            Key itemKey = Datastore.allocateId(orderKey, OrderItem.class);
            OrderItem newItem = new OrderItem();
            BeanUtil.copy(oneItem, newItem);
            newItem.getProductRef().setModel(oneItem.getProductRef().getModel());
            newItem.setKey(itemKey);
            newItem.getOrdersRef().setModel(newOrder);
            newList.add(newItem);

        }
        Datastore.put(tx, newList);

        
        tx.commit();
        
    }
    
    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }
}
