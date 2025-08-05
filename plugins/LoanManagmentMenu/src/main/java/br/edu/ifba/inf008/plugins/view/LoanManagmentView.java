package br.edu.ifba.inf008.plugins.view;

import br.edu.ifba.inf008.plugins.LoanManagmentMenu;
import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.shell.Core;
import br.edu.ifba.inf008.utils.UIUtils;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class LoanManagmentView {
    
    public static Node create() {
        
        VBox layout = new VBox(100);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Search by user's id");
        nameField.setMaxWidth(300);
        nameField.setAlignment(Pos.TOP_RIGHT);

        Button searchBtn = new Button("🔍 Search");
        searchBtn.setAlignment(Pos.CENTER_RIGHT);

        Button devolutionBtn = RegisterDevolutionView.createDevolutionBtn();
        devolutionBtn.setAlignment(Pos.CENTER);

        Button registerLoanBtn = RegisterLoanView.createRegisterBtn();
        registerLoanBtn.setAlignment(Pos.CENTER);

        HBox searchBox = new HBox(10, nameField, searchBtn, devolutionBtn, registerLoanBtn);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        TableView<Loan> resultsTable = createTableView();

        searchBtn.setOnAction(event -> {
            List<Loan> loans;

            try {

                loans = LoanManagmentMenu.getInstance().getLoanService().searchLoansByUserId(Integer.parseInt(nameField.getText().trim()));

            } catch (NumberFormatException e) {

                UIUtils.showAlert("Wrong id format (only numbers)");
                loans = Collections.emptyList();
            }

            resultsTable.setItems(FXCollections.observableArrayList(loans));

        });

        resultsTable.getStylesheets().add(Core.class.getResource("/css/dark-table.css").toExternalForm());

        layout.getChildren().addAll(searchBox, resultsTable);

        return layout;
    }

    private static TableView<Loan> createTableView() {

        TableView<Loan> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxSize(1000.0,1000.0);

        TableColumn<Loan, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Loan, Integer> userCol = new TableColumn<>("user_ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<Loan, Integer> bookCol = new TableColumn<>("book_ID");
        bookCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Loan, LocalDate> dateCol = new TableColumn<>("loan_date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("loanDate"));

        TableColumn<Loan, LocalDate> returnDateCol = new TableColumn<>("return_date");
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        table.getColumns().addAll(idCol, userCol, bookCol, dateCol, returnDateCol);

        return table;
    }
}
