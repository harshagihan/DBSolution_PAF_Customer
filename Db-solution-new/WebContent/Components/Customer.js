//hide alert
$(document).ready(function() {

    $("#alertSuccess").hide();
    $("#alertError").hide();
    $("#hidCustomerIDSave").val("");
    $("#CUSTOMER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();

    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    const email = $("#email").val();
    debugger;
    if(!regex.test(email)){
				$("#alertError").text("invalid email");
        $("#alertError").show();
        return;
    }else{
				$("#alertError").hide();
    }
	var numberPattern = /\d+/g;
    const isPhoneNumber = $("#phone_number").val().match(numberPattern);
    if(!isPhoneNumber){
				$("#alertError").text("invalid phone number");
        $("#alertError").show();
        return
    }else{
			$("#alertError").hide();
    }

    // Form validation-------------------
    var status = validateItemForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    // If valid------------------------
    var type = ($("#customer_id").val () == "") ? "POST" : "PUT";

    $.ajax({
        url : "CustomerAPI",
        type : type,
        data : $("#CUSTOMER").serialize(),
        dataType : "text",
        complete : function(response, status) {
            onItemSaveComplete(response.responseText, status);
        }
    });

});

function onItemSaveComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#CustomerGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    }
    else if (status == "error") {

        $("#alertError").text("Error while saving.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }

    $("#customer_id").val("");
    $("#CUSTOMER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {

    $.ajax({
        url : "CustomerAPI",
        type : "DELETE",
        data : "customer_id=" + event.target.value,
        dataType : "text",
        complete : function(response, status) {
            onItemDeleteComplete(response.responseText, status);
        }
    });
});

function onItemDeleteComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#CustomerGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }

    } else if (status == "error") {

        $("#alertError").text("Error while deleting.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
{
    $("#customer_id").val($(this).closest("tr").find('td:eq(0)').text());
    $("#customer_name").val($(this).closest("tr").find('td:eq(1)').text());
    $("#email").val($(this).closest("tr").find('td:eq(2)').text());
    $("#phone_number").val($(this).closest("tr").find('td:eq(3)').text());
    $("#country").val($(this).closest("tr").find('td:eq(4)').text());
});


// CLIENTMODEL=========================================================================
function validateItemForm() {

    // customer name
    if ($("#customer_name").val().trim() == "") {
        return "Please insert Customer Name.";
    }

    // Customer email
    if ($("#email").val().trim() == "") {
        return "Please insert E-mail";
    }

    // Customer phonenumber
    if ($("#phone_number").val().trim() == "") {
        return "Please insert CustomerPhone Number.";
    }

    // Customer country
    if ($("#country").val().trim() == "") {
        return "Please insert Customer Country.";
    }


    return true;
}
