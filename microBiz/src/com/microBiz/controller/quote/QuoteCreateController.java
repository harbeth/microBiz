package com.microBiz.controller.quote;

import com.microBiz.controller.NewQuoteController;

public class QuoteCreateController extends NewQuoteController {
    
    public String getReturnJsp() {
        return "quote-new.jsp";
    }
}
