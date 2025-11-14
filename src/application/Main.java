package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("=== Application starting ===");
        primaryStage = stage;
        
        System.out.println("Loading login.fxml...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        
        System.out.println("Setting up stage...");
        stage.setTitle("CoWorking Platform - Login");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen(); 
        
        System.out.println("Showing stage...");
        stage.show();
        stage.toFront();
        stage.requestFocus();
        
        
        javafx.application.Platform.setImplicitExit(true);
        
        System.out.println("=== Stage should be visible now ===");
        System.out.println("Stage X: " + stage.getX() + ", Y: " + stage.getY());
        System.out.println("Stage Width: " + stage.getWidth() + ", Height: " + stage.getHeight());
    }

    public static void changeScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(loader.load(), 600, 400);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        System.out.println("=== Main method called ===");
        launch(args);
    }
}
