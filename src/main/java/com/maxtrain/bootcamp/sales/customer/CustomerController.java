package com.maxtrain.bootcamp.sales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin  
@RestController //this signifies that your controller will be sending and receiving info through JSON data
@RequestMapping("/api/customers")


public class CustomerController {
	
	
	@Autowired
	private CustomerRepository custRepo;
	                        //iterable is a collection of instances
	@GetMapping
	public ResponseEntity<Iterable<Customer>> getCustomers(){
		var customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
		
	}

	@GetMapping("{id}")  //this method will return one item by id 
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) { //if the id you type in is not found, you will see the not found message
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}                          //otherwise it will return the customer by the id you specified in postman
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
		if(customer == null || customer.getId() !=0) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);				
		}
		var cust = custRepo.save(customer);		
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes") //this means we can use an instance of a class that is generic but we will not put a generic type in there
	@PutMapping("{id}") 
	public ResponseEntity putCustomer(@PathVariable int id, @RequestBody Customer customer) {
		if(customer == null || customer.getId() == 0) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		var cust =custRepo.findById(customer.getId());
		if(cust.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.delete(customer.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}


