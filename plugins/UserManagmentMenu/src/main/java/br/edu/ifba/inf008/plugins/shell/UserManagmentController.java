package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.crud.RegisterUserView;
import br.edu.ifba.inf008.plugins.crud.ShowUserView;
import javafx.scene.layout.StackPane;

public class UserManagmentController {
    
    private StackPane contentPane;

    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public void showUsers() {
        contentPane.getChildren().setAll(ShowUserView.create());
    }

    public void registerUser() {
        contentPane.getChildren().setAll(RegisterUserView.create());
    }
}
