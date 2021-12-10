package Magang.api;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerMockup {
	List<Customer> customers = new ArrayList<Customer>();
	
	public CustomerMockup() {
		Customer cust1 = new Customer(1,"Reinaldo", "Bogor");
		Customer cust2 = new Customer(2,"Adrianus", "Jakarta");
		customers = Arrays.asList(cust1, cust2);
	}
	
	
	public List<Customer> getCustomers() throws SQLException{
		ResultSet result = getConnection().createStatement().executeQuery("select * from user");
		while(result.next()) {
			Customer cus = new Customer();
			cus.setName(result.getString(2));
			cus.setAddress(result.getString(2));
			customers.add(cus);
		}
		return customers;
	}
	
	public Customer getCustomer(String name) {
		return customers.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
	}
	
	public void createCustomer(Customer customer) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("insert into customer(name,address)values (?,?)");
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getAddress());
		ps.executeUpdate();
	}
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbl:mysql://localhost:8090/customer","root","");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return con;
	}
}
