package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.INavigationController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController implements INavigationController{

    private final Stage mainStage;

    public NavigationController(Stage stage) {
        this.mainStage = stage;
    }

    @Override
    public void showLibraryManagment(){
        Scene menuScene = new Scene(null);
        mainStage.setScene(menuScene);
    }

    @Override
    public void showUsersManagment(){
        Scene usersScene = new Scene(null);
        mainStage.setScene(usersScene);
    }

    @Override
    public void showBooksManagment(){
        Scene booksScene = new Scene(null);
        mainStage.setScene(booksScene);
    }

    @Override
    public void showLoansManagment(){
        Scene loansScene = new Scene(null);
        mainStage.setScene(loansScene);
    }
}
