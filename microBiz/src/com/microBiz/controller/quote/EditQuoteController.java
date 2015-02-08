package com.microBiz.controller.quote;


// load quote details page
public class EditQuoteController extends QuoteDetailsController {

    public String getReturnJsp() {
        return "quote-details-wrapper.jsp";
    }
}
