package br.edu.ifba.inf008.plugins.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.ifba.inf008.plugins.LoanManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.ILoanService;
import br.edu.ifba.inf008.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterLoanView {

    public static void show() {

        ILoanService loanService = LoanManagmentMenu.getInstance().getLoanService();

        Stage editStage = new Stage();
        editStage.setTitle("Register menu");


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatedData = dataAtual.format(formatter);

        TextField userField = UIUtils.createTextField("Insert the user's ID", "");
        TextField bookField = UIUtils.createTextField("Insert the book's ID", "");
        TextField loanDateField = UIUtils.createTextField("Insert the loan's date (dd/MM/yyyy)", formatedData);

        Button registerBtn = new Button("Register");

        Label feedback = new Label();

        registerBtn.setOnAction(event -> {

            boolean success = false;

            int user = Integer.parseInt(userField.getText());
            int book = Integer.parseInt(bookField.getText());

            String inputDate = loanDateField.getText();
            

            try {

                LocalDate parsedDate = LocalDate.parse(inputDate, formatter);
                success = loanService.registerLoan(user, book, parsedDate);

            } catch (Exception e) {
                UIUtils.showAlert(e.getMessage());
            }

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
    
}

