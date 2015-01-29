package com.microBiz.model;

import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model
public class QuoteItem extends OrderItem{

    private ModelRef<QuoteVersion> quoteVersionRef = new ModelRef<QuoteVersion>(QuoteVersion.class);

    public ModelRef<QuoteVersion> getQuoteVersionRef() {
        return quoteVersionRef;
    }

}
