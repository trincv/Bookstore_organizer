package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.INavigationController;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UsersManagment implements IPlugin
{

    public String getName(){
        return "UsersManagment";
    }

    public boolean init() {
        
        INavigationController navController = ICore.getInstance().getNavigationController();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e2f;");

        // topo / titulo:
        Label title = new Label("Users Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(10));
        HBox topBar = new HBox(title);
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: #2b2b3c;");
        root.setTop(topBar);

        // menu lateral:
        VBox sideMenu = new VBox(20);
        sideMenu.setPadding(new Insets(20));
        sideMenu.setStyle("-fx-background-color: #2b2b3c;");
        sideMenu.setPrefWidth(200);
        sideMenu.setAlignment(Pos.TOP_LEFT);

        Button registerBtn = createNavButton("Register");
        Button listBtn = createNavButton("List");
        Button editBtn = createNavButton("Edit");
        Button deleteBtn = createNavButton("Delete");

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(160);
        backBtn.setStyle("-fx-background-color: #444; -fx-text-fill: white;");
        backBtn.setOnAction(e -> navController.goBack());

        sideMenu.getChildren().addAll(registerBtn, listBtn, editBtn, deleteBtn, backBtn);
        root.setLeft(sideMenu);

        // Centro:
        Label placeholder = new Label("Select an action from the left menu.");
        placeholder.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        placeholder.setTextFill(Color.LIGHTGRAY);
        StackPane centerPane = new StackPane(placeholder);
        centerPane.setStyle("-fx-background-color: #202030;");
        root.setCenter(centerPane);

        // Cria nova cena e exibe
        Scene scene = new Scene(root, 800, 600);
        navController.showScene(scene);

        return true;
        
    }

    private Button createNavButton(String label) {
        Button btn = new Button(label);
        btn.setPrefWidth(160);
        btn.setStyle("-fx-background-color: #3a3a4d; -fx-text-fill: white;");
        return btn;
    }

}
