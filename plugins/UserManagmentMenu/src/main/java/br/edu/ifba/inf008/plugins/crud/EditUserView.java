package br.edu.ifba.inf008.plugins.crud;

import java.util.Optional;

import br.edu.ifba.inf008.entities.User;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditUserView {

    public static void show(User user, Runnable onSuccess) {
        Stage editStage = new Stage();
        editStage.setTitle("Edit User");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField(user.getName());
        TextField emailField = new TextField(user.getEmail());

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            user.setName(nameField.getText());
            user.setEmail(emailField.getText());

            boolean updated = UserDao.updateUser(user);
            if (updated) {
                editStage.close();
                if (onSuccess != null) onSuccess.run();
            } else {
                showAlert("Failed to update user.");
            }
        });

        layout.getChildren().addAll(
            new Label("Name:"), nameField,
            new Label("Email:"), emailField,
            saveBtn
        );

        Scene scene = new Scene(layout);
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.showAndWait();
    }


    public static Button createEditBtn() {

        Button editUserBtn = new Button("Edit User");

        editUserBtn.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Edit User");
            dialog.setHeaderText("Enter the ID of the user to edit:");
            dialog.setContentText("User ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(idStr -> {

                try {
                    int id = Integer.parseInt(idStr);
                    User user = UserDao.getUserById(id);

                    if (user != null) {
                        EditUserView.show(user, () -> {});
                    } else {
                        showAlert("User not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid ID format.");
                }
            });
        });

        return editUserBtn;
    }

    private static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }
        
}
