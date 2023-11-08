package com.example.javafxdemo.java.services;
import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertySpecification;

public abstract class PropertyFactory {
    public abstract Property createProperty(String civicAddress, Address address, PropertySpecification propertySpecification, String unitNum, double rentAmount);
}






