package br.edu.ifba.inf008.plugins.crud;

import java.util.Optional;

import br.edu.ifba.inf008.plugins.UserManagmentMenu;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.interfaces.IUserService;
import br.edu.ifba.inf008.plugins.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class DeleteUserView {

    public static Button createDeleteBtn() {

        IUserService userService = UserManagmentMenu.getInstance().getUserService();

        Button deleteUserBtn = new Button("Delete User");

        deleteUserBtn.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete User");
            dialog.setHeaderText("Enter the ID of the user to delete:");
            dialog.setContentText("User ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(idStr -> {

                try {
                    int id = Integer.parseInt(idStr);

                    if (userService.getUserById(id) != null) {
                        userService.deleteUser(id);
                    } else {
                        showAlert("User not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid ID format.");
                }
            });
        });

        return deleteUserBtn;
    }

    private static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
