var productFn = {
	init: function() {
		// register event for edit prd link
		this.onProductEditClick();
		this.onProductNewClick();
	}
	, onProductEditClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=productEdit]").click(function(){
			var productKey = $(this).attr("productKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/prd/productEdit?productKey=" + productKey, function() {
				productEditFn.init(); 
			});
		});
	}
	, onProductNewClick: function() {
		// new invoice button in the invoice list page
		$("a[link=productNew]").click(function(){
			// after get invoice key, get details page
			$("#"+microBizConst.bodyContentId).load("/prd/productEdit", function() {
				// job page is shown by default
				productEditFn.init(); 
			});
		});
	}
}

var productEditFn = {
		init: function() {
			this.onCloseClick();
			// submit button event
			this.onSumitRegister();
		}
		, onSumitRegister: function() {
			// refresh body
			var options = { 
		        target: "#"+microBizConst.bodyContentId
		        , beforeSubmit: function() {
		        	return productEditFn.validateForm();
		        }
		        , success: function(responseText, statusText, xhr, $form){
		        	
		        }
		    }; 
		    // bind to the form's submit event 
		    $("form[name=productEditForm]").submit(function() { 
		    	alert("going to ajax save");
		    	$(this).ajaxSubmit(options); 
		        // !!! Important !!! 
		        // always return false to prevent standard browser submit and page navigation 
		        return false; 
		    }); 
		}
		, onCloseClick: function() {
			// back to invoice list page 
			$("a[link=productEditClose]").click(function(){
				window.location.href = "/prd/product";
			});
		}
		, validateForm: function() {
			var isOK = microBizFn.validateForm();
			if ( isOK ) {
				// check
				isOK = productEditFn.checkProductInput();
			}
			return isOK;
		}
		, checkProductInput: function() {
			// not -1
			var isOK = true;
			$("input[rowIndex!=-1][prdInput=true]").each(function() {
				if ( isOK ) {
					// should trim later
					isOK = $(this).val() != "";
				}
			});
			if ( !isOK ) {
				alert("cannot be empty");
			}
			return isOK;
		}
}

