package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.crud.RegisterBookView;
import br.edu.ifba.inf008.plugins.crud.ShowBookView;
import javafx.scene.layout.StackPane;

public class BookManagmentController {
    
    private StackPane contentPane;

    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public void showUsers() {
        contentPane.getChildren().setAll(ShowBookView.create());
    }

    public void registerUser() {
        contentPane.getChildren().setAll(RegisterBookView.create());
    }
}
