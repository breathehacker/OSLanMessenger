/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class InputWinController implements Initializable{
   
  @FXML
    private AnchorPane MainWin;
 @FXML
    private Label label;
    @FXML
    private JFXButton connect;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private JFXTextField ipAddress;
    @FXML
    void makeConnection(ActionEvent event) {
           String ip= ipAddress.getText();  
            if(!ip.isEmpty())
            {  
               new Thread(new Runnable(){ 
                        @Override
                        public void run()
                        {
                                Platform.runLater(new Runnable(){
                                    @Override
                                    public void run() {
                                        connect.setVisible(false);
                                         ipAddress.setVisible(false);
                                            spinner.setVisible(true);
                                             label.setVisible(true); 
                                    }
                                
                                }); 
                               // System.out.println("______Connected");
                                  Controller.client=new MyClient(ip);
                               
                    if(Controller.client.isConnected())
               {
                  // System.out.println("Connected");
                   JOptionPane.showMessageDialog(null,"COnnected! to port","Success!",JOptionPane.PLAIN_MESSAGE);
                 
                  boolean val=true;
                   Platform.runLater(new Runnable(){
                       @Override
                       public void run() {
                           
                          Parent root;
                try {
                     root = FXMLLoader.load(getClass().getResource("Chatt.fxml"));
                        Scene scene = new Scene(root);
                   Stage stage= new Stage();
                     stage.setScene(scene);
                     stage.setOnCloseRequest(e -> {
        Platform.exit();
        System.exit(0);
                });
                       stage.getIcons().add(new Image("/logo.png"));
                    stage.setTitle("HXou+");
                    stage.show();
                    connect.getScene().getWindow().hide();
                         } 
                catch (IOException ex) {
                        }
                 }
    });          
               }
               else{
                    System.out.println("Not Connected");
                    JOptionPane.showMessageDialog(null,"Failed! to Connect","Error!",JOptionPane.PLAIN_MESSAGE);

                     Platform.runLater(new Runnable(){
                       @Override
                       public void run() {
                           connect.setVisible(true);
                                         ipAddress.setVisible(true);
                                            spinner.setVisible(false);
                                             label.setVisible(false); 
                        
                 }
    });        
               }
                        }
                    }).start();
             
            }
          
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      spinner.setVisible(false);
       label.setVisible(false);
      // connect.setDisable(true);
      
       new Thread(new Runnable(){
          @Override
          public void run() {
             // System.out.println("s0kktated");
               Controller.sc= new textSock();
               Thread t= new Thread(Controller.sc);
               t.start();
              try {
                  t.join();
              } catch (InterruptedException ex) {
                  Logger.getLogger(InputWinController.class.getName()).log(Level.SEVERE, null, ex);
              }
                 if(Controller.sc.ServerStarted())
        {
            //System.out.println("Stated");
                    new Thread(new Runnable(){
          @Override
          public void run() {
                  while(!Controller.sc.isAccepted())
                                {
                                    Controller.sc.accept();
                                   // System.out.println("Trying");
                                }
                  if(Controller.sc.isAccepted())
                  {
                    //  System.out.println("Accepted!");
                      JOptionPane.showMessageDialog(null,"COnnected! to port","Success!",JOptionPane.PLAIN_MESSAGE);
                  }
          }
       }).start();
        }
                 else{
                     JOptionPane.showMessageDialog(null,"Unable to start the server","OOops!",JOptionPane.PLAIN_MESSAGE);
                 }
          }
       }).start();

        //connect.setDisable(false);
       
    }    
    
}
