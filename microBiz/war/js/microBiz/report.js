var reportFn = {
	init: function() {
		this.onClearSearchClick();
		this.onInvoiceSearchSubmit();
	}
	,onClearSearchClick: function() {
		
		$("a[link=clearInvoiceSearch]").click(function(){
		
			$("input[name=signDateFrom]").val('');
			$("input[name=signDateTo]").val('');
			// also need invoice key to get details
			$("#invoiceProfitListDIV").load("/report/invoiceProfit?onlyList=true", function() {
				reportFn.init(); 
			});
		});
	}
, onInvoiceSearchSubmit: function() {
		
		var options = { 
	        target: "#invoiceProfitListDIV"
	        , success: function(responseText, statusText, xhr, $form){
	        	reportFn.init(); 
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=invoiceSearchForm]").submit(function() {
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}


}

