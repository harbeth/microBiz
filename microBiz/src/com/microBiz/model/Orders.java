package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;


@Model
public class Orders implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
        
    

    
    @Attribute(unindexed = true)
    private Double taxRate;
    
    @Attribute(unindexed = true)
    private Double total;
    
    @Attribute(unindexed = true)
    private Double discount;
    
    @Attribute(persistent = false)
    // need to calculate: from order items
    private Double subTotal;
    
    @Attribute(persistent = false)
    private InverseModelListRef<Item, Orders> itemsRef = new InverseModelListRef<Item, Orders>(Item.class, "ordersRef", this);
 
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);
    
    private ModelRef<QuoteVersion> quoteVersionRef = new ModelRef<QuoteVersion>(QuoteVersion.class);
    
    
    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }


    public ModelRef<QuoteVersion> getQuoteVersionRef() {
        return quoteVersionRef;
    }


    public InverseModelListRef<Item, Orders> getItemsRef() {
        return itemsRef;
    }    
    

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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
    
 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Orders other = (Orders) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }



}
