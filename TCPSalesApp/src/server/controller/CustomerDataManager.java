package server.controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Product;

public class CustomerDataManager {

	private List<Customer> customerList;
	
	//Constructor
	public CustomerDataManager() {
		this.customerList= new ArrayList<Customer>();
		generateCustomers();
	}
	
	//Method 1
	private void generateCustomers() {
		
		// Sample data
				int ids[] = {2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010};
				String names[] = {"Afiq", "Daniel", "Haziq","Syamil", "Faiz", "Iman","Farhan", "Aliff", "Imran",};
				
				// Load data into list
				for (int index=0; index < ids.length; index++) {
					
					// Create product
					Customer customer = new Customer();
					customer.setCustomerId(ids[index]);
					customer.setName(names[index]);
					
					// Add to list
					customerList.add(customer);
				}
	}
	
	//Method 2
	public Customer searchCustomerName(String name) {
		
		Customer customer = new Customer();
		
		boolean found = false;
		
		for (Customer c: customerList) {
			if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                customer = c;
                found = true;
                break;
            }
		}

        if (!found) {
            return null;
        }

        else {
            return customer;
        }
	}
	
	//Method 3
	public Customer searchCustomerId(int id) {
		Customer customer = new Customer();

        boolean found = false;

        // create a loop to search a customer
        for (Customer c : customerList) {
            if (c.getCustomerId() == id) {
                customer = c;
                found = true;
                break;
            }
        }

        if (!found) {
            return null;
        }

        else {
            return customer;
        }
	}
	
	//Method 4
	public List<Customer> getCustomers(){
		return customerList;
	}
	
}
