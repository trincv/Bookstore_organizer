package br.edu.ifba.inf008.plugins.crud;

import java.util.Optional;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class DeleteBookView {

    public static Button createDeleteBtn() {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        Button deleteBookBtn = new Button("Delete Book");

        deleteBookBtn.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Book");
            dialog.setHeaderText("Enter the ID of the book to delete:");
            dialog.setContentText("Book ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(idStr -> {

                try {
                    int id = Integer.parseInt(idStr);

                    if (bookService.getBookById(id) != null) {
                        bookService.deleteBook(id);
                    } else {
                        showAlert("Book not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid ID format.");
                }
            });
        });

        return deleteBookBtn;
    }

    private static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
