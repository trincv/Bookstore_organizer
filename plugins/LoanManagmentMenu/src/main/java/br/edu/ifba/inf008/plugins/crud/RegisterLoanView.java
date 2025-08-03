package br.edu.ifba.inf008.plugins.crud;

import java.sql.Date;
import java.util.Optional;

import br.edu.ifba.inf008.plugins.LoanManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.ILoanService;
import br.edu.ifba.inf008.plugins.model.Loan;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class RegisterLoanView {

    public static void show() {

        ILoanService loanService = LoanManagmentMenu.getInstance().getLoanService();

        Stage editStage = new Stage();
        editStage.setTitle("Register menu");


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        TextField userField = createTextField("Insert the user's ID");
        TextField bookField = createTextField("Insert the book's ID");
        TextField loanDateField = createTextField("Insert the loan's date (dd/MM/yyyy)");

        Button registerBtn = new Button("Register");

        Label feedback = new Label();

        registerBtn.setOnAction(event -> {

            int user = Integer.parseInt(userField.getText());
            int book = Integer.parseInt(bookField.getText());
            Date loanDate = Date.valueOf(ILoanService.stringToLocalDate(loanDateField.getText()));

            boolean success = loanService.registerLoan(user, book, loanDate);

            if(success) {

                feedback.setText("book registered successfully!");
                feedback.setStyle("-fx-text-fill: lightgreen;");
                userField.clear();
                bookField.clear();
                loanDateField.clear();

            }else {
                
                feedback.setText("Failed to register book.");
                feedback.setStyle("-fx-text-fill: red;");

            }
        });

        layout.getChildren().addAll(
            userField,
            bookField,
            loanDateField,
            registerBtn,
            feedback
        );

        Scene scene = new Scene(layout);
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.showAndWait();
    }

    public static Button createRegisterBtn() {

        Button registerBtn = new Button("register loan");

        registerBtn.setOnAction(e -> { RegisterLoanView.show(); });

        return registerBtn;
    }

    private static TextField createTextField(String promptText) {

        TextField textField = new TextField();
        textField.setMaxWidth(600);
        textField.setPromptText(promptText);

        return textField;
    }
    
}

