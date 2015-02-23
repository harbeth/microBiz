package com.microBiz.model;

public class LabelValue {
    
    private String label;
    private Integer value;
    
    public LabelValue(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
    
    public String getLabel() {
        return label;
    }
    
    public Integer getValue() {
        return value;
    }
}
