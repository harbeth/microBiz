var jobReportFn = {
	init: function() {
		// register event for edit prd link
		this.onJobReportClick();
		this.onJobReportEditClick();

	}
	, onJobReportClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=jobReport]").click(function(){
			var jobKey = $(this).attr("jobKey");
			// also need invoice key to get details
			$("#jobReportEditDIV").load("/jobReport/jobReportNew?jobKey=" + jobKey, function() {
				jobReportEditFn.init(); 
			});
		});
	}
	
	, onJobReportEditClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=jobReportEdit]").click(function(){
			var jobReportKey = $(this).attr("jobReportKey");
			// also need invoice key to get details
			$("#jobReportEditDIV").load("/jobReport/jobReportEdit?jobReportKey=" + jobReportKey, function() {
				jobReportEditFn.init(); 
			});
		});
	}
	
	, onJobReportDetailsClick: function() {
		// new invoice button in the invoice list page
		$("a[link=jobReportDetails]").click(function(){
			// after get invoice key, get details page
			var jobKey = $(this).attr("jobKey");
			$("#"+microBizConst.bodyContentId).load("/jobReport/jobReportDetails?jobKey=" + jobKey, function() {
				// job page is shown by default
			});
		});
	}
}

var jobReportEditFn = {
		init: function() {
			this.onCloseClick();
			// submit button event
			this.onSumitRegister();
			this.onProductSelectChange();
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
		,onProductSelectChange: function() {
			
			// register event for customer select
			$("#productSelect").change(function(){
				// based on customerKey, refresh contact DIV
				var productKey = $(this).val();
				if ( productKey == "-1" ) {
					// no selection, clear contact
					$("#productRatioDIV").html("");
				}else{
					// AJAX call to retrieve contact
					$( "#productRatioDIV").load( "/jobReport/selectProductRatio?productKey=" + productKey);
				}
			});

		}

}

