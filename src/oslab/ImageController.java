/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;

import com.jfoenix.controls.JFXSpinner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class ImageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView imgView;
    Image image;
       @FXML
    private  JFXSpinner spinner;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        spinner.setVisible(false);
       if(Controller.imageSend)
       {
           System.out.println("true");
       }
       else{
           System.out.println("false");
       }
        image= new Image(Controller.url.toString());
       imgView.setImage(image);
        System.out.println("start");
      
    }   
}
