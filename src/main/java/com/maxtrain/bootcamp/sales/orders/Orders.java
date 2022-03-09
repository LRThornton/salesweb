package com.maxtrain.bootcamp.sales.orders;

import javax.persistence.*;

import com.maxtrain.bootcamp.sales.customer.Customer;

@Entity

public class Orders {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(columnDefinition="int")
	private int id;
	@Column(length=50, nullable=false)
	private String description;
	@Column(columnDefinition="decimal(9,2) NOT NULL")
	private double total;
	//orders have to have a customer
	@ManyToOne(optional=false)  //this is adding the foreign key to Customer
	@JoinColumn(name="customerId", columnDefinition="int")  //the system will build customerID as a column in the order table
	private Customer customer;	
	
	public Orders () {
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
