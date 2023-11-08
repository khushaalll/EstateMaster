package com.example.javafxdemo.java.models;

public class House extends Property {

	public House(String civicAddress, Address address, PropertySpecification propertySpecification, double rentAmount) {
		super(civicAddress, address, propertySpecification, rentAmount);
	}
	
	@Override
	public String toString() {
		return "House :: " +
			   "Civic address: '" + civicAddress + '\'' +
			   ", Address: " + address +
			   ", PropertySpecification: " + propertySpecification+
			   ", Rent: $" + rentAmount +
			   ", occupied : " + this.isOccupied(); 
	}
	
	
	@Override 
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null
            || this.getClass() != obj.getClass())
            return false;
        
        House house = (House)obj;
        
        return this.civicAddress.equals(house.civicAddress)
        	&& this.address.equals(house.address)
        	&& this.propertySpecification.equals(house.propertySpecification)
        	&& this.rentAmount.equals(house.rentAmount);
    }
	

}
