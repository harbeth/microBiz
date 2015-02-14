var quoteFn = {
	init: function() {
		// register event for edit invoice link
		this.onQuoteDetailClick();
		this.onQuoteNewClick();
	}
	, onQuoteDetailClick: function() {
		// invoice number link to sh invoice details on AJAX call in the body panel
		$("a[link=quoteEditDetail]").click(function(){
			var quoteKey = $(this).attr("quoteKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/quote/quoteDetails?quoteKey=" + quoteKey, function() {
				// put it in the page for short cut from customer
				//quoteDetailFn.init(); 
			});
		});
	}
	, onQuoteNewClick: function() {
		// new invoice button in the invoice list page
		$("a[link=quoteNew]").click(function(){
			// after get invoice key, get details page
			// new and edit should be the same for quote
			$("#"+microBizConst.bodyContentId).load("/quote/quoteCreate", function() {
				// job page is shown by default
				// put it in the page for short cut from customer
				// quoteNewFn.init(); 
			});
		});
	}
}

var quoteNewFn = {
	// put it in the page for short cut from customer
	init: function() {
		quoteEditFn.init();
		// submit button event
		this.onSumitNewRegister();
	}
	, onSumitNewRegister: function() {
		// update whole body with edit page
		var options = { 
	        target: "#"+microBizConst.bodyContentId
	        , beforeSubmit: function() {
	        	//keep product -1
	        	return quoteNewFn.validate();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// after edit page loaded
	        	// put it in the page for short cut from customer
	        	// quoteDetailFn.init();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=quoteDetailInfoNewForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	, validate: function() {
		// check basic info first
    	var isOK = quoteEditFn.validateForm();
    	if ( isOK ) {
    		// check order item form
    		// must have at least one order item
    		isOK = quoteDetailFn.validateOrderForm();
    	}
    	return isOK;
	}
}

//on edit tab selected
var quoteUpdateFn = {
	init: function() {
		quoteEditFn.init();
		// submit button event
		this.onSumitEditRegister();
	}
	, onSumitEditRegister: function() {
		// keep the same tab and update info DIV
		var options = { 
	        target: "#quoteInfoDIV"
	        , beforeSubmit: function() {
	        	// validate detail info
	        	return quoteEditFn.validateForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// keep on the order tab
	        	$("#quoteDetailInfoDIV").hide();
	        	$("#quoteDetailVersionDIV").show();
	        	$("a[link=quoteDetailVersion]").parent().attr("class", "active");
	        	$("a[link=quoteDetailInfo]").parent().removeAttr("class");
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=quoteDetailInfoEditForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}	
}
// common functions for new and edit page
var quoteEditFn = {
	init: function() {
		microBizFn.onCustomerSelectChange();
		this.fieldValidate();
	}
	, fieldValidate: function() {
		//$("input[valueType=price]").alphanumeric({allow:"."});
		$("input[valueType=price]").numeric();
	}
	, onCloseClick: function() {
		// back to invoice list page 
		$("a[link=quoteEditClose]").click(function(){
			window.location.href = "/quote/quote";
		});
	}
	, validateForm: function() {
		return microBizFn.validateForm();
	}
}

var quoteDetailFn = {
	init: function() {
		quoteEditFn.onCloseClick();
		// more than one submit button doesn't work, use normal button and post
		//this.onSumitOrderRegister();
		// JQUERY post doesn't work
		//this.registerSaveButton();
		this.registerQuoteOrderChange();
		// register event for three tabs
		this.registerTabClick();
	}
    // just validate order items in detail page
	, validateOrderForm: function() {
		return orderItemFn.validate();
	}
	// events on the version tab
	// only update version tab
	, onSumitOrderRegister: function() {
		// update whole body with edit page
		var options = { 
	        target: "#quoteDetailVersionDIV"
	        , beforeSubmit: function() {
	        	// just validate order item
	        	return quoteDetailFn.validateOrderForm();
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// refresh the current tab, register change event
	        	quoteDetailFn.registerQuoteOrderChange();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=quoteDetailOrderForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	// not used now
	, registerSaveButton: function() {
		$("button[btnAction=save]").on("click", function(){
			var actionName = $(this).attr("name");
			var formParam = $("form[name=quoteDetailVersionForm]").serialize();
			formParam += "actionName="+actionName+"&";
			$.ajax({
				type: "POST",
				url: "/quote/quoteVersionAction",
				data: formParam,
				success: function(responseText, statusText) {
					$("#"+microBizConst.bodyContentId).html(responseText);
				}
			});
		});
	}
	, registerQuoteOrderChange: function() {
		$("#quoteOrder").change(function(){
			// update lower DIV
			var quoteOrderKey = $(this).val();
			$("#quoteOrderChangeDIV").html("Loading...");
			$("#quoteOrderChangeDIV").load("/quote/quoteOrder?quoteOrderKey=" + quoteOrderKey, function(){
				// register form again
				quoteDetailFn.onSumitOrderRegister();
				//quoteDetailFn.registerQuoteOrderChange();
			});
		});
	}
	// tab event
	, registerTabClick: function() {
		$("a[link^=quoteDetail]").click(function(){
			if ( !quoteDetailFn.isTabSelected($(this)) ) {
				// hide three DIVs first
				$("div[id^=quoteDetail]").hide();
				// reset selected flag
				var tabType = $(this).attr("link");
				// also need invoice key to get job list after submit
				eval("quoteDetailFn."+tabType+"Display()");
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
		});
	}
	// need to load for the bean binding
	, quoteDetailInfoDisplay: function() {
		var ctrl = $("#quoteDetailInfoDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			var quoteKey = ctrl.attr("quoteKey");
			ctrl.load("/quote/quoteEdit?quoteKey=" + quoteKey, function(){
				// after edit tab loaded
				quoteUpdateFn.init();
			})
			.attr("hasContent", "y");
		}
	}
	, quoteDetailVersionDisplay: function() {
		// do nothing, already has content
	}
	// ======= END Job =====================
	, isTabSelected: function(ctrlLink) {
		return ctrlLink.parent().hasClass("active");
	}
}
