var dashboardFn = {
	init: function() {
		// view details link
		this.registerSectionClick();
		unPaidInvoiceFn.initUnpaidPage();
	}
	// for four sections: unpaid, ungoing, 
	, registerSectionClick: function() {
		this.registerSectionClickWithCtrl($("a[link^=managerTab]"));
	}
	, registerSectionClickWithCtrl: function(ctrl) {
		ctrl.click(function(){
			// check if not list or not selected
			if ( !dashboardFn.isSectionSelected($(this)) ) {
				dashboardFn.setSelected($(this));
				// hide three DIVs first
				$("div[id^=managerTab]").hide();
				// reset selected flag
				var tabType = $(this).attr("link");
				// also need invoice key to get job list after submit
				eval("dashboardFn."+tabType+"Display()");
				// show the current tab DIV
				$("#"+tabType+"DIV").show();
			}
		});
	}
	, isSectionSelected: function(ctrlLink) {
		return ctrlLink.attr("nowSelected") == "y";
	}
	, setSelected: function(ctrlLink) {
		var ctrlArr = $("a[link^=managerTab]");
		ctrlArr.attr("nowSelected", "n");
		ctrlLink.attr("nowSelected", "y");
	}
	, managerTabUnpaidInvoiceDisplay: function() {
		// already loaded
	}
	, managerTabOngoingInvoiceDisplay: function() {
		var ctrl = $("#managerTabOngoingInvoiceDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			ctrl.load("/manager/managerOngoingInvoice", function(){
				// after edit tab loaded
				ongoingInvoiceFn.initOngoingPage();
			})
			.attr("hasContent", "y");
		}else{
			// show list view
			//invoiceDetailFn.invoicePaymentListImpl(invoiceKey);
		}
	}
	, managerTabUnCompleteJobDisplay: function() {
		var ctrl = $("#managerTabUnCompleteJobDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			ctrl.load("/manager/managerUncompleteJob", function(){
				// after edit tab loaded
				uncompleteJobFn.initUncompletePage();
			})
			.attr("hasContent", "y");
		}else{
			// show list view
			//invoiceDetailFn.invoicePaymentListImpl(invoiceKey);
		}
	}
	, managerTabUnApprovedJobReportDisplay: function() {
		var ctrl = $("#managerTabUnApprovedJobReportDIV");
		var hasContent = ctrl.attr("hasContent");
		if ( hasContent == 'n' ) {
			// load content first
			ctrl.load("/manager/unApprovedJobReports", function(){
				dashboardFn.onJobReportEditClick();
			})
			.attr("hasContent", "y");
		}else{
			// show list view
			//invoiceDetailFn.invoicePaymentListImpl(invoiceKey);
		}
	}
	, onJobReportEditClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=managerJobReportEdit]").click(function(){
			var jobReportKey = $(this).attr("jobReportKey");
			// also need invoice key to get details
			$("#dashboardWorkingAreaDIV").load("/manager/managerJobReportEdit?jobReportKey=" + jobReportKey, function() {
				verifyJobReportFn.initVerifyJobReportPage(); 
				verifyJobReportFn.updateUnApprovedJobReportSection();
			});
		});
	}

}

var ongoingInvoiceFn = {
	initOngoingPage: function() {
		this.onEditInvoiceClick();
		this.onAssignJobClick();
	}
	, registerInvoiceSubmit: function() {
		// refresh invoice list
		var options = { 
	        target: "#managerTabOngoingInvoiceDIV"
	        , beforeSubmit: function() {
	        	microBizFn.setSubmitBtnStatus(false);
	        	$("#managerTabOngoingInvoiceDIV").html("");
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// clear edit payment DIV
	        	$("#managerInvoiceDetailDIV").html("");
	        	// register edit link
	        	ongoingInvoiceFn.initOngoingPage();
	        	ongoingInvoiceFn.updateOngoingSection();
	        	// after close invoice, update unpaid section
	        	unPaidInvoiceFn.updateUnpaidSection();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=managerInvoiceDetailForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	//edit link on unpaid invoice list
	, onEditInvoiceClick: function() {
		// show new payment detail
		$("a[link=managerInvoiceEdit]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var ctrl = $("#managerInvoiceDetailDIV");
			var invoiceKeyOld = ctrl.attr("invoiceKey");
			if( ctrl.attr("hasContent") == "n" || ctrl.attr("hasContent") != "edit" || invoiceKey != invoiceKeyOld ) {
				ctrl.load("/manager/managerInvoiceEdit?invoiceKey="+invoiceKey, function() {
					ongoingInvoiceFn.registerInvoiceSubmit();
				});
				ctrl.attr("hasContent", "edit").attr("invoiceKey", invoiceKey);
			}
		});
	}
	, registerJobSubmit: function() {
		// refresh invoice list
		var options = { 
	        target: "#managerTabOngoingInvoiceDIV"
	        , beforeSubmit: function() {
	        	microBizFn.setSubmitBtnStatus(false);
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	// clear edit payment DIV
	        	$("#managerInvoiceDetailDIV").html("");
	        	// register edit link
	        	ongoingInvoiceFn.initOngoingPage();
	        	uncompleteJobFn.updateUncompleteJobSection();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=managerJobDetailForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	, onAssignJobClick: function() {
		$("a[link=managerInvoiceJobAssign]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var ctrl = $("#managerInvoiceDetailDIV");
			var invoiceKeyOld = ctrl.attr("invoiceKey");
			if( ctrl.attr("hasContent") == "n" || ctrl.attr("hasContent") != "assign" || invoiceKey != invoiceKeyOld ) {
				ctrl.load("/manager/managerInvoiceAssignJob?jobKey=-1&invoiceKey="+invoiceKey, function() {
					ongoingInvoiceFn.registerJobSubmit();
				});
				ctrl.attr("hasContent", "assign").attr("invoiceKey", invoiceKey);
			}
		});
	}
	, updateOngoingSection: function() {
		var selected = $("a[link=managerTabOngoingInvoice]").attr("nowSelected");
		$("#ongoingInvoiceSectionDIV").load("/manager/updateOngoingInvoiceSection", function() {
			$("a[link=managerTabOngoingInvoice]").attr("nowSelected", selected);
			dashboardFn.registerSectionClickWithCtrl($("a[link=managerTabOngoingInvoice]"));
		});
	}
}

var unPaidInvoiceFn = {
	initUnpaidPage: function() {
		this.onReceivePaymentClick();
	}
	, registerPaymentSubmit: function() {
		// refresh invoice list
		var options = { 
	        target: "#managerTabUnpaidInvoiceDIV"
	        , beforeSubmit: function() {
	        	var isOK = microBizFn.validateForm();
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	return isOK;
	        }
	        , success: function() {
	        	microBizFn.setSubmitBtnStatus(true);
	        	// clear edit payment DIV
	        	$("#managerPaymentDetailDIV").html("");
	        	// register edit link
	        	unPaidInvoiceFn.initUnpaidPage();
	        	// update unpaid section
				unPaidInvoiceFn.updateUnpaidSection();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=managerPaymentDetailForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	//edit link on unpaid invoice list
	, onReceivePaymentClick: function() {
		// show new payment detail
		$("a[link=managerInvoicePaymentNew]").click(function() {
			var invoiceKey = $(this).attr("invoiceKey");
			var ctrl = $("#managerPaymentDetailDIV");
			var invoiceKeyOld = ctrl.attr("invoiceKey");
			if( ctrl.attr("hasContent") == "n" || invoiceKey != invoiceKeyOld ) {
				ctrl.load("/manager/managerPaymentEdit?paymentKey=-1&invoiceKey="+invoiceKey, function() {
					unPaidInvoiceFn.registerPaymentSubmit();
				});
				ctrl.attr("hasContent", "y").attr("invoiceKey", invoiceKey);
			}
		});
	}
	, updateUnpaidSection: function() {
		//<a link="managerTabUnpaidInvoice" nowSelected="y"
		var ctrl = $("a[link=managerTabUnpaidInvoice");
		var selected = ctrl.attr("nowSelected");
		$("#unpaidInvoiceSectionDIV").load("/manager/updateUnpaidSection", function() {
			ctrl.attr("nowSelected", selected);
			dashboardFn.registerSectionClickWithCtrl(ctrl);
		});
	}
}

var uncompleteJobFn = {
	initUncompletePage: function() {
		this.onEditJobClick();
	}
	, registerJobSubmit: function() {
		// refresh invoice list
		var options = { 
	        target: "#managerTabUnCompleteJobDIV"
	        , beforeSubmit: function() {
	        	microBizFn.setSubmitBtnStatus(false);
	        	$("#managerTabUnCompleteJobDIV").html("");
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        	$("#managerJobDetailDIV").html("");
	        	// register edit link
	        	uncompleteJobFn.initUncompletePage();
	        	uncompleteJobFn.updateUncompleteJobSection();
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=managerJobDetailForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
	//edit link on unpaid invoice list
	, onEditJobClick: function() {
		// show new payment detail
		$("a[link=managerJobEdit]").click(function() {
			var jobKey = $(this).attr("jobKey");
			var ctrl = $("#managerJobDetailDIV");
			var jobKeyOld = ctrl.attr("jobKey");
			if( ctrl.attr("hasContent") == "n" || jobKey != jobKeyOld) {
				ctrl.load("/manager/managerJobEdit?jobKey="+jobKey, function() {
					uncompleteJobFn.registerJobSubmit();
				});
				ctrl.attr("hasContent", "y").attr("jobKey", jobKey);
			}
		});
	}
	, updateUncompleteJobSection: function() {
		var selected = $("a[link=managerTabUnCompleteJob]").attr("nowSelected");
		$("#uncompleteJobSectionDIV").load("/manager/updateUncompleteJobSection", function() {
			$("a[link=managerTabUnCompleteJob]").attr("nowSelected", selected);
			dashboardFn.registerSectionClickWithCtrl($("a[link=managerTabUnCompleteJob]"));
		});
	}
}

var verifyJobReportFn = {
	initVerifyJobReportPage: function() {
		this.onSumitRegister();
	}
	, onSumitRegister: function() {
		// refresh body
		var options = { 
	        target: "#managerTabUnApprovedJobReportDIV"
	        , beforeSubmit: function() {
	        	var isOK = microBizFn.validateForm();
	        	if ( isOK ) {
	        		microBizFn.setSubmitBtnStatus(false);
	        	}
	        	return isOK;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        }
	    }; 
		 $("form[name=jobReportForm]").submit(function() { 
		    	$(this).ajaxSubmit(options); 
		        // !!! Important !!! 
		        // always return false to prevent standard browser submit and page navigation 
		        return false; 
		 }); 
	  }
	, updateUnApprovedJobReportSection: function() {
		var selected = $("a[link=managerTabUnApprovedJobReport]").attr("nowSelected");
		$("#unapprovedJobReportSectionDIV").load("/manager/updateUnApprovedJobReportSection", function() {
			$("a[link=managerTabUnApprovedJobReport]").attr("nowSelected", selected);
			dashboardFn.registerSectionClickWithCtrl($("a[link=managerTabUnApprovedJobReport]"));
		});
	}
}

var salesCommissionFn = {
	init: function() {
		this.salesSelectChange();
	}
	, salesSelectChange: function() {
		$("#selectSales").change(function(){
			// update lower DIV
			//alert("select sales");
			var sales = $(this).val();
			$("#invoicesForSalesCommissionDIV").html("Loading...");
			$("#invoicesForSalesCommissionDIV").load("/manager/loadInvoicesForSalesCommission?sales=" + sales, function(){
				salesCommissionFn.saleCommissionPageInit();
			});
		});
	}
	, saleCommissionPageInit: function() {
		this.registerSalesForm();
		microBizFn.initPriceInput();
	}
	, registerSalesForm: function() {
		// after submit, reload tab content
		var options = { 
			target: "#invoicesForSalesCommissionDIV"	
	        , beforeSubmit: function() {
	        	microBizFn.setSubmitBtnStatus(false);
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	microBizFn.setSubmitBtnStatus(true);
	        }
	    }; 
	    // bind to the form's submit event 
	    $("#salesCommissionSelectForm").submit(function() { 
	        $(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
	    }); 
	}
}