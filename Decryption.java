import java.io.*;
import javax.swing.*;

public class Decryption{

   public static void main(String[] args) throws Exception{
      
   //----------------------Variable Declarations-----------------------------------------------------------------
      File f, f2;
      FileInputStream fis;
      FileOutputStream fos;
      String path, ext = "", name = "", restpath = "", s = "";
      int len = 0, i1 = 0 , k=0, n=0,  i=0, result = 0, x = 0,y=0;
      JFileChooser chooser = new JFileChooser();
      int ret = chooser.showOpenDialog(null);
      char a =' ';
      char[] arr;
   //-------------------End of Variable Declarations--------------------------------------------------------------
      try{
         if( ret == JFileChooser.APPROVE_OPTION ){
         
            f = chooser.getSelectedFile(); // Selecting the file
            fis = new FileInputStream(f);
         
         //------Getting the entire path, and taking name, and extension from path------
            path = f.getAbsolutePath();
            len = path.length();
            arr = path.toCharArray();
            
            for(i1 = len - 1 ; arr[i1] != '.' ; i1-- ) ext = ext + arr[i1];
            ext = new StringBuilder(ext).reverse().toString();
            i1 = i1 - 10;
            
            for( k = i1 ; arr[k] != '/' && arr[k] != '\\' ; k--) name = name + arr[k];
            name = new StringBuilder(name).reverse().toString();
            i1--;
            
            for(k=i1; k>=0 ; k--) restpath = restpath + arr[k];
            restpath = new StringBuilder(restpath).reverse().toString();
            
         //--------End of previous function----------------------------------------------
         
            f2 = new File(restpath + name + "decrypted."+ext);
            fos = new FileOutputStream(f2);
         
         //-----------Processing through the encrypted file------------------------------
            n = fis.read();
         
            while(n != -1){
               s = "";
            
               while( (char) n != ','){
                  s = s + (char) n;
                  n = fis.read();
               }
            
               x = Integer.parseInt(s);
               n = fis.read();
               a = (char) n;
               y = Integer.parseInt(a+"");
               n = fis.read();
               
               switch(y){
                  case 1 : result = (x + 7)/3;
                     break;
                  case 2 : result = (x - 8)/2;
                     break;
                  case 3 : result = (x - 6)/3;
                     break;
                  case 4 : result = (x + 6)/2;
                     break;
                  case 5 : result = (x - 10)/3;
                     break;
                  default: ;
               }
            
               fos.write(((char) result +"").getBytes("UTF-8")); // Entering the decrypted characters into a new file
               n = fis.read();
            
            }
         //-----------Decryption complete------------------------------
         }
      
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR!\n" + e.getMessage());
         System.exit(0);
      }
   
      
   
   }

}