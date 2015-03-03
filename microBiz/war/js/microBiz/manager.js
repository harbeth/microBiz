var dashboardFn = {
	init: function() {
		// view details link
		this.registerSectionClick();
		unPaidInvoiceFn.initUnpaidPage();
		//
		//this.onOngoingInvoicesClick();
		//this.onUnapprovedJobReportsClick();
		//this.onUncompleteJobsClick();
		//this.onJobReportEditClick();
		//this.onJobReportApproveClick();
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
	, onUnapprovedJobReportsClick: function() {
		$("a[link=unApprovedJobReports]").click(function() {
			$("#unApprovedJobReportsDIV").load("/manager/unApprovedJobReports", function() {

			});
		});
	}
	, onUncompleteJobsClick: function() {
		$("a[link=unCompleteJobs]").click(function() {
			$("#unCompleteJobsDIV").load("/manager/unCompleteJobs", function() {
			});
		});
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
	}
	, registerInvoiceSubmit: function() {
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
	        	ongoingInvoiceFn.onEditInvoiceClick();
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
			var ctrl = $("#managerInvoiceDetailDIV");
			if( ctrl.attr("hasContent") == "n" ) {
				var invoiceKey = $(this).attr("invoiceKey");
				ctrl.load("/manager/managerInvoiceEdit?invoiceKey="+invoiceKey, function() {
					ongoingInvoiceFn.registerInvoiceSubmit();
				});
				ctrl.attr("hasContent", "y");
			}
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
	        , success: function(responseText, statusText, xhr, $form){
	        	// clear edit payment DIV
	        	$("#managerPaymentDetailDIV").html("");
	        	// register edit link
	        	unPaidInvoiceFn.onReceivePaymentClick();
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
			var ctrl = $("#managerPaymentDetailDIV");
			if( ctrl.attr("hasContent") == "n" ) {
				var invoiceKey = $(this).attr("invoiceKey");
				ctrl.load("/manager/managerPaymentEdit?paymentKey=-1&invoiceKey="+invoiceKey, function() {
					ongoingInvoiceFn.registerInvoiceSubmit();
				});
				ctrl.attr("hasContent", "y");
			}
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
	        	uncompleteJobFn.onEditJobClick();
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
			var ctrl = $("#managerJobDetailDIV");
			if( ctrl.attr("hasContent") == "n" ) {
				var jobKey = $(this).attr("jobKey");
				ctrl.load("/manager/managerJobEdit?jobKey="+jobKey, function() {
					uncompleteJobFn.registerJobSubmit();
				});
				ctrl.attr("hasContent", "y");
			}
		});
	}
}


var jobReportEditFn = {
		init: function() {
			this.onCloseClick();
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
		, onCloseClick: function() {
			// back to invoice list page 
			$("a[link=quoteEditClose]").click(function(){
				window.location.href = "/quote";
			});
		}
}
