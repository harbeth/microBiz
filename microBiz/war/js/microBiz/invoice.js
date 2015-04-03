var invoiceFn = {
	init: function() {
		// register event for edit invoice link
		this.onInvoiceDetailClick();
		this.onInvoiceNewClick();
		this.onInvoiceSearchSubmit();
		this.onClearSearchClick();
		this.onPaginateClick();
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
	, onClearSearchClick: function() {
		
		$("a[link=clearInvoiceSearch]").click(function(){
		
			$("input[name=searchInvByInvNo]").val('');
			$("input[name=searchInvByAddr]").val('');
			$("select[name=searchStatus]").val(0);
			// also need invoice key to get details
			$("#invoiceListDIV").load("/invoice/invoiceSearch", function() {
				invoiceFn.init(); 
			});
		});
	}
	, onPaginateClick: function() {
		
		$("a[link=paginating]").click(function(){
			var pageNo = $(this).attr("pageNo");
			var invNoStr = $("input[name=searchInvByInvNo]").val();
			var addrStr = $("input[name=searchInvByAddr]").val();
			var status = $("select[name=searchStatus]").val();
			var param = "?pageNo="+pageNo+"&searchStatus="+status;
			if(invNoStr){
				param += "&searchInvByInvNo="+invNoStr;
			}else{
				if(addrStr){
					param += "&searchInvByAddr="+addrStr;
				}
			}
			
			// also need invoice key to get details
			$("#invoiceListDIV").load("/invoice/invoiceSearch"+param, function() {
				invoiceFn.init(); 
			});
		});
	}
	, onInvoiceNewClick: function() {
		// new invoice button in the invoice list page
		$("a[link=invoiceNew]").click(function(){
			// after get invoice key, get details page
			$("#"+microBizConst.bodyContentId).load("/invoice/invoiceCreate", function() {
				
			});
		});
	}
	, onInvoiceSearchSubmit: function() {
		
		var options = { 
	        target: "#invoiceListDIV"
	        , success: function(responseText, statusText, xhr, $form){
	        	invoiceFn.init(); 
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

// for invoice info page,  new invoice page and edit invoice page "info" tab
var invoiceNewFn = {
	// put it in the page for short cut from customer
	init: function() {
		invoiceEditFn.init();
		invoiceEditFn.onCloseClick();
		// submit button event
		this.onSubmitNewRegister();
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
	, onSubmitNewRegister: function() {
		// refresh body
		var options = { 
	        target: "#"+microBizConst.bodyContentId
	        , beforeSubmit: function() {
	        	var isOK = invoiceNewFn.validateForm();
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
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
		this.onDetailCloseClick();
	}
	, onSumitEditRegister: function() {
		// go to order tab and update info DIV
		var options = { 
	        target: "#invoiceInfoDIV"
	        , beforeSubmit: function() {
	        	var isOK = invoiceEditFn.validateForm();
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// show order tab
	        	invoiceDetailFn.showOrderPage();
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
	, onDetailCloseClick: function() {
		// back to invoice list page 
		$("a[link=invoiceDetailEditClose]").click(function(){
			// show order tab
	    	invoiceDetailFn.showOrderPage();
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
	// put it on the page for portable
	, fieldValidate: function() {
		//$("input[valueType=price]").alphanumeric({allow:"."});
		//$("input[valueType=price]").numeric();
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
		// header button
		this.onEmailClick();
		// ===  detail tab ===
		// close button on detail tab
		this.onEditInvoiceClick();
		this.onOrderClick();
		this.onInvoiceLogEventClick();
		this.onDetailPageClose();
		this.registerOrderForm();
		this.registerEventLogForm();
		this.initManageTab();
	}
	, initManageTab: function() {
		// ===== manage tab ====
		// register event for edit job/payment/expense link
		this.onInvoiceJobEditClick();
		this.onInvoiceExpenseEditClick();
		this.onInvoicePaymentEditClick();
		// register event for three tabs
		this.registerSectionClick();
	}
	, onEmailClick: function() {
		$("a[link=emailToCustomer]").click(function(){
			var invoiceKey = $(this).attr("invoiceKey");
			$.ajax({
				type: "GET"
				, url: "/invoice/emailInvoice"
				, data: "invoiceKey="+invoiceKey
				, success: function(responseText) {
					alert(responseText);
				}
			});
		});
	}
	, onDetailPageClose: function() {
		$("a[link=invoiceEditClose]").click(function(){
			window.location.href = "/invoice/invoice";
		});
	}
	, registerOrderForm: function() {
		// after submit, reload tab content
		var options = { 
	        target: "#invoiceOrderDetailDIV"
	        , beforeSubmit: function() {
	        	// set invoiceKey value
	        	var isOK = orderItemFn.validate();
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	alert("Save successfully.");
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
	, onEditInvoiceClick: function() {
		$("a[link=invoiceEdit]").click(function(){
			// hide order DIV first
			$("#invoiceOrderDetailDIV").hide();
			$("#invoiceLogEventDIV").hide();
			$("a[link=invoiceOrder]").attr("nowSelected", "n");
			$("a[link=invoiceLogEvent]").attr("nowSelected", "n");
			$(this).attr("nowSelected", "y");
			// load content of the edit DIV, always edit latest
			var ctrl = $("#invoiceEditDetailDIV");
			var invoiceKey = $(this).attr("invoiceKey");
			// load content first
			ctrl.load("/invoice/invoiceEdit?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				$(this).attr("hasContent", "y");
				invoiceUpdateFn.init();
			});
			ctrl.show();
		});
	}
	
	, onInvoiceLogEventClick: function() {
		$("a[link=invoiceLogEvent]").click(function(){
			// hide order DIV first
			$("#invoiceOrderDetailDIV").hide();
			$("#invoiceEditDetailDIV").hide();
			$("a[link=invoiceOrder]").attr("nowSelected", "n");
			$("a[link=invoiceEdit]").attr("nowSelected", "n");
			$(this).attr("nowSelected", "y");
			
			var ctrl = $("#invoiceLogEventDIV");
			var invoiceKey = $(this).attr("invoiceKey");
			// load content first
			ctrl.load("/common/logEvent?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				
				$(this).attr("hasContent", "y");
				invoiceDetailFn.registerEventLogForm();
			});
			ctrl.show();
		});
	}
	, registerEventLogForm: function() {
		// after submit, reload tab content
		var options = { 
	        target: "#invoiceLogEventDIV"
	        , success: function(responseText, statusText, xhr, $form){
	        	invoiceDetailFn.registerEventLogForm();
	        }
	    }; 
	    // bind to the form's submit event 
		$("form[name=miLogForm]").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	, onOrderClick: function() {
		$("a[link=invoiceOrder]").click(function(){
			invoiceDetailFn.showOrderPage();
		});
	}
	, showOrderPage: function() {
		// clear edit DIV
		$("#invoiceEditDetailDIV").hide();
		$("#invoiceOrderDetailDIV").show();
		// selected
		$("a[link=invoiceOrder]").attr("nowSelected", "y");
		$("a[link=invoiceEdit]").attr("nowSelected", "n");
		$("a[link=invoiceLogEvent]").attr("nowSelected", "n");
		// set style
		$("a[link=invoiceOrder]").parent().addClass("active");
		$("a[link=invoiceEdit]").parent().removeClass("active");
		$("a[link=invoiceLogEvent]").parent().removeClass("active");
	}
	// ============ manage tab =================
	// if list page is show
	, isListPageShown: function() {
		var title = $("#detailPaneTitleSpan").text();
		// has list inside
		return title.indexOf("List") > -1;
	}
	, setDetailPanelTitle: function(title) {
		$("#detailPaneTitleSpan").text(title);
	}
	// on manage page
	, registerSectionClick: function() {
		$("a[link^=invoiceDetail]").click(function(){
			// check if not list or not selected
			if ( !invoiceDetailFn.isSectionSelected($(this)) 
					   || !invoiceDetailFn.isListPageShown() ) {
				invoiceDetailFn.setSelected($(this));
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
	, invoiceDetailPaymentDisplay: function() {
		var ctrl = $("#invoiceDetailPaymentDIV");
		var hasContent = ctrl.attr("hasContent");
		var invoiceKey = ctrl.attr("invoiceKey");
		if ( hasContent == 'n' ) {
			// load content first
			ctrl.load("/invoice/invoicePayments?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceDetailFn.paymentListPageInit();
			})
			.attr("hasContent", "y");
		}else{
			// show list view
			invoiceDetailFn.invoicePaymentListImpl(invoiceKey);
		}
	}
	, invoiceDetailExpenseDisplay: function() {
		var ctrl = $("#invoiceDetailExpenseDIV");
		var hasContent = ctrl.attr("hasContent");
		var invoiceKey = ctrl.attr("invoiceKey");
		if ( hasContent == 'n' ) {
			// load content first
			ctrl.load("/invoice/invoiceExpenses?invoiceKey=" + invoiceKey, function(){
				// after edit tab loaded
				invoiceDetailFn.onInvoiceExpenseEditClick();
			})
			.attr("hasContent", "y");
		}else{
			invoiceDetailFn.onInvoiceExpenseListImpl(invoiceKey);
		}
	}
	// just show job tab
	, invoiceDetailJobDisplay: function() {
		// show job list, check lager
		var ctrl = $("#invoiceDetailJobDIV");
		var invoiceKey = ctrl.attr("invoiceKey");
		invoiceDetailFn.onInvoiceJobListImpl(invoiceKey);
	}
	// assign job button and edit link, new/edit
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
			invoiceDetailFn.onInvoiceJobListImpl(invoiceKey);
		});
	}
	, onInvoiceJobListImpl: function(invoiceKey) {
		// just load job list 
		$("#invoiceDetailJobDIV").load("/invoice/invoiceJobList?invoiceKey="+invoiceKey, function() {
			invoiceDetailFn.onInvoiceJobEditClick();
			invoiceDetailFn.setDetailPanelTitle("Job List");
		});
	}
	, registerJobDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailJobDIV"
	        , beforeSubmit: function(){
	        	// set invoiceKey value
	        	microBizFn.setSubmitBtnStatus(false);
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// register event again
	        	invoiceDetailFn.onInvoiceJobEditClick();
	        	invoiceDetailFn.updateManageSummarySection();
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
	, isSectionSelected: function(ctrlLink) {
		return ctrlLink.attr("nowSelected") == "y";
	}
	, setSelected: function(ctrlLink) {
		var ctrlArr = $("a[link^=invoiceDetail]");
		ctrlArr.attr("nowSelected", "n");
		ctrlLink.attr("nowSelected", "y");
	}
	// Order, Payment, Job, Info, Expense
	// now used now
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
			if ( !this.isSectionSelected(ctrl) ) {
				// set selected
				this.setSelected(ctrl);
				// DIV
				var ctrlArr = $("div[type=tab][id^=invoiceDetail]");
				ctrlArr.hide();
				var tabType = ctrl.attr("link");
				// also need invoice key to get job list after submit
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
			invoiceDetailFn.setDetailPanelTitle("New " + type);
		}else{
			invoiceDetailFn.setDetailPanelTitle("Edit " + type);
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
			invoiceDetailFn.invoicePaymentListImpl(invoiceKey);
		});
	}
	, invoicePaymentListImpl: function(invoiceKey) {
		// just load job list 
		$("#invoiceDetailPaymentDIV").load("/invoice/invoicePaymentList?invoiceKey="+invoiceKey, function() {
			invoiceDetailFn.onInvoicePaymentEditClick();
			invoiceDetailFn.setDetailPanelTitle("Payment List");
		});
	}
	, registerPaymentDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailPaymentDIV"
	        , beforeSubmit: function(){
	        	var isOK = microBizFn.validateFormWithCtrl($(this));
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	// set invoiceKey value
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// load job list
	        	// register event again
	        	invoiceDetailFn.onInvoicePaymentEditClick();
	        	invoiceDetailFn.updateManageSummarySection();
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
			invoiceDetailFn.onInvoiceExpenseListImpl(invoiceKey);
		});
	}
	, onInvoiceExpenseListImpl: function(invoiceKey) {
		// just load expense list 
		$("#invoiceDetailExpenseDIV").load("/invoice/invoiceExpenses?invoiceKey="+invoiceKey, function() {
			invoiceDetailFn.onInvoiceExpenseEditClick();
			invoiceDetailFn.setDetailPanelTitle("Expense List");
		});
	}
	, registerExpenseDetailForm: function() {
    	// when job detail page is ready
		var options = { 
	        target: "#invoiceDetailExpenseDIV"
	        , beforeSubmit: function(){
	        	var isOK = microBizFn.validateFormWithCtrl($(this));
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	// set invoiceKey value
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// register event again
	        	invoiceDetailFn.onInvoiceExpenseEditClick();
	        	invoiceDetailFn.updateManageSummarySection();
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
	, updateManageSummarySection: function() {
		// get invoice key from outer div
		var invoiceKey = $('#invoiceDetailsInvoiceKeyHidden').val();
		$("#invoiceSummaryDIV").load("/invoice/updateManageSummarySection?invoiceKey="+invoiceKey, function() {
			invoiceDetailFn.initManageTab();
		});
	}
}
