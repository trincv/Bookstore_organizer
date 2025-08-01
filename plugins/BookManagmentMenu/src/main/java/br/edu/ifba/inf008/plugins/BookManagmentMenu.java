package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.INavigationController;
import br.edu.ifba.inf008.plugins.shell.BookManagmentController;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.service.BookService;
import br.edu.ifba.inf008.plugins.dao.BookDao;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BookManagmentMenu implements IPlugin {

    private static BookManagmentMenu instance;

    private IBookService bookService;
    private BookManagmentController bookManagmentController;
    private INavigationController navigationController;

    public BookManagmentMenu() {}

    public static BookManagmentMenu getInstance() {
        if (instance == null) {

            throw new IllegalStateException("BookManagmentMenu não foi inicializado pelo Core via ServiceLoader.");

        }
        return instance;
    }

    @Override
    public String getName() {
        return "BookManagmentMenu";
    }

    @Override
    public boolean init(INavigationController navController) {

        instance = this; 

        BookDao bookDAO = new BookDao();
        this.bookService = new BookService(bookDAO);

        this.bookManagmentController = new BookManagmentController();
        this.navigationController = navController;

        createPluginUI(); 

        System.out.println("Plugin " + getName() + " inicializado com sucesso.");
        
        return true;
    }

    private void createPluginUI() {
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

        Button registerBtn = createNavButton("Register");
        Button searchBtn = createNavButton("Search");
        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(160);
        backBtn.setStyle("-fx-background-color: #444; -fx-text-fill: white;");
        
        // Ações dos botões
        registerBtn.setOnAction(event -> bookManagmentController.registerUser());
        searchBtn.setOnAction(event -> bookManagmentController.showUsers());
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

        bookManagmentController.setContentPane(centerPane);

        Scene scene = new Scene(root);

        navigationController.showScene(scene);
    }

    private Button createNavButton(String label) {
        Button btn = new Button(label);
        btn.setPrefWidth(160);
        btn.setStyle("-fx-background-color: #3a3a4d; -fx-text-fill: white;");
        return btn;
    }

    public IBookService getBookService() {return bookService;};
    
}
