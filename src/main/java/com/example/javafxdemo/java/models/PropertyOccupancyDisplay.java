package com.example.javafxdemo.java.models;

public class PropertyOccupancyDisplay implements Observer {
    private Property property;
    private Tenant tenant;

    public PropertyOccupancyDisplay(Property property, Tenant tenant) {
        this.property = property;
        this.tenant = tenant;
    }

    @Override
    public void updateTenants(Property property, boolean isOccupied) {
        if (!isOccupied) {
            System.out.println("Property " + this.property + " is unoccupied. Notifying " + tenant.getFirstName() + " " + tenant.getLastName() + "... ");
        }
    }
}