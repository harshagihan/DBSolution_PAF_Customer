package model;
import java.sql.*;




//A common method to connect to the DB
public class Customer
{
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");



//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbsolution_customer", "root", "root");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}


public String insertCustomer(String cname, String cemail, String phoneNumber, String ccountry)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}


// create a prepared statement
String query = " insert into customer(`customer_id`,`customer_name`,`email`,`phone_number`,`country`)" + " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, cname);
preparedStmt.setString(3, cemail);
preparedStmt.setInt(4,Integer.parseInt( phoneNumber));
preparedStmt.setString(5, ccountry);




// execute the statement
preparedStmt.execute();
con.close();
String newCustomer = readCustomer();
output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
}
catch (Exception e)
{
//output = "Error while inserting the customer.";
	output = "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";
	System.err.println(e.getMessage());
	System.out.println(e.getMessage());
	System.out.println(e);
	e.printStackTrace();
}
return output;
}

//Read Customer
public String readCustomer()
{
String output = "";

try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}


// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Customer ID</th>" +
"<th>Customer Name</th>" +"<th>E-Mail</th>" +"<th>Phone Number</th>" + "<th>Country</th>" + 
"<th>Update</th><th>Remove</th></tr>";



String query = "select * from customer";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);


// iterate through the rows in the result set
while (rs.next())
{
String customer_id = Integer.toString(rs.getInt("customer_id"));
String customer_name = rs.getString("customer_name");
String email = rs.getString("email");
String phone_number =Integer.toString(rs.getInt("phone_number"));
String country = rs.getString("country");


// Add into the html table
output += "<tr><td>" + customer_id + "</td>";
output += "<td>" + customer_name + "</td>";
output += "<td>" + email + "</td>";
output += "<td>" + phone_number + "</td>";
output += "<td>" + country + "</td>";


// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ customer_id +"' >Remove</button></td></tr>";
}
con.close();

// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the items.";
System.err.println(e.getMessage());
}
return output;
}

//updateResearcher
public String updateCustomer(String customer_id, String cname, String cemail, String phoneNumber, String ccountry)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}

// create a prepared statement
String query = "UPDATE customer SET customer_name=?,email=?,phone_number=?,country=?WHERE customer_id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
preparedStmt.setString(1, cname);
preparedStmt.setString(2, cemail);
preparedStmt.setInt(3, Integer.parseInt(phoneNumber));
preparedStmt.setString(4, ccountry);
preparedStmt.setInt(5,Integer.parseInt (customer_id));


// execute the statement
preparedStmt.execute();
con.close();
String newCustomer = readCustomer(); output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
//output = "Updated successfully";
}
catch (Exception e)
{
//output = "Error while updating the Customer.";
output = "{\"status\":\"error\", \"data\": \"Error while updating the Customer.\"}";
System.err.println(e.getMessage());
}
return output;
}

//deleteResearcher
public String deleteCustomer(String customer_id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}


// create a prepared statement
String query = "delete from customer where customer_id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
preparedStmt.setInt(1,Integer.parseInt (customer_id));

// execute the statement
preparedStmt.execute();
con.close();
String newCustomer = readCustomer(); output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
//output = "Deleted successfully";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the Customer.\"}";
	System.err.println(e.getMessage());
	System.out.println(e);
	}
return output;
}
}