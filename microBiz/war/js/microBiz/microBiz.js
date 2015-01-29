var microBizConst = {
	bodyContentId: "bodyContentDIV"
}

// common function
var microBizFn = {
	// customer, contact
	onCustomerSelectChange: function() {
		
		// register event for customer select
		$("#customerSelect").change(function(){
			// based on customerKey, refresh contact DIV
			var customerKey = $(this).val();
			if ( customerKey == "-1" ) {
				// no selection, clear contact
				$("#customerContactDIV").html("");
			}else{
				// AJAX call to retrieve contact
				$( "#customerContactDIV").load( "/customerContactChange?customerKey=" + customerKey, function() {
					microBizFn.onCustomerContactSelectChange();
				});
			}
		});
		
		// contact select could be there
		if ( $("#customerContactSelect").length ) {
			this.onCustomerContactSelectChange();
		}
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
				$("#customerContactInfoDIV").load( "customerContactInfo?contactKey=" + contactKey);
			}
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