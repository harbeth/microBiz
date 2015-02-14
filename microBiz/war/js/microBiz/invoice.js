var invoiceFn = {
	init: function() {
		// register event for edit invoice link
		this.onInvoiceDetailClick();
		this.onInvoiceNewClick();
	}
	, onInvoiceDetailClick: function() {
		// invoice number link to sh invoice details on AJAX call in the body panel
		$("a[link=invoiceEditDetail]").click(function(){
			var invoiceKey = $(this).attr("invoiceKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/invoice/invoiceDetails?invoiceKey=" + invoiceKey, function() {
				// put it in the page for short cut from customer
				//invoiceDetailFn.init(); 
			});
		});
	}
	, onInvoiceNewClick: function() {
		// new invoice button in the invoice list page
		$("a[link=invoiceNew]").click(function(){
			// after get invoice key, get details page
			$("#"+microBizConst.bodyContentId).load("/invoice/invoiceCreate", function() {
				// job page is shown by default
				// put it in the page for short cut from customer
				//invoiceNewFn.init(); 
			});
		});
	}
}

// for invoice info page,  new invoice page and edit invoice page "info" tab
var invoiceNewFn = {
	// put it in the page for short cut from customer
	init: function() {
		invoiceEditFn.init();
		invoiceEditFn.onCloseClick();
		// submit button event
		this.onSumitNewRegister();
		this.registerAutoInvoiceNumber();
	}
	, registerAutoInvoiceNumber: function() {
		// change event on check box
		$("input[name=autoInvoiceNumber]:checkbox").on("change", function(){
			var invoiceNumberCtrl = $("input[name=invoiceNumber]");
			var isChecked = $(this).is(":checked");
			if ( isChecked ) {
				// if selected, should disable invoice number input and not mandatory
				invoiceNumberCtrl.removeAttr("mandatory");
				invoiceNumberCtrl.val("");
				invoiceNumberCtrl.attr('disabled','disabled');
			}else{
				// if not, enable invoice number input
				invoiceNumberCtrl.attr("mandatory", "y");
				invoiceNumberCtrl.removeAttr('disabled');
			}
		});
		// focus lost event on invoice number input
		$("input[name=invoiceNumber]").on("blur", function() {
			// check hidden invoice number, if empty, ajax call
			var invoiceNumberHidden = $("input[name=invoiceNumberHidden]").val();
			var invoiceNumberCurrent = $(this).val();
			if ( invoiceNumberHidden == "" ) {
				// ajax call to validate current value
				invoiceNewFn.validateInvoiceNumber(invoiceNumberCurrent);
			}else{
				// if not empty, compare current value, if different, ajax call
				if ( invoiceNumberHidden != invoiceNumberCurrent ) {
					invoiceNewFn.validateInvoiceNumber(invoiceNumberCurrent);
				}
			}
		});
	}
	//call on input invoice number focus lost
	, validateInvoiceNumber: function(invoiceNumberCurrent) {
		var isOK = true;
		var param = "invoiceNumber=" + invoiceNumberCurrent;
		$.ajax({
			type: "GET",
			url: "/invoice/invoiceValidate",
			data: param,
			success: function(responseText, statusText) {
				if ( responseText == "success" ) {
					// update hidden invoice number
					$("input[name=invoiceNumberHidden]").val(invoiceNumberCurrent);
				}else{
					isOK = false;
					alert(responseText);
					// set focus back to invoice number and clear value
					$("input[name=invoiceNumber]").val("").focus();
				}
			}
		});
		return isOK;
	}
	, validateForm: function() {
		var isOK = invoiceEditFn.validateForm();
		if ( isOK ) {
			// only for new
			isOK = orderItemFn.validate();
		}
		return isOK;
	}
	, onSumitNewRegister: function() {
		// refresh body
		var options = { 
	        target: "#"+microBizConst.bodyContentId
	        , beforeSubmit: function() {
	        	return invoiceNewFn.validateForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// load invoice details page
	        	// put it in the page for short cut from customer
	        	//invoiceDetailFn.init();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=invoiceDetailInfoNewForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
}

//on edit tab selected
var invoiceUpdateFn = {
	init: function() {
		invoiceEditFn.init();
		// submit button event
		this.onSumitEditRegister();
	}
	, onSumitEditRegister: function() {
		// go to order tab and update info DIV
		var options = { 
	        target: "#invoiceInfoDIV"
	        , beforeSubmit: function() {
	        	return invoiceEditFn.validateForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// show order tab
	        	var tabType = "Order";
	        	invoiceDetailFn.setTabSelected(tabType);
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=invoiceDetailInfoEditForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}	
}
// common function for new and edit page
var invoiceEditFn = {
	init: function() {
		this.initPage();
		microBizFn.onCustomerSelectChange();
		this.fieldValidate();
	}
	, initPage: function() {
		$( "#signDateStr" ).datepicker();
		$( "#preferIntlDateStr" ).datepicker();
	}
	, fieldValidate: function() {
		//$("input[valueType=price]").alphanumeric({allow:"."});
		$("input[valueType=price]").numeric();
	}
	, onCloseClick: function() {
		// back to invoice list page 
		$("button[btnAction=invoiceEditClose]").click(function(){
			window.location.href = "/invoice/invoice";
		});
	}
	, validateForm: function() {
		// check customer cannot be empty, set to mandatory
		return microBizFn.validateForm();
	}
}

var invoiceDetailFn = {
	init: function() {
		// register event for three tabs
		this.registerTabClick();
		// close button
		this.onDetailPageClose();
		// register event for edit job link
		this.onInvoiceJobEditClick();
		this.onInvoiceExpenseEditClick();
		this.registerOrderForm();
		this.onInvoicePaymentEditClick();
	}
	, onDetailPageClose: function() {
		$("a[link=invoiceEditClose]").click(function(){
			window.location.href = "/invoice/invoice";
		});
	}
	, registerOrderForm: function() {
		// after submit, reload tab content
		var options = { 
	        target: "#invoiceDetailOrderDIV"
	        , beforeSubmit: function() {
	        	// set invoiceKey value
	        	$("#invoiceDetailOrderDIV").html("Saving...");
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// register event again
	        	invoiceDetailFn.registerOrderForm();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#invoiceDetailOrderForm").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	// tab event
	, registerTabClick: function() {
		$("a[link^=invoiceDetail]").click(function(){
			if ( !invoiceDetailFn.isTabSelected($(this)) ) {
				// hide three DIVs first
				$("div[id^=invoiceDetail]").hide();
				// reset selected flag
				var tabType = $(this).attr("link");
				// also need invoice key to get job list after submit
				eval("invoiceDetailFn."+tabType+"Display()");
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
		});
	}
	, invoiceDetailInfoDisplay: function() {
		var ctrl = $("#invoiceDetailInfoDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var invoiceKey = ctrl.attr("invoiceKey");
			ctrl.load("/invoice/invoiceEdit?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceUpdateFn.init();
			})
			.attr("hasContent", "y");
		}
	}
	, invoiceDetailPaymentDisplay: function() {
		var ctrl = $("#invoiceDetailPaymentDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var invoiceKey = ctrl.attr("invoiceKey");
			ctrl.load("/invoice/invoicePayments?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceDetailFn.paymentListPageInit();
			})
			.attr("hasContent", "y");
		}
	}
	, invoiceDetailExpenseDisplay: function() {
		var ctrl = $("#invoiceDetailExpenseDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var invoiceKey = ctrl.attr("invoiceKey");
			ctrl.load("/invoice/invoiceExpenses?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceDetailFn.onInvoiceExpenseEditClick();
			})
			.attr("hasContent", "y");
		}
	}
	, invoiceDetailOrderDisplay: function() {
		
	}
	, invoiceDetailJobDisplay: function() {
		var ctrl = $("#invoiceDetailJobDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var invoiceKey = ctrl.attr("invoiceKey");
			ctrl.load("/invoice/invoiceJobs?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceDetailFn.onInvoiceJobEditClick();
			})
			.attr("hasContent", "y");
		}
	}
	// assign job button and edit link
	, onInvoiceJobEditClick: function() {
		$("a[link=invoiceJobEdit]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var jobKey = $(this).attr("jobKey");
			invoiceDetailFn.onNewButtonClick("Job", jobKey);
			$("#invoiceDetailJobDIV").load("/invoice/invoiceJobEdit?jobKey="+jobKey+"&invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.registerJobDetailForm();
				invoiceDetailFn.onInvoiceJobEditClose();
			});
		});
	}
	// on job detail page
	, onInvoiceJobEditClose: function() {
		$("a[link=invoiceEditCloseJob]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			// just load job list 
			$("#invoiceDetailJobDIV").load("/invoice/invoiceJobList?invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.onInvoiceJobEditClick();
			});
		});
	}
	, registerJobDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailJobDIV"
	        , beforeSubmit: function(){
	        	// set invoiceKey value
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// register event again
	        	invoiceDetailFn.onInvoiceJobEditClick();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#invoiceDetailJobDetailForm").submit(function() { 
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
	// Order, Payment, Job, Info, Expense
	, setTabSelected: function(tabType) {
		// hide three DIVs first
		$("div[id^=invoiceDetail]").hide();
		$("a[link^=invoiceDetai]").parent().removeAttr("class");
		// set tab selected
		$("a[link=invoiceDetail"+tabType+"]").parent().addClass("active");
		$("div[id=invoiceDetail"+tabType+"DIV]").show();
	}
	, onNewButtonClick: function(type, key) {
		// when new payment and assign job button click, consider the current tab
		if ( key == "-1" ) {
			// if payment tab is not selected
			var ctrl = $("a[link=invoiceDetail"+type+"]");
			if ( !this.isTabSelected(ctrl) ) {
				// tab
				var ctrlArr = $("a[link^=invoiceDetail]");
				ctrlArr.parent().removeAttr("class");
				ctrl.parent().attr("class", "active");
				// DIV
				ctrlArr = $("div[type=tab][id^=invoiceDetail]");
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
	//======== Expense ===================
	// new expense button and edit link
	, onInvoiceExpenseEditClick: function() {
		$("a[link=invoiceExpenseEdit]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var expenseKey = $(this).attr("expenseKey");
			invoiceDetailFn.onNewButtonClick("Expense", expenseKey);
			$("#invoiceDetailExpenseDIV").load("/invoice/invoiceExpenseEdit?expenseKey="+expenseKey+"&invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.initExpenseDetailPage();
			});
		});
	}
	// on job detail page
	, onInvoiceExpenseEditClose: function() {
		$("a[link=invoiceEditCloseExpense]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			// just load job list 
			$("#invoiceDetailExpenseDIV").load("/invoice/invoiceExpenses?invoiceKey="+invoiceKey, function() {
				invoiceDetailFn.onInvoiceExpenseEditClick();
			});
		});
	}
	, registerExpenseDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailExpenseDIV"
	        , beforeSubmit: function(){
	        	// set invoiceKey value
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// register event again
	        	invoiceDetailFn.onInvoiceExpenseEditClick();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#invoiceDetailExpenseDetailForm").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
    }
	, initExpenseDetailPage: function() {
		// init expense page
		microBizFn.initPriceInput();
		$( "#reportDateStr" ).datepicker();
		
		this.registerExpenseDetailForm();
		this.onInvoiceExpenseEditClose();
	}
}
