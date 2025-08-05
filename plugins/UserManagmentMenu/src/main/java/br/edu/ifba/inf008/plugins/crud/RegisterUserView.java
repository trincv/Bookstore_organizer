package br.edu.ifba.inf008.plugins.crud;

import br.edu.ifba.inf008.plugins.UserManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.IUserService;
import br.edu.ifba.inf008.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterUserView {

    public static Node create() {

        IUserService userService = UserManagmentMenu.getInstance().getUserService();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = UIUtils.createTextField("Insert name");
        TextField emailField = UIUtils.createTextField("Insert Email");

        Button registerBtn = new Button("Register");

        Label feedback = new Label();

        registerBtn.setOnAction(event -> {

            String name = nameField.getText();
            String email = emailField.getText();

            boolean success =userService.registerUser(name, email);

            if(success) {

                feedback.setText("User registered successfully!");
                feedback.setStyle("-fx-text-fill: lightgreen;");
                nameField.clear();
                emailField.clear();

            }else {
                
                feedback.setText("Failed to register user.");
                feedback.setStyle("-fx-text-fill: red;");

            }
        });

        layout.getChildren().addAll(
            nameField,
            emailField,
            registerBtn,
            feedback
        );

        return layout;
    }
}

