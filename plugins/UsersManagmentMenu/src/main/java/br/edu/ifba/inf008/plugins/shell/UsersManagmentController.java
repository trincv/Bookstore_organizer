package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.crud.ShowUsersView;

import javafx.scene.layout.StackPane;

public class UsersManagmentController {
    
    private final StackPane contentPane;

    public UsersManagmentController(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public void ShowUsers() {
        contentPane.getChildren().setAll(ShowUsersView().create());
    }
}
