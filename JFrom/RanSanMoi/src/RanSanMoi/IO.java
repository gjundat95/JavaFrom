/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RanSanMoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class IO {
    public void write(String url,String text,int diem){
        try {
            FileOutputStream fileStream = new FileOutputStream(url);
            DataOutputStream dataStream = new DataOutputStream(fileStream);
                dataStream.writeUTF(text);
                dataStream.writeInt(diem);
                dataStream.close();
                fileStream.close();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Diem read(String url){
        Diem diem = new Diem();
        try {
            FileInputStream fileStream = new FileInputStream(url);
            DataInputStream dataStream = new DataInputStream(fileStream);
            diem.name = dataStream.readUTF();
            diem.d = dataStream.readInt();
            dataStream.close();
            fileStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diem;
    }
    
    
}
