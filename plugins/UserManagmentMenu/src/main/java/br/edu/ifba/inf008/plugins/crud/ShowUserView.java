package br.edu.ifba.inf008.plugins.crud;

import br.edu.ifba.inf008.plugins.UserManagmentMenu;
import br.edu.ifba.inf008.plugins.model.User;
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

import java.util.List;

public class ShowUserView {
    
    public static Node create() {
        
        VBox layout = new VBox(100);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        TextField nameField = UIUtils.createTextField("Search by user's name");
        nameField.setAlignment(Pos.TOP_RIGHT);

        Button searchBtn = new Button("🔍 Search");
        searchBtn.setAlignment(Pos.CENTER_RIGHT);

        Button editUserBtn = EditUserView.createEditBtn();
        editUserBtn.setAlignment(Pos.CENTER);

        Button deleteUserBtn = DeleteUserView.createDeleteBtn();
        deleteUserBtn.setAlignment(Pos.CENTER);

        HBox searchBox = new HBox(10, nameField, searchBtn, editUserBtn, deleteUserBtn);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        TableView<User> resultsTable = createTableView();

        searchBtn.setOnAction(event -> {

            List<User> users = UserManagmentMenu.getInstance().getUserService().searchUsersByName(nameField.getText().trim());
            resultsTable.setItems(FXCollections.observableArrayList(users));

        });

        resultsTable.getStylesheets().add(Core.class.getResource("/css/dark-table.css").toExternalForm());

        layout.getChildren().addAll(searchBox, resultsTable);

        return layout;
    }

    private static TableView<User> createTableView() {

        TableView<User> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxSize(1000.0,1000.0);

        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(idCol, nameCol, emailCol);

        return table;
    }
}
