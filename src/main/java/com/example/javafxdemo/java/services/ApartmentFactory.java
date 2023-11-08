package com.example.javafxdemo.java.services;

import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.Apartment;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertySpecification;

public class ApartmentFactory extends PropertyFactory {
	@Override
	public Property createProperty(String civicAddress, Address address, PropertySpecification propertySpecification,
	String unitNumber, double rentAmount) {
		return new Apartment(civicAddress, address, propertySpecification, unitNumber, rentAmount);
	}
}
