package br.edu.ifba.inf008.plugins.crud;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterBookView {

    public static Node create() {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        TextField titleField = createTextField("Insert title");
        TextField authorField = createTextField("Insert author's name");
        TextField isbnField = createTextField("Insert book's isbn");
        TextField publishedYearField = createTextField("Insert book's published year");
        TextField copiesAvailableField = createTextField("Insert book's avaiable copies");

        Button registerBtn = new Button("Register");

        Label feedback = new Label();

        registerBtn.setOnAction(event -> {

            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int published_year = Integer.parseInt(publishedYearField.getText());
            int copies_available = Integer.parseInt(copiesAvailableField.getText());

            boolean success = bookService.registerBook(title, author, isbn, published_year, copies_available);

            if(success) {

                feedback.setText("book registered successfully!");
                feedback.setStyle("-fx-text-fill: lightgreen;");
                titleField.clear();
                authorField.clear();
                isbnField.clear();
                publishedYearField.clear();
                copiesAvailableField.clear();

            }else {
                
                feedback.setText("Failed to register book.");
                feedback.setStyle("-fx-text-fill: red;");

            }
        });

        layout.getChildren().addAll(
            titleField,
            authorField,
            isbnField,
            publishedYearField,
            copiesAvailableField,
            registerBtn,
            feedback
        );

        return layout;
    }

    private static TextField createTextField(String promptText) {

        TextField textField = new TextField();
        textField.setMaxWidth(600);
        textField.setPromptText(promptText);

        return textField;
    }
    
}

