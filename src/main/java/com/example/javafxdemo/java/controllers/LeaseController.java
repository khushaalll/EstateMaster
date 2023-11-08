package com.example.javafxdemo.java.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.javafxdemo.java.Database;
import com.example.javafxdemo.java.RealEstateManagementSystem;
import com.example.javafxdemo.java.models.Lease;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertyOccupancyDisplay;
import com.example.javafxdemo.java.models.Tenant;
import javafx.stage.Stage;

public class LeaseController {

	private static LeaseController instance = new LeaseController();
	private static Database databaseInstance = Database.getInstance();

	private LeaseController() {
	}

	public static LeaseController getInstance() {
		if (instance == null)
			instance = new LeaseController();

		return instance;
	}

	public boolean rentUnit(Property unit, Tenant tenant, Date startDate, Date endDate) {
		if (unit.isOccupied())
			return false;
		Lease newLease = new Lease(tenant, unit, unit.getRentAmount(), startDate, endDate);
		Database.getInstance().addLease(newLease);
		System.out.println("Lease added successfully");
		return true;
	}
	public ArrayList<Property> displayRentedUnits(Stage stage) {
		return (ArrayList<Property>) Database.getInstance().getRentedProperties();
	}

	public ArrayList<Property> displayVacantUnits(Stage stage) {
		List<Property> properties = databaseInstance.getProperties(),answer=new ArrayList<>();
		for (Property property : properties) {
			if (!property.isOccupied()) {
				answer.add(property);
			}
		}
		return (ArrayList<Property>) answer;
	}

	public List<Property> displayAllUnits(){
		return Database.getInstance().getProperties();
	}

	public ArrayList<Lease> displayAllLeases() {
		return (ArrayList<Lease>) Database.getInstance().getLeases();
	}
	public void addLease(Tenant t1,Property p1,Date startDate,Date endDate,int value){
				Lease newLease = new Lease(t1, p1, p1.getRentAmount(), startDate, endDate);
                Database.getInstance().addLease(newLease);
                p1.setOccupied(true);
                Database.getInstance().getProperties().get(value).setOccupied(true);
                Database.getInstance().addRentedProperty(p1);
	}
	public void removeLease(int value){
		Property le1 = Database.getInstance().getLeases().remove(value).getProperty();
				Database.getInstance().getRentedProperties().remove(le1);
				List<Property> li = Database.getInstance().getProperties();
				int i=0;
				for(i=0;i<li.size();i++){
					if(li.get(i).equals(le1)){
						li.get(i).setOccupied(false);
						RealEstateManagementSystem.propertyTracker.setOccupancy(li.get(i), false);
						break;
					}
				}
				Database.getInstance().setProperty(li);
	}
	public void subscribe(Property p1,Tenant tenant){
		PropertyOccupancyDisplay user1Subscribe = new PropertyOccupancyDisplay(p1, tenant);
		RealEstateManagementSystem.propertyTracker.registerObserver(p1, user1Subscribe);
	}
}
