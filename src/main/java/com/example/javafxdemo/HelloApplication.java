package com.example.javafxdemo;
import com.example.javafxdemo.java.controllers.LeaseController;
import com.example.javafxdemo.java.controllers.PropertyController;
import com.example.javafxdemo.java.controllers.TenantController;
import com.example.javafxdemo.java.models.*;
import com.example.javafxdemo.java.view.LeaseView;
import com.example.javafxdemo.java.view.PropertyView;
import com.example.javafxdemo.java.view.TenantView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.javafxdemo.java.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
public class HelloApplication extends Application {
    String css = "";
    @Override
    public void start(Stage stage) throws IOException {
        Property house = new House("A", new Address("1234","B","C","D","E"), new PropertySpecification(2,1,1000), 5000.0);
        Database.getInstance().addProperty(house);
        house.setOccupied(true);
        Database.getInstance().addRentedProperty(house);
        Property apartment = new House("F", new Address("5678","G","H","I","J"), new PropertySpecification(3,1,1500), 4500.0);
        Database.getInstance().addProperty(apartment);
        Tenant tenant1 = new Tenant("Pravalika","Kokku","5140000000","p.kokku@gmail.com");
        Tenant tenant2 = new Tenant("Tom","Ford","514-990-0799","tom.ford@gmail.com");
        Database.getInstance().addTenant(tenant1);
        Database.getInstance().addTenant(tenant2);
        Database.getInstance().addLease(new Lease(tenant1, house, 300, new Date(), new Date()));
        GridPane root = new GridPane();
        Button addAProperty = new Button("Add property");
        Button addATenant = new Button("Add tenant");
        Button rentUnit = new Button("Rent Property");
        Button subscribe = new Button("Add Subscription");
        Button displayProperties = new Button("Display properties");
        Button displayTenants = new Button("Display tenants");
        Button displayRented = new Button("Display rented");
        Button displayVacant = new Button("Display vacant");
        Button displayLeases = new Button("Display lease");
        Button removeLeases = new Button("Release Lease");
        Button exit  = new Button("Exit");
        root.setHgap(10);
        root.setVgap(10);
        String buttonStyle = "-fx-background-color: linear-gradient(to bottom right, #4CAF50, #00796B); -fx-text-fill: white; -fx-font-family: 'Open Sans', sans-serif; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 8px;";
        addAProperty.setStyle(buttonStyle);
        addATenant.setStyle(buttonStyle);
        rentUnit.setStyle(buttonStyle);
        subscribe.setStyle(buttonStyle);
        displayProperties.setStyle(buttonStyle);
        displayTenants.setStyle(buttonStyle);
        displayRented.setStyle(buttonStyle);
        displayVacant.setStyle(buttonStyle);
        displayLeases.setStyle(buttonStyle);
        exit.setStyle(buttonStyle);
        removeLeases.setStyle(buttonStyle);
        root.add(addAProperty,1,1,1,1);
        root.add(addATenant,2,1,1,1);
        root.add(rentUnit,3,1,1,1);
        root.add(subscribe,1,2,1,1);
        root.add(displayProperties,2,2,1,1);
        root.add(displayTenants,3,2,1,1);
        root.add(displayRented,1,3,1,1);
        root.add(displayVacant,2,3,1,1);
        root.add(displayLeases,3,3,1,1);
        root.add(exit,2,4,1,1);
        root.add(removeLeases, 1, 4,1,1);
        root.setStyle("-fx-background-color: #F0F8FF;");
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Welcome to Main Page");
        stage.setScene(scene);
        stage.show();
       
        removeLeases.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                LeaseView.leaseRemoval(stage);
            }
        });
        addAProperty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PropertyView.addProperty(stage);                
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        displayProperties.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                ArrayList<Property> listOfProperties=  PropertyController.getInstance().displayProperties();
                PropertyView.properties(stage, scene, listOfProperties);
            }
        });
        displayTenants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                ArrayList<Tenant> t1 =  TenantController.getInstance().displayTenants();
                TenantView.displayTenants(stage, t1);
            }
        });
        addATenant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TenantView.addTenantView(stage);
            }
        });
        displayRented.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.close();
                    ArrayList<Property> p1 = LeaseController.getInstance().displayRentedUnits(stage);
                    PropertyView.dispRented(stage, p1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        displayLeases.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.close();
                    ArrayList<Lease> lease = LeaseController.getInstance().displayAllLeases();
                    LeaseView.dispLease(stage, lease);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        subscribe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    PropertyView.rentUnit(stage);
            }
        });
        rentUnit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PropertyView.rentUnit(stage);
            }
        });
        displayVacant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.close();
                    ArrayList<Property> p1 =  LeaseController.getInstance().displayVacantUnits(stage);
                    PropertyView.dispVacant(stage, p1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}