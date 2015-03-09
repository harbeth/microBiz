var dashboardFn = {
	init: function() {
		// view details link
		this.registerSectionClick();
		unPaidInvoiceFn.initUnpaidPage();
	}
	// for four sections: unpaid, ungoing, 
	, registerSectionClick: function() {
		$("a[link^=managerTab]").click(function(){
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
				uncompleteJobFn.initUncomplatePage();
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
				dashboardFn.onJobReportApproveClick();
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
				jobReportEditFn.init(); 
			});
		});
	}
	, onJobReportApproveClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=managerJobReportApprove]").click(function(){
			var jobReportKey = $(this).attr("jobReportKey");
			// also need invoice key to get details
			$("#dashboardWorkingAreaDIV").load("/manager/managerJobReportApprove?jobReportKey=" + jobReportKey, function() {
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
	        	$("#managerTabOngoingInvoiceDIV").html("");
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// clear edit payment DIV
	        	$("#managerInvoiceDetailDIV").html("");
	        	// register edit link
	        	ongoingInvoiceFn.initOngoingPage();
	        	ongoingInvoiceFn.updateOngoingSection();
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
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// clear edit payment DIV
	        	$("#managerInvoiceDetailDIV").html("");
	        	// register edit link
	        	ongoingInvoiceFn.initOngoingPage();
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
		var ctrl = $("a[link=managerTabOngoingInvoice]");
		var selected = ctrl.attr("nowSelected");
		$("#ongoingInvoiceSectionDIV").load("/manager/updateOngoingInvoiceSection", function() {
			ctrl.attr("nowSelected", selected);
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
	        	return true;
	        }
	        , success: function(){
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
		});
	}
}

var uncompleteJobFn = {
	initUncomplatePage: function() {
		this.onEditJobClick();
	}
	, registerJobSubmit: function() {
		// refresh invoice list
		var options = { 
	        target: "#managerTabUnCompleteJobDIV"
	        , beforeSubmit: function() {
	        	return true;
	        }
	        , success: function(responseText, statusText, xhr, $form){
	        	// clear edit payment DIV
	        	$("#managerInvoiceDetailDIV").html("");
	        	// register edit link
	        	uncompleteJobFn.initUncomplatePage();
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
		var ctrl = $("a[link=managerTabUnCompleteJob");
		var selected = ctrl.attr("nowSelected");
		$("#managerTabUnCompleteJobDIV").load("/manager/updateUncomplateJobSection", function() {
			ctrl.attr("nowSelected", selected);
		});
	}
}


var jobReportEditFn = {
	init: function() {
		// submit button event
		this.onSumitRegister();
	}
	, onSumitRegister: function() {
		// refresh body
		var options = { 
	        target: "#"+microBizConst.bodyContentId

	        , success: function(responseText, statusText, xhr, $form){
	        	
	        }
	    }; 
	    // bind to the form's submit event 
	    $("form[name=jobReportForm]").submit(function() { 
	    	$(this).ajaxSubmit(options); 
	        // !!! Important !!! 
	        // always return false to prevent standard browser submit and page navigation 
	        return false; 
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
				salesCommissionFn.registerSalesForm();
			});
		});
	}
	, registerSalesForm: function() {
		// after submit, reload tab content
		var options = { 
	        beforeSubmit: function() {
	        	return true;
	        }
	        , success: function(responseText){
	        	//if ( responseText == "success" ) {
	        		// should trigger value change event
	        		//alert(responseText);
	        		$("#selectSales").val("-1");
	        		$("#invoicesForSalesCommissionDIV").html("");
	        	//}
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