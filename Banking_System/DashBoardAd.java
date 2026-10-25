import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashBoardAd {
    
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    private ArrayList<User> users;

    private Stage loginPage;

    public DashBoardAd(ArrayList<String> usernames, ArrayList<String> passwords, ArrayList<User> users, Stage loginPage) {
        this.usernames = usernames;
        this.passwords = passwords;
        this.users = users;
        this.loginPage = loginPage;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Admin Dashboard!");

        Label message = new Label("");

        Button CreateButton = new Button("Create User");
        CreateButton.setPrefWidth(120);

        CreateButton.setOnAction(e -> {
            
            CreateUser createUser = new CreateUser(usernames, passwords, users, stage);
            stage.hide();
            createUser.show();

        });

        Button deleteButton = new Button("Delete User");
        deleteButton.setPrefWidth(120);

        deleteButton.setOnAction(e -> {
            
            DeleteUser deleteUser = new DeleteUser(users, stage);
            stage.hide();
            deleteUser.show();

        });

        Button suspendButton = new Button("Suspend / Unsuspend User");

        suspendButton.setOnAction(e -> {
            
            SuspendUser suspendUser = new SuspendUser(users, stage);
            stage.hide();
            suspendUser.show();

        });

        Button historyButton = new Button("View Transaction History");

        historyButton.setOnAction(e -> {
            
            TransactionHistory transactionHistory = new TransactionHistory(users, stage);
            stage.hide();
            transactionHistory.show();

        });

        Button displayUserButton = new Button("Display Users");

        displayUserButton.setOnAction(e -> {
            message.setText("Registered Users:\n" + getUserList());
        });

        Button logOutButton = new Button("Log Out");
        logOutButton.setPrefWidth(120);

        logOutButton.setOnAction(e -> {
            stage.close();
            loginPage.show();
        });

        VBox layout = new VBox(20, welcomeLabel, CreateButton, suspendButton, deleteButton, historyButton, displayUserButton, message, logOutButton);
        layout.setStyle("-fx-alignment: top_center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 1000, 600);

        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();

    }

    private String getUserList() {
        StringBuilder userList = new StringBuilder();
        for (User user : users) {
            userList.append("Username: ").append(user.getUsername()).append(" | Password: ").append(user.getPassword()).append("\n");
        }
        return userList.toString();
    }

}
