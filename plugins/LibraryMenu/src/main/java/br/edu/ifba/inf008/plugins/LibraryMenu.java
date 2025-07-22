package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class LibraryMenu implements IPlugin
{

    public String getName(){
        return "LibraryMenu";
    }

    public boolean init() {
        
        IUIController uiController = ICore.getInstance().getUIController();

        // topo / titulo:
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(5, 5, 5, 5));
        topBar.setStyle("-fx-background-color: #2b2b3c");
        
        Label titleLabel = new Label("Library Management Menu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        topBar.getChildren().add(titleLabel);

        // lateral / somente cor:
        VBox leftBar = new VBox();
        leftBar.setAlignment(Pos.CENTER);
        leftBar.setPrefWidth(50.0);
        leftBar.setStyle("-fx-background-color: #2b2b3c");

        // centro / icones:
        VBox usersBox = createIconBox("/icons/usersIcon.png", "users", "UsersManagment"); 
        VBox booksBox = createIconBox("/icons/booksIcon.png", "books", "BooksManagment"); 
        VBox loansBox = createIconBox("/icons/loansIcon.png", "loans", "LoansMangment"); 
    
        HBox iconsBar = new HBox();
        iconsBar.setAlignment(Pos.CENTER);
        iconsBar.setSpacing(150);
        iconsBar.setPadding(new Insets(100, 100, 100, 100));
        iconsBar.getChildren().addAll(usersBox, booksBox, loansBox);
        iconsBar.setStyle("-fx-background-color: #202030");

        uiController.getSceneRoot().setTop(topBar);
        uiController.getSceneRoot().setLeft(leftBar);
        uiController.getSceneRoot().setCenter(iconsBar);
        

        return true;
    }

    private VBox createIconBox(String imagePath, String labelText, String pluginName) {
        
        ImageView icon = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        icon.setFitHeight(200);
        icon.setFitWidth(200);
        icon.setOnMouseClicked(event -> {ICore.getInstance().getNavigationController().showScreen(pluginName);});

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.LIGHTGRAY);

        VBox box = new VBox(10, icon, label);
        box.setAlignment(Pos.CENTER);

        return box;
    }

}
