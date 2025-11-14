package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField passwordField;
    @FXML private TextField emailField;
    @FXML private ChoiceBox<String> typeBox;
    @FXML private Button registerButton;
    @FXML private Button backToLoginButton;

    private static final String DATA_FILE = "src/application/data.csv";

    @FXML
    private void initialize() {
        typeBox.getItems().addAll("User", "Admin");
        typeBox.setValue("User");
    }

    @FXML
    private void register() {
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();
        String type = typeBox.getValue();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        // Admin 密码必须包含 "Admin"
        if (type.equals("Admin") && !password.contains("Admin")) {
            showAlert("Notice", "Registration request received (Admin verification required).");
            return;
        }

        try {
            // 检查用户名是否已存在
            if (userExists(name)) {
                showAlert("Error", "Username already exists!");
                return;
            }

            // 会员状态逻辑：普通用户默认 Non-member，Admin 默认 Member
            String membership = type.equals("Admin") ? "Member" : "Non-member";

            // 写入 CSV 文件，包含 membership 字段
            try (FileWriter fw = new FileWriter(DATA_FILE, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                pw.println(name + "," + password + "," + email + "," + type + "," + membership);
            }

            showAlert("Success", "Registration successful!");
            Main.changeScene("login.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to register user.");
        }
    }

    private boolean userExists(String username) throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 1 && fields[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    private void backToLogin() {
        try {
            Main.changeScene("login.fxml");
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
