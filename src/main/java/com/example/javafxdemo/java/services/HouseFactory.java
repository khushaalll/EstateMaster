package com.example.javafxdemo.java.services;

import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.House;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertySpecification;

public class HouseFactory extends PropertyFactory {
    @Override
    public Property createProperty(String civicAddress, Address address, PropertySpecification propertySpecification,
    String apartmentNumber, double rentAmount) {
    	// TODO
        return new House(civicAddress, address, propertySpecification, rentAmount);
    }
}
