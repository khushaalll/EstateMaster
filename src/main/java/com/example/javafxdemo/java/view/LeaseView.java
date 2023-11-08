package com.example.javafxdemo.java.view;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.example.javafxdemo.HelloApplication;
import com.example.javafxdemo.java.controllers.LeaseController;
import com.example.javafxdemo.java.models.Lease;
import com.example.javafxdemo.java.models.Property;
import com.example.javafxdemo.java.models.Tenant;

public class LeaseView extends HelloApplication {
    public static void dispLease(Stage stage, ArrayList<com.example.javafxdemo.java.models.Lease> lease2){
        ListView<String> rentedLease = new ListView<>();
        Stage stage2 = new Stage();
        VBox header = new VBox(new Button("Back"));
        header.getStyleClass().add("header");
        VBox root = new VBox(rentedLease,header);
        root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20px; -fx-spacing: 10px; -fx-border-color: #333333; -fx-border-width: 2px; -fx-background-radius: 10px;");
        root.setAlignment(Pos.CENTER);
        VBox.setMargin(header, new Insets(20, 0, 0, 300));
        rentedLease.setStyle("-fx-font-size: 18px; -fx-font-family: Arial; -fx-control-inner-background: #ffffff; -fx-border-color: #333333; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-focus-color: transparent;");
        // Set the scene dimensions and add the VBox to the scene
        Scene scene = new Scene(root, 700, 700);
        //scene.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setScene(scene);
        stage2.setTitle("Apartments with lease");
        stage2.show();

        // Create a new thread to handle lease list creation and updates
        Thread leaseThread = new Thread(() -> {
            if(lease2.size()>0){
                for (Lease lease : lease2) {
                    Platform.runLater(() -> rentedLease.getItems().add(lease.toString()));
                }
            }
            else{
                Platform.runLater(() -> rentedLease.getItems().add("No Lease Available"));
            }
        });
        leaseThread.start();

        Button backButton = (Button) header.getChildren().get(0);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage2.close();
                stage.show();
            }
        });
    }


    public static void leaseRemoval(Stage stage){
        Stage rootStage = new Stage();
        GridPane root = new GridPane();
        ComboBox<String> type = new ComboBox<>();
        Label label1 = new Label("Select Lease:");
        ArrayList<Lease> prop   = LeaseController.getInstance().displayAllLeases();
        if(prop.size()>0){
            for(int i=0;i<prop.size();i++){
                type.getItems().add((i+1)+"). "+prop.get(i).toString());
            }
        }
        else{
            type.getItems().add("No Lease Available");
        }
        type.setValue(type.getItems().get(0));
        // Apply CSS styling to the label
        label1.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");
        type.setStyle("-fx-border-color: #cccccc; -fx-font-size: 14px; -fx-pref-height: 30px; -fx-pref-width: 600px; -fx-padding: 5px;");

        // Apply CSS styling to the combo box
        root.setStyle("-fx-background-color: #F0F8FF; -fx-border-color: #cccccc; -fx-font-size: 14px; -fx-pref-height: 30px; -fx-pref-width: 200px; -fx-padding: 5px;");
        root.add(label1,1,1,1,1);
        root.add(type,1,2,1,1);
        Button submit = new Button("Submit");
		Button back = new Button("Back");
        back.setStyle("margin-left: 400px;");
        root.add(submit,1,3,1,1);
		root.add(back, 1, 3,1,1);
        GridPane.setMargin(back, new Insets(20, 0, 0, 300));
        GridPane.setMargin(submit, new Insets(20, 0, 0, 200));
        Scene scene = new Scene(root,700,700);
		// scene.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        rootStage.setTitle("Remove Lease");
        rootStage.setScene(scene);
        rootStage.show();
		root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (type.getValue().equals("No Lease Available")) {
                    rootStage.close();
                    stage.show();
                } else {
                    int value = Integer.parseInt(type.getValue().split("\\)\\s*")[0]) - 1;
                    // Perform the lease removal in a background thread
                    Task<Void> task = new Task<>() {
                        @Override
                        protected Void call() throws Exception {
                            LeaseController.getInstance().removeLease(value);
                            return null;
                        }
                    };
                    task.setOnSucceeded(event -> {
                            // Show a confirmation message if the property is added successfully
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Lease Released");
                            alert.setHeaderText(null);
                            alert.setContentText("Lease Released");
                            alert.showAndWait();
                            rootStage.close();
                            stage.show();
                    });
                    Thread thread = new Thread(task);
                    thread.start();
                }
            }
        });


        back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				rootStage.close();
				stage.show();
			}


		});
    }

    public static void addSubscription(Stage rootStage, Stage stage, GridPane root, Property p1, Tenant tenant) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Subscription Added");
        alert.setHeaderText(null);
        alert.setContentText("Subscription Added");
        alert.showAndWait();
        rootStage.close();
        stage.show();
        // create a new thread to execute the subscribe method
        Thread subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                LeaseController.getInstance().subscribe(p1, tenant);
            }
        });
        subscribeThread.start();
    }
    public static void addLease(Stage stage, Property p1, Tenant tenant, int value, Stage rootStage, GridPane root) {
        Stage stage2 = new Stage();
        GridPane newRoot = new GridPane();
        stage2.setTitle("Lease details");
        DatePicker datePicker = new DatePicker();
        LocalDate today = LocalDate.now(); // get the current date
        datePicker.setValue(today);
        datePicker.setPromptText("Select a date");
        newRoot.getChildren().add(datePicker);
        Button submit = new Button("Submit");
        newRoot.add(submit, 1, 3, 1, 1);
        Scene scene = new Scene(newRoot, 700, 700);
        scene.getStylesheets().add(TenantView.class.getResource("css2.css").toExternalForm());
        stage2.setScene(scene);
        stage2.show();

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Thread thread = new Thread(() -> {
                    Date startDate = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        startDate = new SimpleDateFormat("dd/MM/yyyy").parse(datePicker.getValue().format(formatter));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startDate);
                    cal.add(Calendar.YEAR, 1);
                    Date endDate= cal.getTime();
                    LeaseController.getInstance().addLease(tenant, p1, startDate, endDate, value);
                    Platform.runLater(() -> {
                        
                            // Show a confirmation message if the property is added successfully
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Lease Added");
                            alert.setHeaderText(null);
                            alert.setContentText("Lease Added");
                            alert.showAndWait();
                            stage.show();
                            stage2.close();
                            rootStage.close();
                    });
                });
                thread.start();
            }
        });
    }
}
