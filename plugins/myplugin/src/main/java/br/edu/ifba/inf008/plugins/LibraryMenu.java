package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LibraryMenu implements IPlugin
{

    public String getName(){
        return "LibraryMenu";
    }

    public boolean init() {
        
        IUIController uiController = ICore.getInstance().getUIController();

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(5, 5, 5, 5));
        topBar.setStyle("-fx-background-color:rgb(0, 0, 5)");

        Label titleLabel = new Label("Library Management Menu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        topBar.getChildren().add(titleLabel);

        VBox leftBar = new VBox();
        leftBar.setAlignment(Pos.CENTER);
        leftBar.setPrefWidth(50.0);
        leftBar.setStyle("-fx-background-color:rgb(0, 0, 3)");

        VBox usersBox = createIconBox("/icons/usersIcon.png", "users"); 
        VBox booksBox = createIconBox("/icons/booksIcon.png", "books"); 
        VBox loansBox = createIconBox("/icons/loansIcon.png", "loans"); 
    
        HBox iconsBar = new HBox();
        iconsBar.setAlignment(Pos.CENTER);
        iconsBar.setSpacing(150);
        iconsBar.setPadding(new Insets(100, 100, 100, 100));
        iconsBar.getChildren().addAll(usersBox, booksBox, loansBox);

        uiController.getSceneRoot().setTop(topBar);
        uiController.getSceneRoot().setLeft(leftBar);
        uiController.getSceneRoot().setCenter(iconsBar);
        

        return true;
    }

    private VBox createIconBox(String imagePath, String labelText) {
        
        ImageView icon = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        icon.setFitHeight(200);
        icon.setFitWidth(200);

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        label.setAlignment(Pos.CENTER);

        VBox box = new VBox(10, icon, label);
        box.setAlignment(Pos.CENTER);

        return box;
    }

}
