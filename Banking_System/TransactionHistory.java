import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransactionHistory {

    private ArrayList<User> users;

    private Stage loginPage;

    public TransactionHistory(ArrayList<User> users, Stage loginPage) {
        this.loginPage = loginPage;
        this.users = users;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Transaction History Page!");

        TextField username = new TextField();
        username.setPromptText("Username");

        Label message = new Label("");

        Label title = new Label("Transaction History");
        TextArea historyArea = new TextArea();
        historyArea.setEditable(false);

        Button viewHistoryButton = new Button("View Transaction History");
        viewHistoryButton.setPrefWidth(120);        

        viewHistoryButton.setOnAction(e -> {
            String user = username.getText();
            
            if (user.isEmpty()) {
                message.setText("User transaction history retrieval failed");
                return;
            }

            boolean exists = false;
            for (User u : users) {
                if (u.getUsername().equals(user)) {
                    
                    for (String i : u.getTransactionHistory()) {
                        historyArea.appendText(i + "\n");
                    }

                    exists = true;
                    message.setText("User " + user + " transaction history displayed successfully!");
                    break;
                }
            }

            if (!exists) {
                message.setText("User not found");
            }

            username.clear();
        });

        Button returnButton = new Button("Return to Admin Dashboard");
        
        returnButton.setOnAction(e -> {
    
            stage.close();
            loginPage.show();

        });

        VBox layout = new VBox(20, welcomeLabel, username, viewHistoryButton, title, historyArea, message, returnButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 400);

        stage.setTitle("Create User Page");
        stage.setScene(scene);
        stage.show();

    }

}
