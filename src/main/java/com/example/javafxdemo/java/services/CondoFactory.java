package com.example.javafxdemo.java.services;

import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.Condo;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertySpecification;

public class CondoFactory extends PropertyFactory {
    @Override
    public Property createProperty(String civicAddress, Address address, PropertySpecification propertySpecification, String unitNum, double rent) {
        return new Condo(civicAddress, address, propertySpecification, unitNum, rent);
    }
}
