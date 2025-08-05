package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.INavigationController;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LibraryMenu implements IPlugin
{

    private Scene contentView;

    @Override
    public String getName(){
        return "LibraryMenu";
    }

    @Override
    public Scene getScene() { return contentView; }

    @Override
    public boolean init(INavigationController navController) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e2f;");

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
        HBox iconsBar = new HBox();
        iconsBar.setAlignment(Pos.CENTER);
        iconsBar.setSpacing(150);
        iconsBar.setPadding(new Insets(100, 100, 100, 100));
        iconsBar.setStyle("-fx-background-color: #202030");
        iconsBar.getChildren().addAll(createPluginsButton(navController));

        root.setTop(topBar);
        root.setLeft(leftBar);
        root.setCenter(iconsBar);
        
        Scene menuScene = new Scene(root, 800, 600);
        this.contentView = menuScene;

        return true;
    }

    // Somente para poder implementar IPlugin
    @Override
    public Node createNavButton(Runnable action) { 
        Node node = new Button();
        return node;
    }

    private List<Node> createPluginsButton(INavigationController navController) {
        List<Node> pluginsButton = new ArrayList<>();
        List<IPlugin> pluginsLoaded = ICore.getInstance().getPluginController().getAllLoadedPlugins();

        for(IPlugin plugin : pluginsLoaded) {

            if(plugin.getName() != (this).getName()) {

            Runnable action = () -> {navController.showScene(plugin.getScene());};
            pluginsButton.add(plugin.createNavButton(action));
            
            }
        }

        return pluginsButton;
    }
}
