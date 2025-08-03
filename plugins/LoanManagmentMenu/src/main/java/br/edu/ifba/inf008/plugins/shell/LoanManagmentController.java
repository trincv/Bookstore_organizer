package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.crud.LoanManagmentView;
import br.edu.ifba.inf008.plugins.crud.RegisterLoanView;
import javafx.scene.layout.StackPane;

public class LoanManagmentController {
    
    private StackPane contentPane;

    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public void viewLoanMangment() {
        contentPane.getChildren().setAll(LoanManagmentView.create());
    }
}
