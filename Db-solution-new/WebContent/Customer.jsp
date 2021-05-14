<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Customer Management</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Customer.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Customer Management</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add New Customer </b></legend>
					<form id="CUSTOMER" name="CUSTOMER" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Customer Name:</label>
						    <input type="hidden" id="customer_id" name="customer_id">
						    <input type="text" id="customer_name" class="form-control" name="customer_name">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">E-mail:</label>
						    <input type="text" id="email" class="form-control" name="email">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Phone number:</label>
						    <input type="text" id="phone_number" class="form-control" name="phone_number">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Country:</label>
						    <input type="text" id="country" class="form-control" name="country">						    
						</div>
						<br>
						<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-success btn-lg btn-block">	
					</form>	
				
				
							
			</fieldset>
			<div class="container" id="CustomerGrid">
			<fieldset>
					<legend><b>Customer  Details</b></legend>
						<form method="post" action="Customer.jsp" class="table table-striped">
						<%
						Customer viewCustomer = new Customer();
								out.print(viewCustomer.readCustomer());
						%>
			</form>
					<br>
			</fieldset>
			</div>
			</div>
		</body>
</html>



