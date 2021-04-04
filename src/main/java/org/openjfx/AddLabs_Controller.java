package org.openjfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLabs_Controller {
    @FXML
    private JFXTextField labNumber;

    @FXML
    private JFXDatePicker labDate;

    @FXML
    private JFXTimePicker labStartTime;

    @FXML
    private JFXTimePicker labEndTime;

    @FXML
    private JFXTextField labWeight;

    @FXML
    private JFXButton addLab;

    @FXML
    private JFXButton Cancel;


    @FXML
    private Label className;

    @FXML
    void AddLab(MouseEvent event)throws IOException {
        if(labDate.getValue() == null||labNumber.getText() == null||labWeight.getText()==null || labStartTime.getValue()==null || labEndTime.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Some information was not entered");
            alert.showAndWait();
            return;
        }
        String eventName = className.getText();
        String subeventDate= labDate.getValue().toString();
        String subEventName= labNumber.getText();
        String subeventWeight = labWeight.getText();
        String subStartTime = labStartTime.getValue().toString();
        String subEndTime = labEndTime.getValue().toString();



        String qu = "INSERT INTO subEvents(eventName,subeventName,subeventWeight,subeventDate,subStartTime,subEndTime,user_id) VALUES ("
                + "'" + eventName + "',"
                + "'" + subEventName + "',"
                + "'" + subeventWeight + "',"
                + "'" + subeventDate + "',"
                + "'" + subStartTime + "',"
                + "'" + subEndTime + "',"
                + "'" + Login_Controller.uid + "'"
                + ")";


        if(DatabaseHandler.execAction(qu)){ //Success
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        }
        else{ // Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("This Data Already Exists");
            alert.showAndWait();
        }

        clearSelection();
    }

    void setClassName(String text){
        className.setText(text);
    }

    void clearSelection(){
        labNumber.setText("");
        labWeight.setText("");
        labDate.getEditor().clear();
        labDate.setValue(null);
        labStartTime.setValue(null);
        labEndTime.setValue(null);
    }

    @FXML
    void Cancel(MouseEvent e) throws IOException{
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
}




