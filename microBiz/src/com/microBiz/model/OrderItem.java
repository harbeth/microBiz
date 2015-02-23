package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;


@Model
public class OrderItem extends MiBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double qty;
    
    @Attribute(unindexed = true)
    private Double rate;
    
    @Attribute(unindexed = true)
    private String desc;
    
    @Attribute(persistent = false)
    // only for display
    private Double total;
    
    private ModelRef<Product> productRef = new ModelRef<Product>(Product.class);


    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ModelRef<Product> getProductRef() {
        return productRef;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


}
