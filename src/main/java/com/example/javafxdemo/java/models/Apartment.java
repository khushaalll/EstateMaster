package com.example.javafxdemo.java.models;

public class Apartment extends Property {

	private String apartmentNumber;

	public Apartment(String civicAddress, Address address, PropertySpecification propertySpecification,
			String apartmentNumber, double rent) {
		super(civicAddress, address, propertySpecification, rent);
		this.apartmentNumber = apartmentNumber;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	
	@Override
	public String toString() {
		return "Apartment :: " + 
			   ", Civic address: '" + civicAddress + '\'' +
			   ", Address: " + address +
			   ", Property specification: '" + propertySpecification + '\''+
			   ", Rent: $" + rentAmount ; 
	}
	
	@Override 
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Apartment apartment = (Apartment)obj;

        return this.apartmentNumber.equals(apartment.apartmentNumber)
                && this.address.equals(apartment.getAddress())
                && this.civicAddress.equals(apartment.getCivicAddress()) 
                && this.propertySpecification.equals(apartment.getPropertySpecification())
                && this.rentAmount.equals(this.getRentAmount());
    }

}
