package br.edu.ifba.inf008.plugins.crud;

import java.util.Optional;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.model.Book;
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

public class EditBookView {

    public static void show(Book book, Runnable onSuccess) {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        Stage editStage = new Stage();
        editStage.setTitle("Edit Book");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField titleField = new TextField(book.getTitle());
        TextField authorField = new TextField(book.getAuthor());
        TextField IsbnField = new TextField(book.getIsbn());
        TextField publishedYearField = new TextField(Integer.toString(book.getPublishedYear()));
        TextField copiesField = new TextField(Integer.toString(book.getCopiesAvailable()));

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setIsbn(IsbnField.getText());
            book.setPublishedYear(Integer.parseInt(publishedYearField.getText()));
            book.setCopiesAvailable(Integer.parseInt(copiesField.getText()));

            boolean updated = bookService.updateBook(book);

            if (updated) {
                editStage.close();
                if (onSuccess != null) onSuccess.run();
            } else {
                UIUtils.showAlert("Failed to update book.");
            }
        });

        layout.getChildren().addAll(
            new Label("Title:"), titleField,
            new Label("Author:"), authorField,
            new Label("ISBN:"), IsbnField,
            new Label("Published Year:"), publishedYearField,
            new Label("Copies available:"), copiesField,
            saveBtn
        );

        Scene scene = new Scene(layout);
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.showAndWait();
    }


    public static Button createEditBtn() {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        Button editBookBtn = new Button("Edit Book");

        editBookBtn.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Edit Book");
            dialog.setHeaderText("Enter the ID of the Book to edit:");
            dialog.setContentText("Book ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(idStr -> {

                try {
                    int id = Integer.parseInt(idStr);
                    Book book = bookService.getBookById(id);

                    if (book != null) {
                        EditBookView.show(book, () -> {});
                    } else {
                        UIUtils.showAlert("Book not found.");
                    }
                } catch (NumberFormatException ex) {
                   UIUtils.showAlert("Invalid ID format.");
                }
            });
        });

        return editBookBtn;
    }
        
}
