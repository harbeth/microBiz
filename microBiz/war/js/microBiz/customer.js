var customerFn = {
	init: function() {
		// register event for edit invoice link
		this.onCustomerDetailClick();
		this.onCustomerNewClick();
	}
	, onCustomerDetailClick: function() {
		// invoice number link to sh invoice details on AJAX call in the body panel
		$("a[link=customerEditDetail]").click(function(){
			var customerKey = $(this).attr("customerKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/customer/customerDetails?customerKey=" + customerKey, function() {
				customerDetailFn.init(); 
			});
		});
	}
	, onCustomerNewClick: function() {
		// new invoice button in the invoice list page
		$("a[link=customerNew]").click(function(){
			// after get invoice key, get details page
			$("#"+microBizConst.bodyContentId).load("/customer/customerCreate", function() {
				// job page is shown by default
				customerNewFn.init(); 
			});
		});
	}
}

// for invoice info page,  new invoice page and edit invoice page "info" tab
var customerNewFn = {
	init: function() {
		customerEditFn.init();
		customerEditFn.onCloseClick();
		// submit button event
		this.onSumitNewRegister();
	}
	, onSumitNewRegister: function() {
		// refresh body
		var options = { 
	        target: "#"+microBizConst.bodyContentId
	        , beforeSubmit: function() {
	        	return customerEditFn.validateForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// load invoice details page
	        	customerDetailFn.init();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=customereDetailInfoForm][type=new]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
}

//on edit tab selected
var customerUpdateFn = {
	init: function() {
		customerEditFn.init();
		// submit button event
		this.onSumitEditRegister();
	}
	, onSumitEditRegister: function() {
		// keep the same tab and update info DIV
		var options = { 
	        target: "#customerInfoDIV"
	        , beforeSubmit: function() {
	        	return customerEditFn.validateForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// refresh whole page
	        	window.location.href = "/customer";
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=customerDetailInfoForm][type=edit]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}	
}
var customerEditFn = {
		init: function() {
			//this.onCustomerSelectChange();
			//this.fieldValidate();
		}
		, fieldValidate: function() {
			//$("input[valueType=price]").alphanumeric({allow:"."});
			$("input[valueType=price]").numeric();
		}
		, onCloseClick: function() {
			// back to invoice list page 
			$("a[link=customerEditClose]").click(function(){
				window.location.href = "/customer";
			});
		}
		, onCustomerSelectChange: function() {
			// register event for customer select
			$("#invoiceCustomerSelect").change(function(){
				// based on customerKey, refresh contact DIV
				var customerKey = $(this).val();
				if ( customerKey == "-1" ) {
					// no selection, clear contact
					$("#invoiceCustomerContactDIV").html("");
				}else{
					// AJAX call to retrieve contact
					$( "#invoiceCustomerContactDIV").load( "/invoice/invoiceCustomerContact?customerKey=" + customerKey, function() {
						invoiceEditFn.onCustomerContactSelectChange();
					});
				}
			});
		}
		, onCustomerContactSelectChange: function() {
			// register event for contact select
			$("#invoiceCustomerContactSelect").change(function(){
				// based on customerKey, refresh contact DIV
				var contactKey = $(this).val();
				if ( contactKey == "-1" ) {
					// no selection, clear contact
					$("#invoiceCustomerContactInfoDIV").html("");
				}else{
					// AJAX call to retrieve contact
					$("#invoiceCustomerContactInfoDIV").load( "/invoice/invoiceCustomerContactInfo?contactKey=" + contactKey);
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
			if( isOK ) {
				// check customer type
				var oldCustomerType = $("input[name=oldCustomerType]").val();
				var editMode = $("input[name=oldCustomerType]").attr("editMode");
				if ( editMode == "edit" ) {
					var currentCustomerType = $("#customerTypeSelect").val();
					if ( oldCustomerType == "commercial" && currentCustomerType == "residential" ) {
						isOK = false;
						alert("Cannot change customer type from commercial to residential.");
					}
					// from residential to commercial, need to info add contact to invoice and quotation
				}
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

var customerDetailFn = {
	init: function() {
		// register event for three tabs
		this.registerTabClick();
		// close button
		customerEditFn.onCloseClick();
		// new contact button
		this.onCustomerContactEditClick();
		// register event for edit job link
		//this.onInvoiceJobEditClick();
		//this.onInvoicePaymentEditClick();
	}
	// tab event
	, registerTabClick: function() {
		$("a[link^=customerDetail]").click(function(){
			if ( !customerDetailFn.isTabSelected($(this)) ) {
				// hide three DIVs first
				$("div[id^=customerDetail]").hide();
				// reset selected flag
				var tabType = $(this).attr("link");
				// also need invoice key to get job list after submit
				eval("customerDetailFn."+tabType+"Display()");
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
		});
	}
	, customerDetailInfoDisplay: function() {
		var ctrl = $("#customerDetailInfoDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var customerKey = ctrl.attr("customerKey");
			ctrl.load("/customer/customerEdit?customerKey=" + customerKey, function(){
				// after edit tab loaded
				customerUpdateFn.init();
			})
			.attr("hasContent", "y");
		}
	}
	// quote is default page
	, customerDetailInvoiceDisplay: function() {
		var ctrl = $("#customerDetailInvoiceDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var customerKey = ctrl.attr("customerKey");
			ctrl.load("/customer/customerInvoice?customerKey=" + customerKey, function(){
				// invoice page
			})
			.attr("hasContent", "y");
		}
	}
	, customerDetailQuoteDisplay: function() {
		// do nothing, already has content
	}
	, customerDetailContactDisplay: function() {
		// show contact tab, contact list
		// do nothing, already has content
		var ctrl = $("#customerDetailContactDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var customerKey = ctrl.attr("customerKey");
			ctrl.load("/customer/customerContacts?customerKey=" + customerKey, function(){
				// contact page page, register edit link
				customerDetailFn.onCustomerContactEditClick();
				
			})
			.attr("hasContent", "y");
		}
	}
	// quote edit link will redirect to 
	, onCustomerContactEditClick: function() {
		$("a[link=customerContactEdit]").click(function() {
			var customerKey = $(this).attr("customerKey");
			var contactKey = $(this).attr("contactKey");
			if ( contactKey == "-1" ) {
				customerDetailFn.onNewButtonClick(contactKey);
			}
			// just load contact list 
			$("#customerDetailContactDIV").load("/customer/customerContactEdit?contactKey="+contactKey+"&customerKey="+customerKey, function() {
				customerDetailFn.onCustomerContactEditClose();
				customerDetailFn.registerCustomerContactDetailForm();
			});
		});
	}
	// on contact detail page close
	, onCustomerContactEditClose: function() {
		$("a[link=customerContactEditClose]").click(function() {
			var customerKey = $(this).attr("customerKey");
			// just load job list 
			$("#customerDetailContactDIV").load("/customer/customerContacts?customerKey="+customerKey, function() {
				// register event on contact list page
				customerDetailFn.onCustomerContactEditClick();
			});
		});
	}
	//submit on contact detail
	, registerCustomerContactDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#customerDetailContactDIV"
	        , beforeSubmit: function(){
	        	// set invoiceKey value
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// load job list
	        	// register event again
	        	// customerDetailFn.onCustomerContactEditClick();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#customerDetailContactDetailForm").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
    }
	// ======= END Job =====================
	, isTabSelected: function(ctrlLink) {
		return ctrlLink.parent().hasClass("active");
	}
	, onNewButtonClick: function(key) {
		// when new contact button click, consider the current tab
		if ( key == "-1" ) {
			// if payment tab is not selected
			var ctrl = $("a[link=customerDetailContact]");
			if ( !this.isTabSelected(ctrl) ) {
				// tab
				var ctrlArr = $("a[link^=customerDetail]");
				ctrlArr.parent().removeAttr("class");
				ctrl.parent().attr("class", "active");
				// DIV
				ctrlArr = $("div[type=tab][id^=customerDetail]");
				ctrlArr.hide();
				var tabType = ctrl.attr("link");
				// also need invoice key to get job list after submit
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
		}
	}
	// ====== Payment ======================
	// assign payment button and edit link
	, paymentListPageInit: function() {
		// edit link and button
		this.onInvoicePaymentEditClick();
	}
	, paymentDetailPageInit: function() {
		this.registerPaymentDetailForm();
		this.onInvoicePaymentEditClose();
		invoiceEditFn.fieldValidate();
	}
	, onInvoicePaymentEditClick: function() {
		$("a[link=invoicePaymentEdit]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var paymentKey = $(this).attr("paymentKey");
			invoiceDetailFn.onNewButtonClick("Payment", paymentKey);
			$("#invoiceDetailPaymentDIV").load("/invoice/invoicePaymentEdit?paymentKey="+paymentKey+"&invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.paymentDetailPageInit();
			});
		});
	}
	// on job detail page
	, onInvoicePaymentEditClose: function() {
		$("a[link=invoiceEditClosePayment]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			// just load job list 
			$("#invoiceDetailPaymentDIV").load("/invoice/invoicePaymentList?invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.onInvoicePaymentEditClick();
			});
		});
	}
	, registerPaymentDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailPaymentDIV"
	        , beforeSubmit: function(){
	        	// set invoiceKey value
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// load job list
	        	// register event again
	        	invoiceDetailFn.onInvoicePaymentEditClick();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#invoiceDetailPaymentDetailForm").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
    }
}
