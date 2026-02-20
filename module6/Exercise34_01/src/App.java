import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    private Label messageLabel = new Label();

    private TextField idField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField miField = new TextField();
    private TextField addressField = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField telephoneField = new TextField();

    private Connection connection;

    @Override
    public void start(Stage stage) {
        initalizeDB();

        // outer pane is borderPane - form fields in gridPane
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();

        // arranged by rows like in exercise prompt
        gridPane.addRow(0, new Label("ID"), idField);
        gridPane.addRow(1, new Label("Last Name"), lastNameField, new Label("First Name"), firstNameField,
                new Label("MI"), miField);
        gridPane.addRow(2, new Label("Address"), addressField);
        gridPane.addRow(3, new Label("City"), cityField, new Label("State"), stateField);
        gridPane.addRow(4, new Label("Telephone"), telephoneField);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // button options stored in HBox on the bottom
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");
        HBox bottomRow = new HBox(viewButton, insertButton, updateButton, clearButton);
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setSpacing(10);

        borderPane.setTop(messageLabel);
        borderPane.setCenter(gridPane);
        borderPane.setPadding(new Insets(10));
        borderPane.setBottom(bottomRow);
        BorderPane.setMargin(bottomRow, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.show();

        viewButton.setOnMousePressed(e -> viewStaff());
        insertButton.setOnMousePressed(e -> insertStaff());
        updateButton.setOnMousePressed(e -> updateStaff());
        clearButton.setOnMousePressed(e -> clearFields());

    }

    private void initalizeDB() {
        String url = "jdbc:mysql://localhost:3306/staff";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // use the connection to make a preparedStatement and view all staff fields, if
    // found
    private void viewStaff() {
        String id = idField.getText();
        try {
            String sql = "SELECT firstName, lastName, mi, address, city, state, telephone FROM staff WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                String lastName = rset.getString(1);
                String firstName = rset.getString(2);
                String mi = rset.getString(3);
                String address = rset.getString(4);
                String city = rset.getString(5);
                String state = rset.getString(6);
                String telephone = rset.getString(7);

                lastNameField.setText(lastName);
                firstNameField.setText(firstName);
                miField.setText(mi);
                addressField.setText(address);
                cityField.setText(city);
                stateField.setText(state);
                telephoneField.setText(telephone);
                messageLabel.setText("Found record!");

            } else {
                messageLabel.setText("No record found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // use preparedStatement to insert new staff data, if a duplicate is found,
    // update the row instead
    private void insertStaff() {
        String sql = "INSERT INTO staff (id, lastName, firstName, mi, address, city, state, telephone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, idField.getText());
            ps.setString(2, lastNameField.getText());
            ps.setString(3, firstNameField.getText());
            ps.setString(4, miField.getText());
            ps.setString(5, addressField.getText());
            ps.setString(6, cityField.getText());
            ps.setString(7, stateField.getText());
            ps.setString(8, telephoneField.getText());
            ps.setString(9, lastNameField.getText());
            ps.setString(10, firstNameField.getText());
            ps.setString(11, miField.getText());
            ps.setString(12, addressField.getText());
            ps.setString(13, cityField.getText());
            ps.setString(14, stateField.getText());
            ps.setString(15, telephoneField.getText());

            ps.executeUpdate();
            clearFields();
            messageLabel.setText("Successfully added staff information!");
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Error adding staff information");
        }
    }

    // update staff data
    private void updateStaff() {
        String sql = "UPDATE staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(8, idField.getText());
            ps.setString(1, lastNameField.getText());
            ps.setString(2, firstNameField.getText());
            ps.setString(3, miField.getText());
            ps.setString(4, addressField.getText());
            ps.setString(5, cityField.getText());
            ps.setString(6, stateField.getText());
            ps.setString(7, telephoneField.getText());

            ps.executeUpdate();
            clearFields();
            messageLabel.setText("Successfully updated information!");
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Error updating staff information");
        }
    }

    // clear all staff fields
    private void clearFields() {
        idField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        miField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        telephoneField.setText("");
        messageLabel.setText("Cleared fields!");
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
