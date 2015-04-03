package com.microBiz.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;

//as child of invoice or quote
@Model
public class MiLog extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

  
    @Attribute(unindexed = true)
    private String note;

 
 
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getEnterDateStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }
}
