import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    // initialize all input/output fields
    private ComboBox<String> databaseURLField = new ComboBox<>();
    private ComboBox<String> jdbcDriverField = new ComboBox<>();
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();

    private TextArea logTextArea = new TextArea();

    private Label dbConnectionLabel = new Label();
    private Label batchUpdateLabel = new Label();

    private Connection dbConnection;
    private boolean dbConnected = false;

    @Override
    public void start(Stage stage) {
        // batch update screen
        BorderPane batchUpdatePane = new BorderPane();
        batchUpdatePane.setPadding(new Insets(10));

        // position UI like in exercise prompt
        Button openDialogButton = new Button("Connect to database");
        openDialogButton.setAlignment(Pos.CENTER_RIGHT);
        Pane batchUpdateTopSpacer = new Pane();
        HBox.setHgrow(batchUpdateTopSpacer, Priority.ALWAYS);
        HBox batchUpdateTop = new HBox(batchUpdateLabel, batchUpdateTopSpacer, openDialogButton);
        batchUpdateTop.setSpacing(10);
        BorderPane.setMargin(batchUpdateTop, new Insets(0, 0, 10, 0));

        Button batchUpdateButton = new Button("Batch Update");
        Button nonBatchUpdateButton = new Button("Non-Batch Update");
        HBox batchUpdateBottom = new HBox(batchUpdateButton, nonBatchUpdateButton);
        batchUpdateBottom.setSpacing(10);
        batchUpdateBottom.setAlignment(Pos.CENTER);
        BorderPane.setMargin(batchUpdateBottom, new Insets(10, 0, 0, 0));

        logTextArea.setEditable(false);

        batchUpdatePane.setTop(batchUpdateTop);
        batchUpdatePane.setCenter(logTextArea);
        batchUpdatePane.setBottom(batchUpdateBottom);

        // dialog for db connection screen
        BorderPane dialogBorderPane = new BorderPane();
        GridPane dbConnectionForm = new GridPane();

        // set ComboBox options
        jdbcDriverField.setEditable(true);
        jdbcDriverField.getItems().addAll("com.mysql.cj.jdbc.Driver");
        databaseURLField.setEditable(true);
        databaseURLField.getItems().addAll("jdbc:mysql://localhost:3306/javabook");

        // create UI like in exercise prompt
        dbConnectionForm.addRow(0, new Label("JDBC Drive"), jdbcDriverField);
        dbConnectionForm.addRow(1, new Label("Database URL"), databaseURLField);
        dbConnectionForm.addRow(2, new Label("Username"), usernameField);
        dbConnectionForm.addRow(3, new Label("Password"), passwordField);
        dbConnectionForm.setVgap(10);
        dbConnectionForm.setHgap(10);

        VBox dialogBottom = new VBox();

        Button connectToDbButton = new Button("Connect to DB");
        Button closeDialogButton = new Button("Close Dialog");

        HBox connectToDbBox = new HBox(connectToDbButton);
        connectToDbBox.setAlignment(Pos.CENTER_RIGHT);
        HBox closeDialogBox = new HBox(closeDialogButton);
        closeDialogBox.setAlignment(Pos.CENTER);

        VBox.setMargin(connectToDbBox, new Insets(10, 0, 10, 0));

        dialogBottom.getChildren().addAll(connectToDbBox, closeDialogBox);

        dialogBorderPane.setTop(dbConnectionLabel);
        dialogBorderPane.setCenter(dbConnectionForm);
        dialogBorderPane.setBottom(dialogBottom);
        dialogBorderPane.setPadding(new Insets(10));

        // scenes can be set to change screens
        Scene batchUpdateScene = new Scene(batchUpdatePane);
        Scene dialogScene = new Scene(dialogBorderPane);

        // navigation between screens
        openDialogButton.setOnMouseClicked(e -> {
            stage.setScene(dialogScene);
            stage.setTitle("Connect to Database");
        });
        closeDialogButton.setOnMouseClicked(e -> {
            stage.setScene(batchUpdateScene);
            stage.setTitle("Exercise 35.01");
        });

        // db update handling
        batchUpdateButton.setOnMouseClicked(e -> sendBatchUpdate());
        nonBatchUpdateButton.setOnMouseClicked(e -> sendNonBatchUpdate());

        // db connection request handling
        connectToDbButton.setOnMouseClicked(e -> connectToDb());

        stage.setScene(batchUpdateScene);
        stage.setTitle("Exercise 35.01");
        stage.show();
    }

    // attempt to connect to DB using user input
    private void connectToDb() {
        try {
            Class.forName(jdbcDriverField.getValue());

            dbConnection = DriverManager.getConnection(databaseURLField.getValue(), usernameField.getText(),
                    passwordField.getText());
            dbConnectionLabel.setText("Successfully connected to database!");
            dbConnected = true;
            dbConnection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            dbConnectionLabel.setText("Error connecting to database");
        }
    }

    // send and time a batch update, outputting results to the user
    private void sendBatchUpdate() {
        if (!dbConnected) {
            batchUpdateLabel.setText("To send updates, you must first connect to a database");
            return;
        }
        try {
            long startTime = System.nanoTime();

            PreparedStatement ps = dbConnection.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");

            for (int i = 0; i < 1000; i++) {
                ps.setDouble(1, Math.random());
                ps.setDouble(2, Math.random());
                ps.setDouble(3, Math.random());
                ps.addBatch();
            }

            ps.executeBatch();
            dbConnection.commit();

            long elapsedTime = System.nanoTime() - startTime;
            logTextArea.setText(logTextArea.getText() + "\nBatch update completed\nElapsed time: " + elapsedTime);

            batchUpdateLabel.setText("Batch update successful");
        } catch (Exception e) {
            batchUpdateLabel.setText("Error executing batch update");
            e.printStackTrace();
        }
    }

    // send and time a non-batch update, outputting results to the user
    private void sendNonBatchUpdate() {
        if (!dbConnected) {
            batchUpdateLabel.setText("To send updates, you must first connect to a database");
            return;
        }
        try {
            PreparedStatement ps = dbConnection.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
            long startTime = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                ps.setDouble(1, Math.random());
                ps.setDouble(2, Math.random());
                ps.setDouble(3, Math.random());
                ps.execute();
            }
            dbConnection.commit();

            long elapsedTime = System.nanoTime() - startTime;
            logTextArea.setText(logTextArea.getText() + "\nNon-batch update completed\nElapsed time: " + elapsedTime);

            batchUpdateLabel.setText("Non-batch update successful");
        } catch (Exception e) {
            batchUpdateLabel.setText("Error executing non-batch update");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
