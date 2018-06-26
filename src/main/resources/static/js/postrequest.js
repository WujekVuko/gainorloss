$(document).ready(function () {

    var listTransactions = [];

    /**
     * Using JQuery for hiding some elements in view when bootstrap app
     */
    // Hide transaction table when starting
    // we just show it if having any adding-transaction
    $('#transactionTable').hide();
    $('#postTransactionsBtn').hide();

    // Transaction-Form submit
    $("#transactionForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();

        // get data from submit form

        var formTransaction = {
            name: $("#name").val(),
            numberOfShares: $("#numberOfShares").val(),
            price: $("#price").val(),
            buyDate: $("#buyDate").val(),
            sellDate: $("#sellDate").val()

        };

        // store transaction
        listTransactions.push(formTransaction);

        // re-render transaction table by append new transaction to table

        var transactionRow = '<tr>' +
            '<td>' + listTransactions.length + '</td>' +
            '<td>' + formTransaction.name.toUpperCase() + '</td>' +
            '<td>' + formTransaction.numberOfShares + '</td>' +
            '<td>' + formTransaction.price + '</td>' +
            '<td>' + formTransaction.buyDate + '</td>' +
            '<td>' + formTransaction.sellDate + '</td>' +
            '<td class="text-center">' +
            '<input type="hidden" value=' + (listTransactions.length) + '>' +
            '<a>' +
            '<span class="glyphicon glyphicon-remove"></span>' +
            '</a>' +
            '</td>' +
            '</tr>';

        $('#transactionTable tbody').append(transactionRow);

        // just how transaction table and POST button
        $('#transactionTable').show();
        $('#postTransactionsBtn').show();

        // Reset FormData after Posting
        resetData();
    });

// Do DELETE a Customer via JQUERY AJAX
    $(document).on("click", "a", function () {
        var customerId = $(this).parent().find('input').val();
        $(this).closest("tr").remove()
    });

    // Submit List of Transaction to Back-End server

    $('#postTransactionsBtn').click(function () {
        ajaxPost();
    });

    function ajaxPost() {

        // DO POST
        $.ajax({
            type: "POST",
            contentType: "application/json",
            accept: 'text/plain',
            url: window.location + "api/transaction/save",
            data: JSON.stringify(listTransactions),
            dataType: 'text',
            success: function (result) {
                // clear transaction body
                $('#transactionTable tbody').empty();
                $('#transactionTable').hide();

                // re-set transaction table list
                listTransactions = [];

                // fill successfully message on view
                $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
                    result +
                    "</p>");
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }

    function resetData() {
        $("#name").val("");
        $("#numberOfShares").val("");
        $("#price").val("");
        $("#buyDate").val("");
        $("#sellDate").val("");
    }



});