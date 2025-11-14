package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    private static final String DATA_FILE = "src/application/data.csv";

    @FXML
    private void login() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    String u = fields[0];
                    String p = fields[1];
                    String type = fields[3];

                    if (u.equals(username) && p.equals(password)) {
                        // 使用 FXMLLoader 手动加载界面
                    	 UserController.setCurrentUser(username);
                    	 AdminController.setCurrentAdmin(username); 
                    	 BookingController.setCurrentUser(username);
                    	 UsersController.setCurrentUser(username);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            type.equalsIgnoreCase("Admin") ? "admin.fxml" : "user.fxml"
                        ));
                        Scene scene = new Scene(loader.load(), 600, 400);
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(scene);
                        return;
                    }
                }
            }
            showAlert("Error", "Invalid username or password!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister() {
        try {
            Main.changeScene("register.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
