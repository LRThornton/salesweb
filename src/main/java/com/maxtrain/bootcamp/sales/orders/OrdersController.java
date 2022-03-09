package com.maxtrain.bootcamp.sales.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin  
@RestController //this signifies that your controller will be sending and receiving info through JSON data
@RequestMapping("/api/orders")


public class OrdersController {
	
	@Autowired
	private OrderRepository ordRepo;
	                        
	//iterable is a collection of instances
	@GetMapping
	public ResponseEntity<Iterable<Orders>> getOrders(){
		var orders = ordRepo.findAll();
		return new ResponseEntity<Iterable<Orders>>(orders, HttpStatus.OK);
		
	}

	@GetMapping("{id}")  //this method will return one item by id 
	public ResponseEntity<Orders> getOrder(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) { //if the id you type in is not found, you will see the not found message
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}                          //otherwise it will return the customer by the id you specified in postman
		return new ResponseEntity<Orders>(order.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Orders> postOrder(@RequestBody Orders order){
		if(order == null || order.getId() !=0) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);				
		}
		var ord = ordRepo.save(order);		
		return new ResponseEntity<Orders>(ord,HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes") //this means we can use an instance of a class that is generic but we will not put a generic type in there
	@PutMapping("{id}") 
	public ResponseEntity putOrder(@PathVariable int id, @RequestBody Orders order) {
		if(order == null || order.getId() == 0) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		var ord =ordRepo.findById(order.getId());
		if(ord.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.save(order);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.delete(order.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
