package br.edu.ifba.inf008.shell;

import java.util.ArrayDeque;
import java.util.Deque;

import br.edu.ifba.inf008.interfaces.INavigationController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController implements INavigationController{

    private final Stage mainStage;
    private final Deque<Scene> sceneStack = new ArrayDeque<>();

    public NavigationController(Stage stage) {
        this.mainStage = stage;
    }

    public void showScene(Scene newScene) {

        try {
            Scene curretScene = mainStage.getScene();
            sceneStack.push(curretScene);
            mainStage.setScene(newScene);
            mainStage.show();

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void goBack() {

        try {
            Scene previousScene = sceneStack.pop();
            mainStage.setScene(previousScene);
            mainStage.show();

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void showScreen(String pluginName) {

            IPlugin plugin = Core.getInstance().getPluginController().getPlugin(pluginName);

            if(plugin == null) {
                System.err.println("Plugin " + pluginName + " não inicializado");
                return;
            }
            
            boolean sucess = plugin.init(this);

            if(sucess == false) {
                System.err.println("Plugin " + pluginName + " falhou ao inicializar");
                return;
            }
            
            showScene(plugin.getScene());
    }
}
