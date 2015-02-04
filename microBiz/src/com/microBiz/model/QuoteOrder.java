package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;


@Model
public class QuoteOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private String name;
    
    private Date createAt;
    
    @Attribute(persistent = false)
    private String createAtStr;
    
    private ModelRef<Order> orderRef = new ModelRef<Order>(Order.class);
    
    private ModelRef<Quote> quoteRef = new ModelRef<Quote>(Quote.class);

    public ModelRef<Quote> getQuoteRef() {
        return quoteRef;
    }

 
    
    public Date getCreateAt() {
        return createAt;
    }



    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }



    public String getCreateAtStr() {
        return MicroBizUtil.parseDateToStr(createAt);
    }



    public void setCreateAtStr(String createAtStr) {
        this.createAtStr = createAtStr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        QuoteOrder other = (QuoteOrder) obj;
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
