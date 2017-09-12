/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class textSock implements Runnable{
     private ServerSocket server;
     private boolean serverStarted;
       private Socket socket ;
       private DataOutputStream dout;
     public boolean ServerStarted(){
         return serverStarted;
     }
     private boolean accepted;
     public void accept()
     {
        
         try {
             socket=server.accept(); 
             dout=new DataOutputStream(socket.getOutputStream());  
             accepted=true;
         } catch (IOException ex) {
            accepted=false;
            //accept();
         }
     }
     public boolean isAccepted()
     {
         return accepted;
     }
     public boolean stopServer() throws IOException
     {
            server.close();
            return server.isClosed();
     }
     public void writeMessage(String str) 
     {
         try {  
             dout.writeUTF(str);
         } catch (IOException ex) {
             Logger.getLogger(textSock.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {   
             dout.flush();
         } catch (IOException ex) {
             Logger.getLogger(textSock.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     public Boolean write(String filePath) throws IOException
     {
          File myFile = new File(filePath);
       writeMessage("data-img:true;");
       long length= myFile.length();
        long totalBytesRead = 0;
       String fileName=myFile.getName();
       String str="filename:"+fileName+";filelength"+length+";";
       dout.writeLong(length);
        dout.writeUTF(fileName);
        FileInputStream fis;
         try {
             fis = new FileInputStream(myFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                                     int theByte = 0;
                                     while((theByte = bis.read()) != -1)
                                     {
                                        totalBytesRead += theByte;
                                        System.out.println(totalBytesRead);
                                        dout.write(theByte);
                                     }
                                      bis.close();
                                      return true;
         } catch (FileNotFoundException ex) {
             System.out.println("Sorry file Not found");
             return false;
         }
                                  
                                   
     }
    @Override
    public void run() {
         try {
             
             server = new ServerSocket(Controller.port);
              //socket=server.accept(); 
              
             accepted=false;
             serverStarted=true;
         } catch (IOException ex) {
             //System.out.println("LOS");
              serverStarted=false;
         }
         
    }
}
