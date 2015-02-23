package com.microBiz.service;

import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;


public class OrderService {
    
    
    public List<OrderItem> gerOrderItems(Key orderKey){
       return Datastore.query(OrderItem.class, orderKey).asList();
        
    }
    //paramerter already has orderItems, no key, order is parent of orderItem 
    //return order key
    public Key saveNewOrder(Order order) {
       Transaction tx = Datastore.beginTransaction();
       Key orderKey = Datastore.put(tx, order);
       List<OrderItem> oiList = order.getOrderItemList();
       setOrderItemParent(orderKey,oiList );
        Datastore.put(tx, oiList);
        tx.commit();
        return orderKey;
    }
    
    //will remove older order and its children orderItems, insert new oder, return new order key
    //parameter order already had orderkey, but order items might be different f
    public Key updateOrder(Order order){
        Transaction tx = Datastore.beginTransaction();
        //this will delete order and its children
        Datastore.deleteAll(tx,order.getKey());
        //put back order again
        Key orderKey = Datastore.put(tx, order);

        List<OrderItem> oiList = order.getOrderItemList();
        
        setOrderItemParent(orderKey,oiList );
        Datastore.put(tx, oiList);
        
        tx.commit();
        return orderKey;
        
    }
    
    //will keep old order and order item  in datastore, create new order record with different order key, insert new oder, return new order key
    //parameter order already had orderkey
    public Key updateOrderAsNew(Order order){
        Transaction tx = Datastore.beginTransaction();
        //this will delete order and its children
        Key newOrderKey = Datastore.allocateId(Order.class);
        order.setKey(newOrderKey);
        
        Datastore.put(tx, order);

        List<OrderItem> oiList = order.getOrderItemList();
        
        setOrderItemParent(newOrderKey,oiList );
        Datastore.put(tx, oiList);
        
        tx.commit();
        return newOrderKey;
        
    }
    
    private void setOrderItemParent(Key orderKey,List<OrderItem> oiList ){
        
        Iterator<OrderItem> itemsI= oiList.iterator();
       
        while(itemsI.hasNext()) {
            OrderItem oneItem = (OrderItem)itemsI.next();
            Key itemKey = Datastore.allocateId(orderKey, OrderItem.class);
            oneItem.setKey(itemKey);
      

        }

    }
    
}
