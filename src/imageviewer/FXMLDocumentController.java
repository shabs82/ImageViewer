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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

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

    private ScheduledExecutorService executor;

    private final List<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;
    private List<File> files = new ArrayList<>();
    @FXML
    private JFXButton selectfile;

//    private boolean running;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void playaction(ActionEvent event) {
        start();
    }

    private void start() {
//        if (!running) {
            Runnable task = () -> {
                Platform.runLater(() -> nextbtn.fire());
            };

            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(task, 2, 2, TimeUnit.SECONDS);

//            running = true;
        }
//    }

    @FXML
    private void stopaction(ActionEvent event) {
        executor.shutdown();
//        running = false;
    }

    @FXML
    private void nextpic(ActionEvent event) {
        if (!images.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
            setName(files.get(currentImageIndex).getName());

        }
    }

    @FXML
    private void previousPic(ActionEvent event) {
        if (!images.isEmpty()) {
            currentImageIndex
                    = (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
            setName(files.get(currentImageIndex).getName());

        }
    }

    private void displayImage() {
        if (!images.isEmpty()) {
            imageview.setImage(images.get(currentImageIndex));
        }

    }

    @FXML
    private void handleselectfile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp", "*.jpeg"));
        files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty()) {
            files.forEach((File f)
                    -> {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
            setName(files.get(currentImageIndex).getName());
        }
    }

    public void setName(String imagepic) {
        name.setText(imagepic.substring(imagepic.lastIndexOf("/") + 1, imagepic.length()));
    }

}
