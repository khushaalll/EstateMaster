package com.example.javafxdemo.java.controllers;
import java.util.ArrayList;
import com.example.javafxdemo.java.Database;
import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.PropertySpecification;
import com.example.javafxdemo.java.services.ApartmentFactory;
import com.example.javafxdemo.java.services.CondoFactory;
import com.example.javafxdemo.java.services.HouseFactory;
import com.example.javafxdemo.java.services.PropertyFactory;
public class PropertyController{

    private static PropertyController instance = new PropertyController();



    public static PropertyController getInstance(){
        if(instance == null)
            instance = new PropertyController();
        return instance;
    }

    /**
     * 
     */
    public String addProperty(String civicAddress,String streetNumber,String streetName,String city,String province,String postal,Address address,int numBedrooms,int numBathrooms,int squareFootage,int rentAmount,int value,String unitNum) {
                PropertyFactory propertyFactory=null;
                
                switch (value){
                    case 1:
                        propertyFactory = new HouseFactory();
                        unitNum=null;
                        break;
                    case 2:
                        propertyFactory = new CondoFactory();
                        break;
                    case 3:
                        propertyFactory = new ApartmentFactory();
                        break;
                }
                PropertySpecification propertySpecification = new PropertySpecification(numBedrooms, numBathrooms, squareFootage);
                Property property = propertyFactory.createProperty(civicAddress, address, propertySpecification, unitNum, rentAmount);
                    if(Database.getInstance().getProperties().contains(property)){
                       return "Property Already Exists";
                    }
                    else{
                        Database.getInstance().addProperty(property);
                        return "Property Added";
                    }
                    
    }
    public Property gProperty(int value){
        return Database.getInstance().getProperties().get(value);
    }
    public ArrayList<Property> displayProperties() {
        return (ArrayList<Property>) Database.getInstance().getProperties();
        // Create a task to load properties in the background
        
    }


}
