import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateUser {
    
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    private ArrayList<User> users;

    private Stage loginPage;

    public CreateUser(ArrayList<String> usernames, ArrayList<String> passwords, ArrayList<User> users, Stage loginPage) {
        this.usernames = usernames;
        this.passwords = passwords;
        this.loginPage = loginPage;
        this.users = users;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Create User Page!");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label message = new Label("");

        Button createButton = new Button("Create User");
        createButton.setPrefWidth(120);

        Button returnButton = new Button("Return to Admin Dashboard");

        createButton.setOnAction(e -> {
            String user = username.getText();
            String pass = password.getText();
            
            if (user.isEmpty() || pass.isEmpty()) {
                message.setText("User creation failed");
                return;
            }

            boolean exists = false;
            for (User u : users) {
                if (u.getUsername().equals(user)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                message.setText("User already exists");
            } else {

                User newUser = new User(user, pass);
                users.add(newUser);

                username.clear();
                password.clear();

                message.setText("User " + user + " created successfully!"); 
            }
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
