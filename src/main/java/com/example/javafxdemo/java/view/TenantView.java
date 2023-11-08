package com.example.javafxdemo.java.view;
import com.example.javafxdemo.HelloApplication;
import com.example.javafxdemo.java.controllers.TenantController;
import com.example.javafxdemo.java.models.Tenant;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TenantView extends HelloApplication {
    public static void addTenantView(Stage stage){
        stage.close();
        Stage stage2 = new Stage();
        GridPane root = new GridPane();
        Label firstName = new Label("First Name");
        TextField textFirstName = new TextField();
        root.add(firstName,1,1,1,1);
        root.add(textFirstName,1,2,1,1);
        Label lastName = new Label("Last Name");
        TextField textLastName = new TextField();
        root.add(lastName,1,3,1,1);
        root.add(textLastName,1,4,1,1);
        Label contactNumber = new Label("Contact Number");
        TextField textContactNumber = new TextField();
        root.add(contactNumber,1,5,1,1);
        root.add(textContactNumber,1,6,1,1);
        Label email = new Label("Email id");
        TextField textEmail = new TextField();
        root.add(email,1,7,1,1);
        root.add(textEmail,1,8,1,1);
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
                stage2.close();
                stage.show();
            }


        });
        back.setStyle("margin:10px 10px 10px 10px");
        root.add(submit,1,9,1,1);
        root.add(back,4,9,1,1);
        Scene scene = new Scene(root,700,700);
        // scene.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setTitle("Add Tenants");
        stage2.setScene(scene);
        stage2.show();
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Thread thread = new Thread(() -> {
                    String firstName = textFirstName.getText();
                    String lastName = textLastName.getText();
                    String contactNumber = textContactNumber.getText();
                    String email = textEmail.getText();
                    String s = TenantController.getInstance().addTenant(firstName, lastName, contactNumber, email);
                    Platform.runLater(()-> {
                        if (s.equals("Tenant Already Exists")) {
                            // Show an alert if the property already exists
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Tenant Already Exists");
                            alert.setHeaderText(null);
                            alert.setContentText(s);
                            alert.showAndWait();
                        } else {
                            // Show a confirmation message if the property is added successfully
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Tenant Added");
                            alert.setHeaderText(null);
                            alert.setContentText(s);
                            alert.showAndWait();
                            stage.show();
                            stage2.close();
                        }
                    });
                });
                    thread.start();
            }
        });

    }
    public static void displayTenants(Stage stage, ArrayList<Tenant> listOfTenants) {
        ListView<String> tenants = new ListView<>();
        Stage stage2 = new Stage();
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            stage2.close();
            stage.show();
        }); // Close the current window when the button is clicked
        VBox header = new VBox(backButton);
        header.getStyleClass().add("header");
        VBox root = new VBox(tenants,header);
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setScene(scene);
        stage2.setTitle("All Tenants");
        stage2.show();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (Tenant tenant : listOfTenants) {
                    // Use Platform.runLater to update the UI from the background thread
                    Platform.runLater(() -> tenants.getItems().add(tenant.toString()));
                }
                if (listOfTenants.isEmpty()) {
                    // Use Platform.runLater to update the UI from the background thread
                    Platform.runLater(() -> tenants.getItems().add("No Tenants Available"));
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

}
