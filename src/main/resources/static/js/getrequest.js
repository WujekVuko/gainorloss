$( document ).ready(function() {
	
	// GET REQUEST
	$("#getAllTransactionsBtnId").click(function(event){
		event.preventDefault();
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : window.location + "api/transaction/all",
			success: function(result){
					$('#resultGetAllTransactionDiv ul').empty();
					var custList = "";
					$.each(result, function(i, transaction){
						var transaction = "{name: " + transaction.name +
						", number: " + transaction.numberOfShares +
						", price: " + transaction.price +
						", buy date: " + transaction.buyDate +
						", sell date: " + transaction.sellDate +"}";
						
						$('#resultGetAllTransactionDiv .list-group').append("<li>" + transaction + "</li>");
			        });
					
					// just re-css for result-div
					$('#resultGetAllTransactionDiv').css({'background-color':'#D16953', 'color':'white', 'padding':'20px 20px 5px 30px'});
					
					// just hide POST button
					if($('#transactionTable').is(":hidden")){
						$('#postTransactionsBtn').hide();
					}
			},
			error : function(e) {
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
			}
		});
	}
})