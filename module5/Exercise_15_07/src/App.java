import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // create circle with border and set at center

        Circle circle = new Circle(100);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(10);
        circle.setCenterX(145);
        circle.setCenterY(145);

        Pane pane = new Pane(circle);
        pane.setPadding(new Insets(25, 25, 25, 25));

        // handle mouse press and release
        pane.setOnMousePressed((e) -> {
            circle.setFill(Color.BLACK);
        });

        pane.setOnMouseReleased((e) -> {
            circle.setFill(Color.WHITE);
        });

        // launch scene
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        stage.setTitle("Exercise 15.7");
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
