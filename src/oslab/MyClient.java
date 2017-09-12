/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oslab;
import java.net.*;  
import java.io.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
class MyClient{  
    private Socket s;
    private DataInputStream din;
    //private DataOutputStream dout;
    private boolean connected;
    public MyClient(String ip)
    {
        try { 
            s=new Socket(ip,Controller.port);
            s.setSoTimeout(5000);
             din=new DataInputStream(s.getInputStream());  
            this.connected=true;
        } catch (IOException ex) {
            this.connected=false;
        }
    }
    public String get()
    {
        String dirPath=null;
        String filename=null;
        dirPath = "pics";
        // System.out.println("Entry!");
        try {
            long length=din.readLong();
             filename=din.readUTF();
            File file = new File( filename);
             FileOutputStream fos = new FileOutputStream(file);
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                System.out.println("dskjhdkjsh ----- ? "+filename);
                int tot = 0;
                for(int j = 0; j < length; j++) {
                    bos.write(din.read());
                   // System.out.println("YUp!");
                }
            }
                  System.out.println("fileWrited    "+file.getAbsolutePath());
                  
        } catch (IOException ex) {
           System.out.println("Error Writing reading!");
        }
        
        return filename;
    }
    public String getMessage() throws IOException
    {
        return din.readUTF();
    }
    public boolean isConnected()
    {
        return connected;
    }
    public void closeConnection() throws IOException
    {
      din.close();  
        s.close();  
    }
    public boolean isClosed()
    {
     if(s.isClosed())
     {
         return true;
     }
     else{
         return false;
     }
    }
}  
