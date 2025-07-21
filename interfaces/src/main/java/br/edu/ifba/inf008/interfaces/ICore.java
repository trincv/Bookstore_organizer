package br.edu.ifba.inf008.interfaces;

import javafx.stage.Stage;

public abstract class ICore
{
    public static ICore getInstance() {
        return instance;
    }

    public abstract IUIController getUIController();

    public abstract IAuthenticationController getAuthenticationController();

    public abstract IIOController getIOController();

    public abstract IPluginController getPluginController();

    public abstract INavigationController getNavigationController();

    public abstract void initNavigationController(Stage stage);

    protected static ICore instance = null;
}
