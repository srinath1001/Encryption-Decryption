import java.io.*;
import javax.swing.*;
import java.util.Random;

public class Encryption{

   public static void main(String[] args) throws Exception{
   
      System.setProperty("file.encoding", "UTF-8"); // Setting the default encoding of the files to UTF-8
      
   //----------------------Variable Declarations-----------------------------------------------------------------
      File f;
      FileInputStream fis;
      String path, ext = "", name = "", restpath = "", b = "", b1 = "";
      int len = 0, i1 = 0 , k=0, n=0,  i=0, z=0, number=0, ranint=0, result = 0;
      char[] arr = {};
      Random r = new Random();
      JFileChooser chooser = new JFileChooser();
      int ret = chooser.showOpenDialog(null);
   //-------------------End of Variable Declarations--------------------------------------------------------------
      
      if( ret == JFileChooser.APPROVE_OPTION ){
      
         
         f = chooser.getSelectedFile(); // Selecting the file
         fis = new FileInputStream(f);
            
         //------Getting the entire path, and taking name, and extension from path------
            path = f.getAbsolutePath();
            len = path.length();
            arr = path.toCharArray();
            
            for(i1 = len - 1 ; arr[i1] != '.' ; i1-- ) ext = ext + arr[i1];
            ext = new StringBuilder(ext).reverse().toString();
            i1--;
            
            for( k = i1 ; arr[k] != '/' && arr[k] != '\\' ; k--) name = name + arr[k];
            name = new StringBuilder(name).reverse().toString();
            i1--;
            
            for(k=i1 ; k>=0 ; k--) restpath = restpath + arr[k];
            restpath = new StringBuilder(restpath).reverse().toString(); 
         //--------End of previous function----------------------------------------------
            
         FileOutputStream fos = new FileOutputStream(new File("D:/hello/aencrypted.txt"));
         
         //---------Processing through the file------------------------------------------
         
         n = fis.read();
                    
         while(n!=-1){
             /*  
                 This is the explanation I referred to for UTF - 8 encoding and 
                 it shows how the bytes will be represented in this encoding
                 
                 https://www.youtube.com/watch?v=MijmeoH9LT4
             */
            i=0;
            b = Integer.toBinaryString(n);
            if(b.length()<8) number = Integer.parseInt(b, 2);
            else
            {
               while(b.charAt(i) == '1') i++;
               b1 = b.substring(i);
               for(z=1;z<i;z++){
                  n = fis.read();
                  b = Integer.toBinaryString(n);
                  b1 = b1 + b.substring(2);
               }
               
               number = Integer.parseInt(b1, 2);
               
            }
            ranint = 1 + r.nextInt(5);
            
            switch(ranint){
               case 1 : result = number*3 - 7;
                  break;
               case 2 : result = 8 + number*2;
                  break;
               case 3 : result = 6 + number*3;
                  break;
               case 4 : result = number*2 - 6;
                  break;
               case 5 : result = 10 + number*3;
                  break;
               default: ;
            }
            
            fos.write(( result + ","+ranint+",").getBytes("UTF-8")); // Writing into the new file
            n = fis.read(); 
            
         }
         //------------Encryption complete-------------------
         fis.close();
         fos.close(); 
        
         
      }
      
   }

}