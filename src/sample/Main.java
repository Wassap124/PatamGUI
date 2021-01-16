package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.viewModel.SampleController;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SampleController controller=new SampleController();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/sample/images/planes/plane-0.png")));
        primaryStage.setTitle("Flight Simulator GUI");
        primaryStage.setScene(new Scene(controller, 1200, 520));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
