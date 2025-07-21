package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.stage.Stage;

public interface IUIController
{
    public abstract MenuItem createMenuItem(String menuText, String menuItemText);

    public abstract boolean createTab(String tabText, Node contents);

    public abstract Stage getStage();
    
    public abstract BorderPane getSceneRoot();
}
