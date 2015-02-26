var dashboardFn = {
	init: function() {

		this.onUnpaidInvoicesClick();
		this.onOngoingInvoicesClick();
		this.onUnapprovedJobReportsClick();
		this.onUncompleteJobsClick();
		this.onJobReportEditClick();
		this.onJobReportApproveClick();
		
	}
		
	

	
	, onUnpaidInvoicesClick: function() {
		$("a[link=unapprovedJobReports]").click(function() {
			$("#unApprovedJobReportsDIV").load("/manager/unApprovedJobReports", function() {

			});
		});
	}
	
	, onOngoingInvoicesClick: function() {
		$("a[link=unapprovedJobReports]").click(function() {
			$("#unApprovedJobReportsDIV").load("/manager/unApprovedJobReports", function() {

			});
		});
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
