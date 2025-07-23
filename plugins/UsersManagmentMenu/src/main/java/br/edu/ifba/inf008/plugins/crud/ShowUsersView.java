package br.edu.ifba.inf008.plugins.crud;

import br.edu.ifba.inf008.plugins.dao.UsersDao;
import br.edu.ifba.inf008.entities.User;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.List;

public class ShowUsersView {
    
    public static Node create() {
        
        VBox layout = new Vbox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Search by name");

        Button searchBtn = new Button("🔍 Search");
        VBox resultsBox = new VBox(5);

        searchBtn.setOnAction(event -> {
            resultsBox.getChildren().clear();

            List<User> users = UserDao.searchUsersByName(nameField.getText());

            if(users.isEmpty()) {
                resultsBox.getChildren().add(new Label("No users found."));
            } else {
                for (User user : users) {
                    Label lbl = new Label(user.getId() + ": " + user.getName() + " - " + user.getEmail());
                    resultsBox.getChildren().add(lbl);
                }
            }
        });

        layout.getChildren().addAll(nameField, searchBtn, resultsBox);
        return layout;
    }
}
