package com.example.javafxdemo.java.view;
import com.example.javafxdemo.HelloApplication;
import com.example.javafxdemo.java.controllers.PropertyController;
import com.example.javafxdemo.java.controllers.TenantController;
import com.example.javafxdemo.java.models.Address;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.Tenant;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class PropertyView extends HelloApplication{
    public static void rentUnit(Stage stage){
        Stage rootStage = new Stage();
        GridPane root = new GridPane();
        ComboBox<String> type2 = new ComboBox<>();
        Label label2 = new Label("Select Tenant:");
        ArrayList<Tenant> prop2   = TenantController.getInstance().displayTenants();
        for(int i=0;i<prop2.size();i++){
            type2.getItems().add((i+1)+"). "+prop2.get(i).toString());
        }
        ComboBox<String> type = new ComboBox<>();
        Label label1 = new Label("Select property:");
        ArrayList<Property> prop   = PropertyController.getInstance().displayProperties();
        for(int i=0;i<prop.size();i++){
            type.getItems().add((i+1)+"). "+prop.get(i).toString());
        }
            
        // Apply CSS styling to the label
        label1.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        // Apply CSS styling to the combo box
        type.setStyle("-fx-border-color: #cccccc; -fx-font-size: 14px; -fx-pref-height: 30px; -fx-pref-width: 200px; -fx-padding: 5px;");
        label2.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        // Apply CSS styling to the combo box
        type2.setStyle("-fx-border-color: #cccccc; -fx-font-size: 14px; -fx-pref-height: 30px; -fx-pref-width: 200px; -fx-padding: 5px;");
        type.setValue(type.getItems().get(0));
        type2.setValue(type2.getItems().get(0));
        root.add(label1,1,9,1,1);
        root.add(type,1,10,1,1);
        root.add(label2,1,1,1,1);
        root.add(type2,1,2,1,1);
        
        Button submit = new Button("Submit");
        Button back = new Button("Back");
        submit.setOnMouseEntered(e -> {
            submit.setScaleX(1.2);
            submit.setScaleY(1.2);
        });
        
        submit.setOnMouseExited(e -> {
            submit.setScaleX(1);
            submit.setScaleY(1);
        });
        back.setOnMouseEntered(e -> {
            back.setScaleX(1.2);
            back.setScaleY(1.2);
        });
        
        back.setOnMouseExited(e -> {
            back.setScaleX(1);
            back.setScaleY(1);
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootStage.close();
                stage.show();
            }

        });
        
        root.add(submit,1,33,1,1);
        root.add(back,3,33,1,1);
        Scene scene = new Scene(root,700,700);
        // scene.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        rootStage.setTitle("Rent a Unit");
        rootStage.setScene(scene);
        rootStage.show();
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int value2 = Integer.parseInt(type2.getValue().split("\\)\\s*")[0])-1;
                int value = Integer.parseInt(type.getValue().split("\\)\\s*")[0])-1;
                Tenant tenant = TenantController.getInstance().gTenant(value2);
                Property p1 = PropertyController.getInstance().gProperty(value);
                        if(!p1.isOccupied()) {
                            LeaseView.addLease(stage, p1, tenant, value, rootStage, root);
                        }
                        else{
                            LeaseView.addSubscription(rootStage, stage, root, p1, tenant);
                }
                
            }
        });
    }
    public static void addProperty(Stage stage){
        stage.close();
        Stage stage2 = new Stage();
        GridPane root = new GridPane();
        ComboBox<String> type = new ComboBox<>();
        Label label1 = new Label("Select type of property:");
        type.getItems().add("1. House");
        type.getItems().add("2. Condo");
        type.getItems().add("3. Apartment");
        type.setValue(type.getItems().get(0));
        
        root.add(label1,1,1,1,1);
        root.add(type,1,2,1,1);
        Label label2 = new Label("Enter civic address:");
        TextField textCivicAddress = new TextField();
        root.add(label2,1,3,1,1);
        root.add(textCivicAddress,1,4,1,1);
        Label label3 = new Label("Street Number:");
        TextField textStreetNumber = new TextField();
        root.add(label3,1,5,1,1);
        textStreetNumber.setText("");
        root.add(textStreetNumber,1,6,1,1);
        Label label4 = new Label("Street name:");
        TextField textStreetName = new TextField();
        textStreetName.setText("");
        root.add(label4,1,7,1,1);
        root.add(textStreetName,1,8,1,1);
        Label label5 = new Label("City:");
        TextField textCity = new TextField();
        root.add(label5,1,9,1,1);
        root.add(textCity,1,10,1,1);
        Label label6 = new Label("Province:");
        TextField textProvince = new TextField();
        root.add(label6,1,11,1,1);
        root.add(textProvince,1,12,1,1);
        Label label7 = new Label("Postal code:");
        TextField textPostalCode = new TextField();
        root.add(label7,1,13,1,1);
        root.add(textPostalCode,1,14,1,1);
        Label unit = new Label("Enter Unit Number:");
        TextField textUnitNumber = new TextField();
        textUnitNumber.setText("");
        type.setOnAction(e -> {
            String selectedValue = type.getValue();
            if(selectedValue.equals("1. House")){
                textStreetNumber.setDisable(false);
                textUnitNumber.setDisable(true);
            }
            if(selectedValue.equals("2. Condo")){
                textStreetNumber.setDisable(false);
                textUnitNumber.setDisable(false);
            }
            if(selectedValue.equals("3. Apartment")){
                textStreetNumber.setDisable(true);
                textUnitNumber.setDisable(false);
            }
            // Call your function here or pass selectedValue as parameter to it
        });
        textStreetNumber.setDisable(false);
        textUnitNumber.setDisable(true);
        root.add(unit,1,15,1,1);
        root.add(textUnitNumber,1,16,1,1);
        Label bedRoom = new Label("Number of bedrooms:");
        TextField textNumBedrooms = new TextField();
        root.add(bedRoom,1,17,1,1);
        root.add(textNumBedrooms,1,18,1,1);
        Label bathRoom = new Label("Number of bathrooms:");
        TextField textNumBathroom = new TextField();
        root.add(bathRoom,1,19,1,1);
        root.add(textNumBathroom,1,20,1,1);
        Label size = new Label("Square footage:");
        TextField textSize = new TextField();
        root.add(size,1,21,1,1);
        root.add(textSize,1,22,1,1);
        Label rent = new Label("Rent amount:");
        TextField textRent= new TextField();
        root.add(rent,1,23,1,1);
        root.add(textRent,1,24,1,1);

        Button submitBut = new Button("Submit");
        Button backBut = new Button("Back");
        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.show();
                stage2.close();
            }
        });
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.add(submitBut,0,26,1,1);
        root.add(backBut, 5, 26,1,1);
        root.setStyle("-fx-background-color: #F0F8FF;");
        root.setAlignment(Pos.CENTER);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(6.0);
        dropShadow.setOffsetX(4.0);
        dropShadow.setOffsetY(3.0);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane,800,800);
        // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("style.css").toExternalForm());
        stage2.setScene(scene);
        stage2.setTitle("Add Property");
        stage2.show();
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        submitBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Create a new thread to run the database operation
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int value = Integer.parseInt(type.getValue().substring(0, 1));
                        String unitNum = textUnitNumber.getText();
                        String civicAddress = textCivicAddress.getText();
                        String streetNumber = textStreetNumber.getText();
                        String streetName = textStreetName.getText();
                        String city = textCity.getText();
                        String province = textProvince.getText();
                        String postal = textPostalCode.getText();
                        Address address = new Address(streetNumber, streetName, city, province, postal);
                        int numBedrooms = Integer.parseInt(textNumBedrooms.getText());
                        int numBathrooms = Integer.parseInt(textNumBathroom.getText());
                        int squareFootage = Integer.parseInt(textSize.getText());
                        int rentAmount = Integer.parseInt(textRent.getText());

                        // Call the controller method to add the property
                        String s = PropertyController.getInstance().addProperty(civicAddress, streetNumber, streetName, city, province, postal, address, numBedrooms, numBathrooms, squareFootage, rentAmount, value, unitNum);
                        // Update the UI components using Platform.runLater()
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (s.equals("Property Already Exists")) {
                                    // Show an alert if the property already exists
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText(s);
                                    alert.showAndWait();
                                } else {
                                    // Show a confirmation message if the property is added successfully
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Success");
                                    alert.setHeaderText(null);
                                    alert.setContentText(s);
                                    alert.showAndWait();
                                    stage.show();
                                    stage2.close();
                                }
                            }
                        });
                    }
                });
                thread.start();
            }
        });
    }
    public static void dispRented(Stage stage, ArrayList<Property> rentedProperties){
        Stage stage2 = new Stage();
        ListView<String> rented = new ListView<>();
        if(rentedProperties.size()==0){
            rented.getItems().add("No Rented Properties");
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage2.close();
                stage.show();
            }
        }); // Close the current window when the button is clicked
        VBox header = new VBox(backButton);
        header.getStyleClass().add("header");
        VBox root = new VBox(rented,header);
        root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20px; -fx-spacing: 10px; -fx-border-color: #333333; -fx-border-width: 2px; -fx-background-radius: 10px;");
        root.setAlignment(Pos.CENTER);
        VBox.setMargin(header, new Insets(20, 0, 0, 300));
        rented.setStyle("-fx-font-size: 18px; -fx-font-family: Arial; -fx-control-inner-background: #ffffff; -fx-border-color: #333333; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-focus-color: transparent;");
        Scene scene = new Scene(root, 700, 700);
        //scene.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setScene(scene);
        stage2.setTitle("Rented properties");

        // Create a new thread to add items to the ListView
        new Thread(() -> {
            for(Property p: rentedProperties) {
                Platform.runLater(() -> rented.getItems().add(p.toString()));
            }
        }).start();

        stage2.show();
    }

public static void dispVacant(Stage stage, ArrayList<Property> properties) {
    Stage stage2 = new Stage();
    ListView<String> vacant = new ListView<>();
    Button backButton = new Button("Back");
    backButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            stage2.close();
            stage.show();
        }
    });
    VBox header = new VBox(backButton);
    header.getStyleClass().add("header");
    VBox root = new VBox(vacant,header);
    root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20px; -fx-spacing: 10px; -fx-border-color: #333333; -fx-border-width: 2px; -fx-background-radius: 10px;");
    root.setAlignment(Pos.CENTER);
    VBox.setMargin(header, new Insets(20, 0, 0, 300));
    vacant.setStyle("-fx-font-size: 18px; -fx-font-family: Arial; -fx-control-inner-background: #ffffff; -fx-border-color: #333333; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-focus-color: transparent;");

    Scene scene = new Scene(root, 700, 700);
    scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());

    stage2.setTitle("Vacant properties");
    stage2.setScene(scene);

    // Create a new thread to populate the vacant list view
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (Property property : properties) {
                if (!property.isOccupied()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            vacant.getItems().add(property.toString());
                        }
                    });
                }
            }
            if(properties.size()==0){
                vacant.getItems().add("No Vacant Places");
            }
        }
    });

    thread.start();

    stage2.show();
}

    public static void properties(Stage stage, Scene scene, List<Property> listOfProperties){
        Stage stage2 = new Stage();
        ListView<String> properties = new ListView<>();
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage2.close();
                stage.show();
            }
        }); // Close the current window when the button is clicked
        VBox header = new VBox(backButton);
        header.getStyleClass().add("header"); // Add the "header" CSS class
        VBox root = new VBox(properties,header);
        root.getStyleClass().add("v-box"); // Add the "v-box" CSS class
        VBox.setMargin(header, new Insets(20, 0, 0, 300));
        root.setAlignment(Pos.CENTER);
        scene = new Scene(root, 700, 700);
        properties.getStyleClass().add("list-view"); // Add the "list-view" CSS class
        stage2.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("/css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setTitle("All Properties");
        stage2.show();

        Task<Void> loadPropertiesTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                for (Property p : listOfProperties) {
                    // Update the ListView on the JavaFX Application Thread
                    Platform.runLater(() -> properties.getItems().add(p.toString()));
                }
                if(listOfProperties.size()==0){
                    // Update the ListView on the JavaFX Application Thread
                    Platform.runLater(() -> properties.getItems().add("No Properties Available"));
                }
                return null;
            }
        };

        // Start the task in a separate thread
        new Thread(loadPropertiesTask).start();
    }

}
