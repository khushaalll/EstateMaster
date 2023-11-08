package com.example.javafxdemo.java.models;

public class PropertySpecification {
	private Integer numBedrooms;
	private Integer numBathrooms;
	private Integer squareFootage;

	public PropertySpecification(int numBedrooms, int numBathrooms, int squareFootage) {
		this.numBedrooms = numBedrooms;
		this.numBathrooms = numBathrooms;
		this.squareFootage = squareFootage;
	}

	public int getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public int getNumBathrooms() {
		return numBathrooms;
	}

	public void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}

	@Override
	public String toString() {
		return "Number of bedrooms: " + numBedrooms +
				", Number of bathrooms: " + numBathrooms +
				", Square footage: " + squareFootage;
	}
	
	@Override 
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null
            || this.getClass() != obj.getClass())
            return false;
        PropertySpecification propertySpecification = (PropertySpecification)obj;

        return this.numBedrooms.equals(propertySpecification.numBedrooms)
        	&& this.numBathrooms.equals(propertySpecification.numBathrooms)
        	&& this.squareFootage.equals(propertySpecification.squareFootage);
    }
}
