package br.edu.ifba.inf008.interfaces;

import javafx.scene.Node;
import javafx.scene.Scene;

public interface IPlugin
{
    public abstract String getName();

    public abstract boolean init(INavigationController navController);

    public Node createNavButton(Runnable action);

    public Scene getScene();
}
