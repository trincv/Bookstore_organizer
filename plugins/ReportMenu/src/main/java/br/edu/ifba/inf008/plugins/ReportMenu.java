package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.INavigationController;
import br.edu.ifba.inf008.plugins.view.ReportView;
import br.edu.ifba.inf008.plugins.dao.BookDao;
import br.edu.ifba.inf008.plugins.dao.LoanDao;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.service.ReportService;
import br.edu.ifba.inf008.plugins.shell.ReportMenuController;
import br.edu.ifba.inf008.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReportMenu implements IPlugin {

    private ReportService reportService;
    private INavigationController navigationController;
    private ReportMenuController reportMenuController;
    private Scene contentView;

    @Override
    public String getName() {
        return "ReportMenu";
    }

    @Override
    public boolean init(INavigationController navController) {

        LoanDao loanDao = new LoanDao();
        BookDao bookDao = new BookDao();
        UserDao userDao = new UserDao();
        this.reportService = new ReportService(loanDao, bookDao, userDao);

        ReportMenuController reportMenuController = new ReportMenuController(reportService);

        this.reportMenuController = reportMenuController;
        this.navigationController = navController;
        this.contentView = createPluginUI();

        System.out.println("Plugin " + getName() + " inicializado com sucesso.");
        return true;
    }

    private Scene createPluginUI() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("my-border-pane"); 

        // Top bar
        Label title = new Label("Reports");
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

        Button booksReportBtn = UIUtils.createInternNavButton("Books report");
        Button backBtn = UIUtils.createInternNavButton("Back");
        
        // Ações dos botões
        booksReportBtn.setOnAction(event -> reportMenuController.showBookReportView());
        backBtn.setOnAction(e -> navigationController.goBack());

        sideMenu.getChildren().addAll(booksReportBtn, backBtn);
        root.setLeft(sideMenu);

        // Center pane
        Label placeholder = new Label("Select an action from the left menu.");
        placeholder.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        placeholder.setTextFill(Color.LIGHTGRAY);
        StackPane centerPane = new StackPane(placeholder);
        centerPane.setStyle("-fx-background-color: #202030;");
        root.setCenter(centerPane);

        reportMenuController.setContentPane(centerPane);

        Scene scene = new Scene(root);

        return scene;
    }

    @Override
    public Node createNavButton(Runnable action) {

        return UIUtils.createNavButtonWithIcon(
            "Reports",
            "/icons/report.png", 
            this.getClass(),
            action
        );
    }

    @Override
    public Scene getScene() {
        return this.contentView;
    }
}