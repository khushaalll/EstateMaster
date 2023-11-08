package com.example.javafxdemo.java.models;

public class Condo extends Property {

	private String unitNumber;
	public Condo(String civicAddress, Address address, PropertySpecification propertySpecification, String unitNum, double rent) {
		super(civicAddress, address, propertySpecification, rent);
		this.unitNumber = unitNum;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	
	@Override
	public String toString() {
		return "Condo :: " + "Unit number: " + unitNumber +
			   ", Civic address: '" + civicAddress + '\'' +
			   ", Address: " + address +
			   ", Property specification: '" + propertySpecification + '\'';
	}
	
	@Override 
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null
            || this.getClass() != obj.getClass())
            return false;
        Condo condo = (Condo)obj;

        return this.unitNumber.equals(condo.unitNumber)
            && this.address.equals(condo.getAddress())
            && this.civicAddress.equals(condo.getCivicAddress()) 
            && this.propertySpecification.equals(condo.getPropertySpecification())
            && this.rentAmount.equals(this.getRentAmount());
            
    }

}
