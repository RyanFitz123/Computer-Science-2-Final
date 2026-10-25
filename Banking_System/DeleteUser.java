import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteUser {

    private ArrayList<User> users;

    private Stage loginPage;

    public DeleteUser(ArrayList<User> users, Stage loginPage) {
        this.loginPage = loginPage;
        this.users = users;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Delete User Page!");

        TextField username = new TextField();
        username.setPromptText("Username");

        Label message = new Label("");

        Button deleteButton = new Button("Delete User");
        deleteButton.setPrefWidth(120);

        Button returnButton = new Button("Return to Admin Dashboard");

        deleteButton.setOnAction(e -> {
            String user = username.getText();
            
            if (user.isEmpty()) {
                message.setText("User deletion failed");
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
                boolean deleted = deleteUser(user, users);
                if (deleted) {
                    message.setText("User " + user + " deleted successfully!");
                } else {
                    message.setText("User deletion failed");
                }
            } else {
                message.setText("User does not exist");
            }  
        });

        returnButton.setOnAction(e -> {
    
            stage.close();
            loginPage.show();

        });

        VBox layout = new VBox(20, welcomeLabel, username, deleteButton, message, returnButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 400);

        stage.setTitle("Create User Page");
        stage.setScene(scene);
        stage.show();

    }

    public boolean deleteUser(String username, ArrayList<User> users) {
        
        for (int i = 0; i < users.size(); i++) {
            
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                return true; 
            }

        }

        return false; 
    }

}
