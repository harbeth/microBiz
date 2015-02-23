package com.microBiz.model;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;


@Model
public class Order extends MiBaseModel{
 
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double taxRate;
    
    @Attribute(unindexed = true)
    private Double total;
    
    @Attribute(unindexed = true)
    private Double discount;
    
    @Attribute(persistent = false)
    // need to calculate: from order items
    private Double subTotal;
    
    // only to collect data
    @Attribute(persistent = false)
    // need to calculate: from order items
    private List<OrderItem> orderItemList;
    
    @Attribute(persistent = false)
    private InverseModelListRef<OrderItem, Order> itemsRef = new InverseModelListRef<OrderItem, Order>(OrderItem.class, "orderRef", this);
 

    public InverseModelListRef<OrderItem, Order> getItemsRef() {
        return itemsRef;
    }    
    
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }



    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }



    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    public String getTaxRateStr() {
        return String.valueOf(taxRate);
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
 
}
