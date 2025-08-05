package br.edu.ifba.inf008.plugins.crud;

import java.util.Optional;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.utils.UIUtils;
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
                        UIUtils.showAlert("Book not found.");
                    }
                } catch (NumberFormatException ex) {
                    UIUtils.showAlert("Invalid ID format.");
                }
            });
        });

        return deleteBookBtn;
    }
}
