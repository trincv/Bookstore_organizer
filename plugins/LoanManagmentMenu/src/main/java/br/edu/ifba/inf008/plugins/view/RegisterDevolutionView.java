package br.edu.ifba.inf008.plugins.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import br.edu.ifba.inf008.plugins.LoanManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.ILoanService;
import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterDevolutionView {

    public static void show(Loan loan, Runnable onSuccess) {

        ILoanService loanService = LoanManagmentMenu.getInstance().getLoanService();

        Stage editStage = new Stage();
        editStage.setTitle("devolution");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatedData = dataAtual.format(formatter);

        TextField returnDateField = new TextField(formatedData);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            
            String dateString = returnDateField.getText();
            boolean updated = loanService.registerDevolution(loan, dateString);

            if (updated) {
                editStage.close();
                if (onSuccess != null) onSuccess.run();
            } else {
                UIUtils.showAlert("Failed to update loan.");
            }
        });

        layout.getChildren().addAll(
            new Label("Return date:"), returnDateField,
            saveBtn
        );

        Scene scene = new Scene(layout);
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.showAndWait();
    }


    public static Button createDevolutionBtn() {

        ILoanService loanService = LoanManagmentMenu.getInstance().getLoanService();

        Button devolutionBtn = new Button("register devolution");

        devolutionBtn.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Devolution");
            dialog.setHeaderText("Enter the ID of the loan to edit:");
            dialog.setContentText("Loan ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(idStr -> {

                try {
                    int id = Integer.parseInt(idStr);
                    Loan loan = loanService.getLoanById(id);

                    if (loan != null && loan.isActive()) {
                        RegisterDevolutionView.show(loan, () -> {});
                    } else {

                        if(loan == null) UIUtils.showAlert("Loan not found.");

                        else UIUtils.showAlert("The loan was already closed");
                    }
                } catch (NumberFormatException ex) {
                UIUtils.showAlert("Invalid ID format.");
                }
            });
        });

        return devolutionBtn;
    }
        
}
