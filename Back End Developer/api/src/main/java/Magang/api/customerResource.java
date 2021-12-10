package Magang.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")

public class customerResource {
	
	CustomerMockup mockup = new CustomerMockup();	
	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<Customer> getCustomer() throws SQLException {
		
		return mockup.getCustomers();
	}
	
	@POST
	@Path("customer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Customer createNewCustomer(Customer cus) throws SQLException {
		mockup.createCustomer(cus);
		return cus;
	}
	
	@GET
	@Path("customer/{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Customer getCustomer(@PathParam("name") String name) {
		return mockup.getCustomer(name);
	}
}
