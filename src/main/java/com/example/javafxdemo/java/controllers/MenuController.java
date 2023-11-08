package com.example.javafxdemo.java.controllers;
import java.text.ParseException;

public class MenuController {

    private static MenuController instance = new MenuController();

    private MenuController(){}

    public static MenuController getInstance(){
        if(instance == null)
            instance = new MenuController();
        return instance;
    }

    public void displayMenu() {
        System.out.println("Rental Management System Menu");
        System.out.println("-------------------------------");
        System.out.println("1. Add a property");        // Property Controller
        System.out.println("2. Add a tenant");          // Tenant Controller
        System.out.println("3. Rent a unit");           // Lease Controller
        System.out.println("4. Subscribe to properties");   // Property Controller
        System.out.println("5. Display properties");    // Property Controller
        System.out.println("6. Display tenants");       // Tenant Controller
        System.out.println("7. Display rented units");  // Lease Controller
        System.out.println("8. Display vacant units");  // Lease Controller
        System.out.println("9. Display all leases");    // Lease Controller
        System.out.println("10. Exit");
        System.out.println("-------------------------------");
        System.out.print("Please enter your choice (1-9): ");
    }

    public void addProperty() {
        //PropertyController.getInstance().addProperty();
    }

    public void addTenant() throws ParseException {
        //TenantController.getInstance().addTenant();
    }

    public void rentUnit() throws ParseException {
        //TenantController.getInstance().addTenant();
    }

    public void displayProperties() {
        //PropertyController.getInstance().displayProperties(new Stage());
    }

    public void displayTenants() {
        //TenantController.getInstance().displayTenants();
    }

    public void displayRentedUnits() {
		//LeaseController.getInstance().displayRentedUnits();
    }

    public void displayVacantUnits() {
		//LeaseController.getInstance().displayVacantUnits();
    }

    public void displayLeases() {
		//LeaseController.getInstance().displayAllLeases();
    }
    public void subscribe() throws ParseException {
    	//TenantController.getInstance().addTenant();
    }
}
