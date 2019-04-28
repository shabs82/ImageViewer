/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.stage.Stage;

/**
 *
 * @author wailampoon
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private ImageView imageview;
    @FXML
    private JFXButton playbtn;
    @FXML
    private JFXButton previousbtn;
    @FXML
    private JFXButton stopbtn;
    @FXML
    private JFXButton nextbtn;

    private final List<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;
    private List<File> files = new ArrayList<>();
    @FXML
    private JFXButton selectfile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playbtn.setOnAction((ActionEvent event)
                -> {
            playaction(event);
        });

        previousbtn.setOnAction((ActionEvent event)
                -> {
            previousPic(event);
        });

        nextbtn.setOnAction((ActionEvent event)
                -> {
            nextpic(event);
        });
    }

    @FXML
    private void playaction(ActionEvent event) {

        
       for(int i =0; i <images.size();i++){
        if (!images.isEmpty()) {
            currentImageIndex
                    = (currentImageIndex - 1 + images.size()) % images.size();
          
        }
         displayImage();
       }
        //  imageview.setImage(images.get(currentImageIndex));
    }

    @FXML
    private void previousPic(ActionEvent event) {

        if (!images.isEmpty()) {
            currentImageIndex
                    = (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
           
        }
    }

    @FXML
    private void stopaction(ActionEvent event) {
    }

    @FXML
    private void nextpic(ActionEvent event) {

        if (!images.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
        }
    }

    private void displayImage() {
        if (!images.isEmpty()) {
            imageview.setImage(images.get(currentImageIndex));
        }
    }

    @FXML
    private void handleselectfile(ActionEvent event) {

        loadFile();

        if (!files.isEmpty()) {
            files.forEach((File f)
                    -> {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
        }
    }

    public void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp", "*.jpeg"));
        files = fileChooser.showOpenMultipleDialog(new Stage());

    }

}
