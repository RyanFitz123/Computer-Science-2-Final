import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAdmin {
    
    private ArrayList<String> usernamesAd;
    private ArrayList<String> passwordsAd;

    private Stage loginPage;

    public CreateAdmin(ArrayList<String> usernamesAd, ArrayList<String> passwordsAd, Stage loginPage) {
        this.usernamesAd = usernamesAd;
        this.passwordsAd = passwordsAd;
        this.loginPage = loginPage;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Create Admin Page!");

        TextField username = new TextField();
        username.setPromptText("Admin Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Admin Password");

        Label message = new Label("");

        Button createButton = new Button("Create Admin");
        createButton.setPrefWidth(120);

        Button returnButton = new Button("Return to Login");
        returnButton.setPrefWidth(120);

        createButton.setOnAction(e -> {
            String user = username.getText();
            String pass = password.getText();
            
            usernamesAd.add(user);
            passwordsAd.add(pass);

            message.setText("Admin " + user + " created successfully!"); 
        });

        returnButton.setOnAction(e -> {
    
            stage.close();
            loginPage.show();

        });

        VBox layout = new VBox(20, welcomeLabel, username, password, createButton, message, returnButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 400);

        stage.setTitle("Create User Page");
        stage.setScene(scene);
        stage.show();

    }

}
