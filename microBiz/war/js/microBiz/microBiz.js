var microBizConst = {
	bodyContentId: "bodyContentDIV"
	// should get it from java constant file
	, CODE_CUSTOMER_TYPE_RESIDENTIAL: "70"
	, CODE_CUSTOMER_TYPE_COMMERCIAL: "71"
	, CODE_PRODUCT_TYPE_RAW_MATERIAL: "51"
}

// common function
var microBizFn = {
	// customer, contact
	onCustomerSelectChange: function() {
		/*
		// register event for customer select
		$("#customerSelect").change(function(){
			// based on customerKey, refresh contact DIV
			var customerKey = $(this).val();
			if ( customerKey == "-1" ) {
				// no selection, clear contact
				$("#customerContactDIV").html("");
			}else{
				// AJAX call to retrieve contact
				$( "#customerContactDIV").load( "/common/customerContactChange?customerKey=" + customerKey, function() {
					microBizFn.onCustomerContactSelectChange();
				});
			}
		});
		*/
		// contact select could be there
		if ( $("#customerContactSelect").length > 0 ) {
			this.onCustomerContactSelectChange();
		}
		
		$("input[name=customerSearch]").devbridgeAutocomplete({
			serviceUrl: "/common/customerSearch" + "?customerSearch="
			, onSelect: function (suggestion) {
		    	// update hidden field
		    	var customerKey = suggestion.data;
		    	if ( customerKey == "-1" ) {
		    		// new customer
		    		var msg = "Are you sure you want to create a new customer?";
		    		if ( confirm(msg) ) {
		    			window.location.href = "/customer/newCustomer";
		    		}else{
		    			// clear input
		    			$(this).val("");
		    		}
		    	}else{
		    		$("input[name=customerKey]:hidden").val(customerKey);
		    		microBizFn.loadCustomerContact(customerKey);
		    	}
		    }
		});

	}
	, loadCustomerContact: function(customerKey) {
		// AJAX call to retrieve contact
		$( "#customerContactDIV").load( "/common/customerContactChange?customerKey=" + customerKey, function() {
			microBizFn.onCustomerContactSelectChange();
		});
	}
	, onCustomerContactSelectChange: function() {
		// register event for contact select
		$("#customerContactSelect").change(function(){
			// based on customerKey, refresh contact DIV
			var contactKey = $(this).val();
			if ( contactKey == "-1" ) {
				// no selection, clear contact
				$("#customerContactInfoDIV").html("");
			}else{
				// AJAX call to retrieve contact
				$("#customerContactInfoDIV").load( "/common/customerContactInfo?contactKey=" + contactKey);
			}
		});
	}
	, initPriceInput: function() {
		$('input[valueType=price]').priceFormat({
		    prefix: ''
		    , thousandsSeparator: ''
		    , centsLimit: 2
		});
	}
	, validateForm: function() {
		var isOK = true;
		// mandatory field
		var fields = [];
		$("input[mandatory=y]").each(function(){
			if ( $(this).val() == "" ) {
				fields.push($(this).attr("field"));
			}
		});
		if ( isOK ) {
			$("select[mandatory=y]").each(function(){
				if ( $(this).val() == "-1" ) {
					fields.push($(this).attr("field"));
				}
			});
		}
		if ( fields.length > 0 ) {
			isOK = false;
			alert(fields.join() + " cannot be empty.")
		}
			/*
			$("input[valueType=price]").each(function(){
				var priceValue = $(this).val();
				if ( priceValue != "" ) {
					var regex = /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})/;
			        var passed = priceValue.match(regex);
			        if (passed == null) {
			        	isOK = false;
			            alert($(this).attr("field") + " is price only. For example: 523.36");
			        }
				}
			});
			*/
		return isOK;
	}
}