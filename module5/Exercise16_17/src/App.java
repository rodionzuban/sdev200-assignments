import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));

        Label showColorsText = new Label("Show Colors");
        showColorsText.setTextFill(Color.BLACK);
        StackPane topPane = new StackPane(showColorsText);

        StackPane.setAlignment(showColorsText, Pos.CENTER);

        GridPane colorPickerPane = new GridPane();

        colorPickerPane.setHgap(10);
        colorPickerPane.setVgap(10);

        colorPickerPane.setPadding(new Insets(10));

        Slider redSlider = new Slider(0, 255, 0);
        Slider greenSlider = new Slider(0, 255, 0);
        Slider blueSlider = new Slider(0, 255, 0);
        Slider opacitySlider = new Slider(0, 1, 1);

        Label redText = new Label("Red");
        Label greenText = new Label("Green");
        Label blueText = new Label("Blue");
        Label opacityText = new Label("Opacity");

        colorPickerPane.addColumn(0, redText, greenText, blueText, opacityText);
        colorPickerPane.addColumn(1, redSlider, greenSlider, blueSlider, opacitySlider);

        topPane.setOnMouseClicked(e -> {
            showColorsText.setTextFill(
                    new Color(redSlider.getValue() / 255, greenSlider.getValue() / 255, blueSlider.getValue() / 255,
                            opacitySlider.getValue()));
        });

        mainPane.setTop(topPane);
        mainPane.setCenter(colorPickerPane);

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);

        stage.setTitle("Exercise 16.17");
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
