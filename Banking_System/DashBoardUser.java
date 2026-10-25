import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DashBoardUser {
    
    private Stage loginPage;
    private User loggedInUser;
    private ArrayList<User> users;

    public DashBoardUser(Stage loginPage, User loggedInUser) {
        this.loginPage = loginPage;
        this.loggedInUser = loggedInUser;
        this.users = users;
    }

    public void show() {

        Stage stage = new Stage();

        Label welcomeLabel = new Label("Welcome to the Dashboard!");
        
        //Log Out
        Button logOutButton = new Button("Log Out");
        logOutButton.setPrefWidth(120);

        logOutButton.setOnAction(e -> {
            stage.close();
            loginPage.show();
        });

        Label message = new Label("");

        //Transaction History
        Label transactionLabel = new Label("Transaction History");
        TextArea historyArea = new TextArea();
        historyArea.setEditable(false);
        refreshTransactionHistory(historyArea);

        //Deposit
        TextField depositAmount = new TextField();
        depositAmount.setPromptText("Deposit Amount");

        Button depositButton = new Button("Deposit");
        depositButton.setPrefWidth(120);

        depositButton.setOnAction(e -> {

            String input = depositAmount.getText();

            try {

                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    message.setText("Amount is less than 0");
                    return;
                }
                loggedInUser.Deposit(amount);
                message.setText("Deposited: $" + amount);
                refreshTransactionHistory(historyArea);

            } catch (NumberFormatException ex) {
                message.setText("Invalid deposit amount.");
                depositAmount.clear();
            }

            depositAmount.clear();

        });

        //Withdraw
        TextField withdrawAmount = new TextField();
        withdrawAmount.setPromptText("Withdraw Amount");

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setPrefWidth(120);

        withdrawButton.setOnAction(e -> {

            String input = withdrawAmount.getText();
            
            try {

                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    message.setText("Amount is less than 0");
                    return;
                }
                
                int processed = loggedInUser.Withdraw(amount);
                if (processed == 1) {
                    message.setText("Withdrew: $" + amount);
                    refreshTransactionHistory(historyArea);
                } else {
                    message.setText("Withdraw Failed.");
                }

            } catch (NumberFormatException ex) {
                message.setText("Invalid withdraw amount.");
                withdrawAmount.clear();
                return;
            }

            withdrawAmount.clear();

        });

        //Display Balance
        Button displayButton = new Button("Display Balance");
        displayButton.setPrefWidth(120);

        displayButton.setOnAction(e -> {

            message.setText("Current Balance: $" + loggedInUser.getBalance());

        });

        VBox layout = new VBox(20, welcomeLabel, depositAmount, depositButton, withdrawAmount, withdrawButton, displayButton, transactionLabel, message, historyArea, logOutButton);
        layout.setStyle("-fx-alignment: top_center; -fx-padding: 20;");
        
        Scene scene = new Scene(layout, 1000, 600);

        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();

    }

    private void refreshTransactionHistory(TextArea logArea) {
        logArea.clear();
        for (String entry : loggedInUser.getTransactionHistory()) {
            logArea.appendText(entry + "\n");
        }
        
    }

}
