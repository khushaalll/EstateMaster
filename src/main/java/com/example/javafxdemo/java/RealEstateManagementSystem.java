package com.example.javafxdemo.java;
import java.text.ParseException;
import java.util.Scanner;
import com.example.javafxdemo.java.controllers.MenuController;
import com.example.javafxdemo.java.models.*;

//@SpringBootApplication
public class RealEstateManagementSystem{


	private static Scanner scanner = new Scanner(System.in);

	private static RealEstateManagementSystem instance = new RealEstateManagementSystem();

	private RealEstateManagementSystem() {}
	public static PropertyTracker propertyTracker = new PropertyTracker();
	public static void main(String[] args) throws ParseException {
		System.out.println("Adding properties");
		Property house = new House("A", new Address("1234","B","C","D","E"), new PropertySpecification(2,1,1000), 5000.0);
		Database.getInstance().addProperty(house);
		Property apartment = new House("F", new Address("5678","G","H","I","J"), new PropertySpecification(3,1,1500), 4500.0);
		Database.getInstance().addProperty(apartment);
		//PropertyController.getInstance().displayProperties();

		System.out.println("Adding tenants");
		Tenant tenant1 = new Tenant("Pravalika","Kokku","5140000000","p.kokku@gmail.com");
		Tenant tenant2 = new Tenant("Tom","Ford","514-990-0799","tom.ford@gmail.com");
		Database.getInstance().addTenant(tenant1);
		Database.getInstance().addTenant(tenant2);
		house.setOccupied(true);
		System.out.println("house is set to occupied");
		System.out.println("Observer Pattern");
		// Register observer to the subject
		PropertyOccupancyDisplay user1Subscribe = new PropertyOccupancyDisplay(house, tenant1);
		propertyTracker.registerObserver(house, user1Subscribe);
		System.out.println("Now setting house to unoccupied. To check observer pattern manually....");
        propertyTracker.setOccupancy(house, false);
        int i=0;
        for(i=0;i<Database.getInstance().getProperties().size();i++) {
        	if(Database.getInstance().getProperties().get(i).equals(house)) {
        		Database.getInstance().getProperties().get(i).setOccupied(false);
        		break;
        	}
        }
		System.out.println("Real Estate Management System");
        Boolean goToMainMenu=true;
        int flag=0;
		while(goToMainMenu) {
			int choice=0;
			MenuController.getInstance().displayMenu();
			System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        switch (choice){
        	case 1:
        		MenuController.getInstance().addProperty();
        		flag=1;
        		break;
        	case 2:
        		MenuController.getInstance().addTenant();
        		flag=1;
        		break;
        	case 3:
        		MenuController.getInstance().addTenant();
        		flag=1;
        		break;
        	case 4:
        		MenuController.getInstance().subscribe();
        		flag=1;
        		break;
        	case 5:
        		MenuController.getInstance().displayProperties();
        		flag=1;
        		break;
        	case 6:
        		MenuController.getInstance().displayTenants();
        		flag=1;
        		break;
        	case 7:
        		MenuController.getInstance().displayRentedUnits();
        		flag=1;
        		break;
        	case 8:
        		MenuController.getInstance().displayVacantUnits();
        		flag=1;
        		break;
        	case 9:
        		MenuController.getInstance().displayLeases();
        		flag=1;
        		break;
        	case 10:
        		System.out.println("Thank you!");
        		System.exit(0);
        		break;
        	default:
        		System.out.println("Please enter an option only between 1 and 9");
        		flag=1;
		   		break;
		   	}
        	if(flag==1) {
        		goToMainMenu=true;
        		flag=0;
        	}else {
        		goToMainMenu=false;
        	}
		}
		
      }
	
	public static RealEstateManagementSystem getInstance(){
		if(instance == null)
			instance = new RealEstateManagementSystem();
		return instance;
	}
}
