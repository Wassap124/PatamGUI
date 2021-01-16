package sample.viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

import java.io.IOException;

import flightsim.FlyMain;
import flightsim.client.Client;

public class Right_SideController extends SplitPane {

    @FXML
    Radio_ButtonsController radioButtons;
    @FXML
    Display_ScriptController displayScript;
    @FXML
    JoystickController joystick;

    FlyMain flyMain=new FlyMain();
    Property<Boolean> autopilotProperty=new SimpleBooleanProperty();
    Property<Boolean> manualProperty=new SimpleBooleanProperty();


    public Right_SideController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/JavaFX Components/right_side.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        joystick.setDisable(true);
        bind();
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
    }
    private void bind(){
        autopilotProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue)
            {
                joystick.setDisable(true);
                flyMain.setAutoPilot(true);
            }
        });
        manualProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue)
            {
                joystick.setDisable(false);
                try {
                    joystick.getCurrentValues();
                }catch (NullPointerException ignored){}
                flyMain.setAutoPilot(false);
            }
        });

        displayScript.scriptProperty.bind(radioButtons.scriptProperty);
        displayScript.scriptProperty.addListener((observable, oldValue, newValue) -> {
            flyMain.runSimulator(newValue);
        });
        radioButtons.bindRadio(autopilotProperty,manualProperty);
    }
    public void setClient(Client client) {
        joystick.setClient(client);
    }

    public void enableAll() {
        radioButtons.enableAll();
    }
}