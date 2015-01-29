package com.microBiz.controller.quote;

import com.microBiz.controller.EditQuoteController;

// load quote details page
public class QuoteDetailsController extends EditQuoteController {

    public String getReturnJsp() {
        return "quote-details.jsp";
    }
}
