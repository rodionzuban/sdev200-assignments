import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // import flag images and set proper aspect ratios

        Image flag1 = new Image("file:Images/flag1.gif");
        ImageView flag1ImageView = new ImageView(flag1);
        flag1ImageView.setFitWidth(400);
        flag1ImageView.setFitHeight(300);

        Image flag2 = new Image("file:Images/flag2.gif");
        ImageView flag2ImageView = new ImageView(flag2);
        flag2ImageView.setFitWidth(400);
        flag2ImageView.setFitHeight(300);

        Image flag3 = new Image("file:Images/flag6.gif");
        ImageView flag3ImageView = new ImageView(flag3);
        flag3ImageView.setFitWidth(400);
        flag3ImageView.setFitHeight(300);

        Image flag4 = new Image("file:Images/flag7.gif");
        ImageView flag4ImageView = new ImageView(flag4);
        flag3ImageView.setFitWidth(400);
        flag3ImageView.setFitHeight(300);

        // create new grid pane
        GridPane gridPane = new GridPane();

        // add padding
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        // gaps between images
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setAlignment(Pos.CENTER);

        // add images in correct position
        // NOTE: Germany's flag as shown in textbook is replaced by UK flag provided by
        // instructor
        gridPane.add(flag2ImageView, 0, 0);
        gridPane.add(flag4ImageView, 1, 0);
        gridPane.add(flag3ImageView, 0, 1);
        gridPane.add(flag1ImageView, 1, 1);

        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.setTitle("Exercise 14.01");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}