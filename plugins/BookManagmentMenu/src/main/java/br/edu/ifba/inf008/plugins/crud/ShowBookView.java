package br.edu.ifba.inf008.plugins.crud;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.model.Book;
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

import java.util.List;

public class ShowBookView {
    
    public static Node create() {
        
        VBox layout = new VBox(100);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setAlignment(Pos.TOP_RIGHT);

        Button searchBtn = new Button("🔍 Search");
        searchBtn.setAlignment(Pos.CENTER_RIGHT);

        Button editBookBtn = EditBookView.createEditBtn();
        editBookBtn.setAlignment(Pos.CENTER);

        Button deleteBookBtn = DeleteBookView.createDeleteBtn();
        deleteBookBtn.setAlignment(Pos.CENTER);

        HBox searchBox = new HBox(10, titleField, searchBtn, editBookBtn, deleteBookBtn);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        TableView<Book> resultsTable = createTableView();

        searchBtn.setOnAction(event -> {

            List<Book> books = BookManagmentMenu.getInstance().getBookService().searchBooksByTitle(titleField.getText().trim());
            resultsTable.setItems(FXCollections.observableArrayList(books));

        });

        resultsTable.getStylesheets().add(ShowBookView.class.getResource("/CSS/dark-table.css").toExternalForm());

        layout.getChildren().addAll(searchBox, resultsTable);

        return layout;
    }

    private static TableView<Book> createTableView() {

        TableView<Book> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxSize(1000.0,1000.0);

        TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Book, String> publishedYearCol = new TableColumn<>("Published year");
        publishedYearCol.setCellValueFactory(new PropertyValueFactory<>("publishedYear"));

        TableColumn<Book, String> copiesCol = new TableColumn<>("Copies Available");
        copiesCol.setCellValueFactory(new PropertyValueFactory<>("copiesAvailable"));


        table.getColumns().addAll(idCol, titleCol, authorCol, isbnCol, publishedYearCol, copiesCol);

        return table;
    }
}
