package com.example.javafxdemo.java;

import com.example.javafxdemo.java.models.Lease;
import com.example.javafxdemo.java.models.Payment;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.Tenant;

import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<Property> properties;
	private List<Tenant> tenants;
	private List<Lease> leases;
	private List<Payment> payments;
	private List<Property> rentedProperties;
	private static Database instance = new Database();

	private Database() {
		properties = new ArrayList<>();
		setTenants(new ArrayList<>());
		leases = new ArrayList<>();
		setPayments(new ArrayList<>());
		rentedProperties = new ArrayList<>();
	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	public void addProperty(Property property) {
		properties.add(property);
	}

	public void addLease(Lease lease) {
		leases.add(lease);
	}

	public void addTenant(Tenant tenant) {
		tenants.add(tenant);
	}
	public void setProperty(List<Property> list){
		this.properties = list;
	}
	public void addRentedProperty(Property property) {
		rentedProperties.add(property);
	}
	
	public List<Lease> getLeases() {
		return leases;
	}

	public void setLeases(List<Lease> leases) {
		this.leases = leases;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Property> getRentedProperties() {
		return rentedProperties;
	}

	public void setRentedProperties(List<Property> rentedProperties) {
		this.rentedProperties = rentedProperties;
	}

}
