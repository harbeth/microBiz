package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.microBiz.MicroBizUtil;


@Model
public class InvoiceOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    
    private ModelRef<Order> orderRef = new ModelRef<Order>(Order.class);
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);

    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }

    public ModelRef<Order> getOrderRef() {
        return orderRef;
    }
    
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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
        InvoiceOrder other = (InvoiceOrder) obj;
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
