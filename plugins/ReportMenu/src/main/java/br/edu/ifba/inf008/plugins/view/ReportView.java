package br.edu.ifba.inf008.plugins.view;

import br.edu.ifba.inf008.plugins.model.BorrowedBookReportData;
import br.edu.ifba.inf008.plugins.service.ReportService;
import br.edu.ifba.inf008.shell.Core;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ReportView {

    public static Node create(ReportService reportService) {
        TableView<BorrowedBookReportData> reportTable = createTableView();

        reportTable.setItems(FXCollections.observableArrayList(reportService.getBorrowedBooksReport()));

        reportTable.getStylesheets().add(Core.class.getResource("/css/dark-table.css").toExternalForm());

        VBox layout = new VBox(10, reportTable);
        layout.setStyle("-fx-padding: 20;");

        return layout;
    }

    private static TableView<BorrowedBookReportData> createTableView() {

        TableView<BorrowedBookReportData> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<BorrowedBookReportData, String> titleCol = new TableColumn<>("Título");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        TableColumn<BorrowedBookReportData, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));

        TableColumn<BorrowedBookReportData, String> userCol = new TableColumn<>("Usuário");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<BorrowedBookReportData, String> dateCol = new TableColumn<>("Data do Empréstimo");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("loanDate"));

        table.getColumns().addAll(titleCol, authorCol, userCol, dateCol);
        return table;
    }
}
