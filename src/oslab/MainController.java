/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Ass hole
 */
public class MainController implements Initializable {
    
    @FXML
    protected Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("InputWin.fxml"));
       Scene scene = new Scene(root);
        
     Stage stage= new Stage();
       stage.getIcons().add(new Image("/logo.png"));
        stage.setTitle("HXou+");
       stage.setScene(scene);
       stage.setOnCloseRequest(e -> {
        Platform.exit();
        System.exit(0);
    });
      stage.show();
     label.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
