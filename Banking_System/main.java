import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class main extends Application{
        
        private static ArrayList<User> users = new ArrayList<>();

        private static ArrayList<String> usernamesAd = new ArrayList<>();
        private static ArrayList<String> passwordsAd = new ArrayList<>();
        
    public void start(Stage stage) {

        LoginPage(stage);

    }

    public static void LoginPage(Stage stage) {

        User newUser = new User("a", "a");
        users.add(newUser); 

        usernamesAd.add("a");
        passwordsAd.add("a");

        Label title = new Label("Login Page");

        //User
        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label message = new Label("");

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(120);

        //Admin
        TextField usernameAd = new TextField();
        usernameAd.setPromptText("Admin Username");

        PasswordField passwordAd = new PasswordField();
        passwordAd.setPromptText("Admin Password");

        Button loginButtonAd = new Button("Admin Login");
        loginButtonAd.setPrefWidth(120);

        //Create

        Button CreateButtonAd = new Button("Create Admin");
        CreateButtonAd.setPrefWidth(120);

        loginButton.setOnAction(e -> {
            String name = username.getText();
            String pass = password.getText();

            boolean found = false;
            User loggedInUser = null;

            for (int i = 0; i < users.size(); i ++){
                User u = users.get(i);
                if (u.getUsername().equals(name) && u.getPassword().equals(pass)){
                    found = true;
                    loggedInUser = u;
                    break;
                }
            }

            for (User u : users) {
                if (u.getUsername().equals(name) && u.getPassword().equals(pass)) {
                    if (u.isSuspended()) {
                        message.setText("User is suspended");
                        return;
                    }
                    loggedInUser = u;
                    clearFieldsUser(username, password);
                    break;
                }
            }

            if (found && loggedInUser != null){

                message.setText("Login Succesful!");
                DashBoardUser dashboard = new DashBoardUser(stage, loggedInUser);
                dashboard.show();

                stage.hide();

            } else {
                message.setText("Login Failed!");
            }

            clearFieldsUser(username, password);
        });

        loginButtonAd.setOnAction(e -> {
            String name = usernameAd.getText();
            String pass = passwordAd.getText();

            if (usernamesAd.contains(name) && passwordsAd.contains(pass)){
                if (usernamesAd.indexOf(name) == passwordsAd.indexOf(pass)){
                    message.setText("Login Succesful!");

                    DashBoardAd dashboard = new DashBoardAd(usernamesAd, passwordsAd, users, stage);
                    dashboard.show();

                    stage.hide();
                } else {
                    message.setText("Login Failed!");
                }
            } else {
                message.setText("Login Failed!");
            }

            clearFieldsAdmin(usernameAd, passwordAd);
        });

        CreateButtonAd.setOnAction(e -> {
            CreateAdmin createAdmin = new CreateAdmin(usernamesAd, passwordsAd, stage);
            stage.hide();
            createAdmin.show();
        });

        VBox layout = new VBox(12);
        layout.getChildren().addAll(title, username, password, 
            loginButton, usernameAd, passwordAd, loginButtonAd, 
            CreateButtonAd, message);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 800, 700);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();

    }

    public static void clearFieldsUser(TextField username, PasswordField password){
        username.clear();
        password.clear();
    }

    public static void clearFieldsAdmin(TextField usernameAd, PasswordField passwordAd){
        usernameAd.clear();
        passwordAd.clear();
    }

    public static void main(String[] args) {

        launch();

    }

}
