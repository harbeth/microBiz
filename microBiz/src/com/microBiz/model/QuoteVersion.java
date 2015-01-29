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
public class QuoteVersion implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private String name;
    
    private Date createDate;
    
    @Attribute(persistent = false)
    private List<QuoteItem> itemList;
    
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
    // only for display
    private boolean selected;
    
    @Attribute(persistent = false)
    private InverseModelListRef<QuoteItem, QuoteVersion> quoteItemsRef = new InverseModelListRef<QuoteItem, QuoteVersion>(QuoteItem.class, "quoteVersionRef", this);
 
    public InverseModelListRef<QuoteItem, QuoteVersion> getQuoteItemsRef() {
        return quoteItemsRef;
    }    
    
    private ModelRef<Quote> quoteRef = new ModelRef<Quote>(Quote.class);

    public ModelRef<Quote> getQuoteRef() {
        return quoteRef;
    }

    public List<QuoteItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<QuoteItem> itemList) {
        this.itemList = itemList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
        QuoteVersion other = (QuoteVersion) obj;
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
