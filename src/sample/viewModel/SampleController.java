package sample.viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import java.io.IOException;

import flightsim.client.Client;
import flightsim.client.SimpleClient;

public class SampleController extends SplitPane
{
    final Client client=new SimpleClient();

    Property<Boolean> booleanProperty=new SimpleBooleanProperty();
    Property<Boolean> enableProperty =new SimpleObjectProperty<>(false);

    @FXML
    MapController leftSide;

    @FXML
    Right_SideController rightSide;


    public SampleController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/JavaFX Components/full_app.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        bind();

        Image image  = new Image(getClass().getResourceAsStream("/sample/images/sky.jpg"));
        BackgroundImage bgImage = new BackgroundImage(
				image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false)
				);
		this.setBackground(new Background(bgImage));
    }

    public void bind(){
        leftSide.bind(client,enableProperty);
        rightSide.setClient(client);
        enableProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) rightSide.enableAll();
        });
    }
}
