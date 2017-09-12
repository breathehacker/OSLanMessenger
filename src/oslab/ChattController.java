/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.geometry.Insets;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ass hole
 */
public class ChattController implements Initializable {
    private String str;
 @FXML
    private AnchorPane MainWin;

    @FXML
    private JFXScrollPane flowPane;

    @FXML
    private Label connecttion;

    @FXML
    private JFXScrollPane textPane;

    @FXML
    private JFXTextArea message;

    @FXML
    private JFXButton send;
 @FXML
    private ScrollPane newpane;
   @FXML
    private JFXButton imageSend;
  
  
    @FXML
    void SendImage(ActionEvent event) {
        
  FileChooser fileChooser = new FileChooser();
FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Img files (*.jpg", "*.jpg","*.png","*.gif","*.jpeg");
fileChooser.getExtensionFilters().add(extFilter);
File file = fileChooser.showOpenDialog(new Stage());
    fileChooser.setTitle("Open File");
   // File file = chooser.showOpenDialog(new Stage());
    System.out.println(file.getAbsolutePath()+"\n"+file.getPath());
 try (FileInputStream imageInFile = new FileInputStream(file)) {
                 
		/* Reading a Image file from file system
                String base64Image = "";
		byte imageData[] = new byte[(int) file.length()];
		imageInFile.read(imageData);
		base64Image = Base64.getEncoder().encodeToString(imageData);
                	System.out.println(base64Image);
*/
                        String str;
                        new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Controller.imageSend=false;
                        JFXSpinner spinner=new JFXSpinner();
                                     Platform.runLater(new Runnable(){
                                    @Override
                                    public void run() {
                                        try {
                                            Controller.url = Paths.get(file.getAbsolutePath()).toUri().toURL();
                                        } catch (MalformedURLException ex) {
                                            Logger.getLogger(ChattController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                        AnchorPane an = null;
                                        try {
                                            an = (AnchorPane) FXMLLoader.load(getClass().getResource("image.fxml"));
                                   HBox hBox=new HBox();
                                    
                            hBox.getChildren().add(an);
                            hBox.getChildren().add(spinner);
                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.maxWidth(200);
                        vbox.getChildren().add(hBox);
                                        } catch (IOException ex) {
                                           Logger.getLogger(ChattController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                       
                                    }
                                });
                        try {
                            boolean va=Controller.sc.write(file.getAbsolutePath());
                            if(va)
                            {
                               Platform.runLater(new Runnable(){
                                   @Override
                                   public void run() {
                                       spinner.setVisible(false);
                                 
                                   }
                               
                               });
                               
                            }
                            
                        } catch (IOException ex) {
                           System.out.println("AbsPath");
                        }
                    }
                            
                        }).start();

                      //  Controller.sc.writeMessage(base64Image);
                        
	} 
         catch (FileNotFoundException e) 
         {
		System.out.println("Image not found" + e);
	} 
         catch (IOException ioe) 
         {
		System.out.println("Exception while reading the Image " + ioe);
	}
    }
    private void sending(){
        if(!Controller.sc.isAccepted())
        {
        JOptionPane.showMessageDialog(null,"No User is Connected!\nYou will be notified on connection","Ooops!!",JOptionPane.PLAIN_MESSAGE);

        }
         if(!message.getText().isEmpty() && Controller.sc.isAccepted())
            {
               Controller.sc.writeMessage(message.getText().trim());
               Text l= new Text();
                TextFlow t= new TextFlow ();
               l.setText(message.getText().trim());
                l.setWrappingWidth(200);
                            l.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
                            l.setStyle("-fx-fill:  linear-gradient(#fff , #fff);");
                            t.getChildren().add(l);
                            t.setMaxWidth(200);
                            t.setStyle("-fx-background-color: #3498db;-fx-padding: 7pt;-fx-text-fill:#fff; -fx-background-radius:50pt;-fx-effect: dropshadow(three-pass-box,#bdc3c7, 15, 0.5, 0, 0); ");
                             HBox hBox=new HBox();
                            hBox.getChildren().add(t);
                            hBox.setAlignment(Pos.BASELINE_RIGHT);
                            vbox.getChildren().add(hBox);
                             newpane.setVvalue(1.0);
              // vbox.getChildren().add(l);
               message.setText("");
            }
    }
    @FXML
    void sendText(ActionEvent event) {
           sending();
            newpane.setVvalue(1.0);
    }
  @FXML
public void handleEnterPressed(KeyEvent event){
    if (event.getCode() == KeyCode.ENTER) {
         sending();
          message.setText("");
           newpane.setVvalue(1.0);
    }
}
    @FXML
    private VBox vbox;
    
 private AnchorPane checking;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
          DoubleProperty wProperty = new SimpleDoubleProperty();
    wProperty.bind(vbox.widthProperty()); // bind to Hbox width chnages
    wProperty.addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {
           //when ever Hbox width chnages set ScrollPane Hvalue
         newpane.setVvalue(1.0d); 
        }
    }) ;

             new Thread() {
             
                       //newpane.
                // runnable for that thread
                public void run() {
                    
                    while(true) {
                       
                        try {
                            str=Controller.client.getMessage();
                           // System.out.println(str);
                            //Label l=
                        } catch (IOException ex) {
                           str="";
                           // System.out.println("NOOOoo");
                        }
                        if(!str.isEmpty())
                        {
                           newpane.setVvalue(1.0);
                            if(str.contains("data-img:true"))
                            {
                               Thread t= new Thread(new Runnable(){
                                    @Override
                                    public void run() {
                                        String Pathname=Controller.client.get();
                                        if(!Pathname.isEmpty())
                                        {
                                             Platform.runLater(new Runnable(){
                                    @Override
                                    public void run() {
                                        try {
                                            Controller.url = Paths.get(Pathname).toUri().toURL();
                                        } catch (MalformedURLException ex) {
                                            Logger.getLogger(ChattController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                        AnchorPane an = null;
                             try {
                                   an = (AnchorPane) FXMLLoader.load(getClass().getResource("image.fxml"));
                                   HBox hBox=new HBox();
                                    
                            hBox.getChildren().add(an);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.maxWidth(200);
                        vbox.getChildren().add(hBox);
                                        } catch (IOException ex) {
                                           Logger.getLogger(ChattController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                       
                                    }
                                });
                                            
                                        }
                                        else{
                                            System.out.println("NUll");
                                        }
                                    }
                                    
                                });
                               t.start();
                                try {
                                    t.join();
                                } catch (InterruptedException ex) {
                                   System.out.println("JoinERR");
                                }
                            }
                            else{
                                Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                 newpane.setVvalue(newpane.vmaxProperty().doubleValue());
                            Text l= new Text();
                            TextFlow t= new TextFlow ();
                            
                          //  Text text = new Text("kjshkjsdhkjhsdkjfhsdkjfhsdkj");
                           // text.set
                            l.setText(str);
                            l.setWrappingWidth(200);
                            l.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
                            l.setStyle("-fx-fill:  linear-gradient(#fff , #fff);");
                            t.getChildren().add(l);
                            t.setMaxWidth(200);
                           // l.setBackground();
                            HBox hBox=new HBox();
                            t.setStyle("-fx-background-color: #e74c3c;-fx-padding: 7pt;-fx-text-fill:#fff; -fx-background-radius:50pt; -fx-effect: dropshadow(three-pass-box,#bdc3c7, 15, 0.5, 0, 0);");
                            hBox.getChildren().add(t);
                            hBox.setAlignment(Pos.BASELINE_LEFT);
        //vBox.getChildren().add(hBox);
                            vbox.getChildren().add(hBox);
                             newpane.setVvalue(1.0);
                              newpane.setVvalue(1.0);
                            }
                        });
                            }
                            
                        }
                        // update ProgressIndicator on FX thread
                        
                    }
                }
            }.start();
    }    
    
}
