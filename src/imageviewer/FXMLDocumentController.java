/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author wailampoon
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label name;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXButton play;
    @FXML
    private JFXButton prepic;
    @FXML
    private JFXButton stop;
    @FXML
    private JFXButton next;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playaction(ActionEvent event) {
    }

    @FXML
    private void previousPic(ActionEvent event) {
    }

    @FXML
    private void stopaction(ActionEvent event) {
    }

    @FXML
    private void nextpic(ActionEvent event) {
    }
    
}
