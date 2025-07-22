package br.edu.ifba.inf008.interfaces;

import javafx.scene.Scene;

public interface INavigationController {

    public abstract void showScene(Scene newScene);

    public abstract void goBack();

    public abstract void showScreen(String pluginName);

}
