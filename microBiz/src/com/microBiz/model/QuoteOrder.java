package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.microBiz.MicroBizUtil;


@Model
public class QuoteOrder extends MiCreatorBaseModel {

    private static final long serialVersionUID = 1L;


    private String name;

    
    @Attribute(persistent = false)
    private String createAtStr;
    
    private ModelRef<Order> orderRef = new ModelRef<Order>(Order.class);
    
    private ModelRef<Quote> quoteRef = new ModelRef<Quote>(Quote.class);

    public ModelRef<Quote> getQuoteRef() {
        return quoteRef;
    }

 
  



    public String getCreateAtStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }



    public void setCreateAtStr(String createAtStr) {
        this.createAtStr = createAtStr;
    }



    public ModelRef<Order> getOrderRef() {
        return orderRef;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
