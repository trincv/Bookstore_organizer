package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.crud.RegisterUserView;
import br.edu.ifba.inf008.plugins.crud.ShowUserView;

import javafx.scene.layout.StackPane;

public class UserManagmentController {
    
    private final StackPane contentPane;

    public UserManagmentController(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public void ShowUsers() {
        contentPane.getChildren().setAll(ShowUserView.create());
    }

    public void RegisterUser() {
        contentPane.getChildren().setAll(RegisterUserView.create());
    }
}
