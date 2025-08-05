package br.edu.ifba.inf008.utils;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UIUtils {
    
    /**
     * Exibe um alerta de erro na interface do usuário.
     * @param msg A mensagem a ser exibida.
     */
    public static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Converte uma string no formato "dd/MM/yyyy" para um objeto LocalDate.
     * @param dateString A string com a data.
     * @return O LocalDate correspondente ou null se o formato for inválido.
     */
    public static LocalDate stringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null; 
        }
    }

    /**
     * Cria um botão de navegação com texto e um ícone.
     *
     * @param buttonText O texto do botão.
     * @param iconPath O caminho do ícone dentro da pasta de recursos do plugin.
     * @param contextClass A classe do plugin que está carregando o recurso.
     * @param action A ação a ser executada quando o botão é clicado.
     * @return Um objeto Node (botão) com o ícone.
     */

    public static Node createNavButtonWithIcon(String buttonText, String iconPath, Class<?> contextClass, Runnable action) {
        Button btn = new Button(buttonText);
        btn.setPrefSize(250, 250);
        btn.setStyle("-fx-background-color: #3a3a4d; -fx-text-fill: white;");
        btn.setOnAction(e -> action.run());

        try {
            InputStream iconStream = contextClass.getResourceAsStream(iconPath);
            if (iconStream != null) {
                Image iconImage = new Image(iconStream);
                ImageView iconView = new ImageView(iconImage);
                iconView.setFitHeight(100);
                iconView.setFitWidth(100);
                
                btn.setGraphic(iconView);
                btn.setContentDisplay(ContentDisplay.TOP);
                btn.setGraphicTextGap(30);
            } else {
                System.err.println("Icon not found at: " + iconPath + " for class " + contextClass.getName());
            }
        } catch (Exception e) {
            System.err.println("Could not load icon: " + e.getMessage());
        }

        return btn;
    }

    public static Button createInternNavButton(String label) {
        Button btn = new Button(label);
        btn.setPrefWidth(160);

        if (label == "Back") btn.setStyle("-fx-background-color: #444; -fx-text-fill: white;");

        else btn.setStyle("-fx-background-color: #3a3a4d; -fx-text-fill: white;");

        return btn;
    }

    public static TextField createTextField(String promptText, String contextText) {

        TextField textField = new TextField(contextText);
        textField.setMaxWidth(600);
        textField.setPromptText(promptText);

        return textField;
    }
    public static TextField createTextField(String promptText) {

        TextField textField = new TextField();
        textField.setMaxWidth(600);
        textField.setPromptText(promptText);

        return textField;
    }
}
