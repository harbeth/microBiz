var jobReportFn = {
	init: function() {
		// register event for edit prd link
		this.onJobMaterialReportClick();
		this.onJobReportDetailsClick();
		this.onJobLaborReportClick();
	}
	, onJobMaterialReportClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=jobMaterialReport]").click(function(){
			var jobKey = $(this).attr("jobKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/jobReport/jobMaterialReport?jobKey=" + jobKey, function() {
				jobReportEditFn.init(); 
			});
		});
	}
	, onJobLaborReportClick: function() {
		// prd number link to sh invoice details on AJAX call in the body panel
		$("a[link=jobLaborReport]").click(function(){
			var jobKey = $(this).attr("jobKey");
			// also need invoice key to get details
			$("#"+microBizConst.bodyContentId).load("/jobReport/jobLaborReport?jobKey=" + jobKey, function() {
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

