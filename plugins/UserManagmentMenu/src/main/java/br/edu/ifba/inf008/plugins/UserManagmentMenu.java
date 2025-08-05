package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.INavigationController;
import br.edu.ifba.inf008.plugins.shell.UserManagmentController;
import br.edu.ifba.inf008.utils.UIUtils;
import br.edu.ifba.inf008.plugins.interfaces.IUserService;
import br.edu.ifba.inf008.plugins.service.UserService;
import br.edu.ifba.inf008.plugins.dao.UserDao;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserManagmentMenu implements IPlugin {

    private static UserManagmentMenu instance;

    private IUserService userService;
    private UserManagmentController userManagmentController;
    private INavigationController navigationController;
    private Scene contentView;

    public UserManagmentMenu() {}

    public static UserManagmentMenu getInstance() {
        if (instance == null) {

            throw new IllegalStateException("UserManagmentMenu não foi inicializado pelo Core via ServiceLoader.");

        }
        return instance;
    }

    @Override
    public String getName() {
        return "UsersManagmentMenu";
    }

    @Override
    public Scene getScene() { return contentView; }

    @Override
    public boolean init(INavigationController navController) {

        instance = this; 

        UserDao userDAO = new UserDao();
        this.userService = new UserService(userDAO);

        this.userManagmentController = new UserManagmentController();
        this.navigationController = navController;

        this.contentView = createPluginUI(); 

        System.out.println("Plugin " + getName() + " inicializado com sucesso.");
        
        return true;
    }

    @Override
    public Node createNavButton(Runnable action) {

        return UIUtils.createNavButtonWithIcon(
            "Users Management", 
            "/icons/usersIcon.png", 
            getClass(), 
            action
        );
        
    }

    private Scene createPluginUI() {
        
        BorderPane root = new BorderPane();
        root.getStyleClass().add("my-border-pane"); 

        // Top bar
        Label title = new Label("Users Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(10));
        HBox topBar = new HBox(title);
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: #2b2b3c;");
        root.setTop(topBar);

        // Side menu
        VBox sideMenu = new VBox(20);
        sideMenu.setPadding(new Insets(20));
        sideMenu.setStyle("-fx-background-color: #2b2b3c;");
        sideMenu.setPrefWidth(200);
        sideMenu.setAlignment(Pos.TOP_LEFT);

        Button registerBtn = UIUtils.createInternNavButton("Register");
        Button searchBtn = UIUtils.createInternNavButton("Search");
        Button backBtn = UIUtils.createInternNavButton("Back");
        
        // Ações dos botões
        registerBtn.setOnAction(event -> userManagmentController.registerUser());
        searchBtn.setOnAction(event -> userManagmentController.showUsers());
        backBtn.setOnAction(e -> navigationController.goBack());

        sideMenu.getChildren().addAll(registerBtn, searchBtn, backBtn);
        root.setLeft(sideMenu);

        // Center pane
        Label placeholder = new Label("Select an action from the left menu.");
        placeholder.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        placeholder.setTextFill(Color.LIGHTGRAY);
        StackPane centerPane = new StackPane(placeholder);
        centerPane.setStyle("-fx-background-color: #202030;");
        root.setCenter(centerPane);

        userManagmentController.setContentPane(centerPane);

        Scene scene = new Scene(root);

        return scene;
    }

    public IUserService getUserService() {return userService;};
    
}
