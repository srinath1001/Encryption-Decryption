import java.io.*;
import javax.swing.*;
import java.util.*;

public class Decryption{

	public static void main(String[] args) throws Exception{

		File f;
		FileReader fr;
		FileWriter fw;
		//FileOutputStream fos;
		int n, ranint, result=0;
		Random rand = new Random();
		String fname, fencname="",fencpath="";
		JFileChooser j = new JFileChooser("C:");
		int r = j.showSaveDialog(null);
		if (r == JFileChooser.APPROVE_OPTION){
			f = j.getSelectedFile();
			fname = f.getAbsolutePath();
			int len = fname.length();
			int dot=len-1,start=0;
			for(int i=dot;fname.charAt(i)!='\\';i--){
				start = i;
			}
			for(int i=start;i<dot-8;i++)
				fencname += fname.charAt(i);
			System.out.println("Decrypting....... "+fencname);
			fencname += "DECRYPTED.txt";
			start--;
			for(int i=0;i<=start;i++)
				fencpath+=fname.charAt(i);
			fr = new FileReader(fname);
			System.out.println("Path: "+fencpath);
			fw = new FileWriter(fencpath+fencname);
			fw.flush();
			n = fr.read();
			String re="";
			int res=0, towrite=0;
			while(n!=-1){
				re="";
				while(((char)n)!=','){
					re+=((char) n);
					n = fr.read();
				}
				res = Integer.parseInt(re);
				n = fr.read();
				ranint = Integer.parseInt(((char) n)+"");
				switch(ranint){
					case 1 : towrite = (res + 7)/3;
						break;
					case 2 : towrite = (res-8)/2;
						break;
					case 3 : towrite = (res-6)/3;
						break;
					case 4 : towrite = (res+6)/2;
						break;
					case 5 : towrite = (res-10)/3;
						break;
					default: ;
				}
				fw.write(((char) towrite));
				n = fr.read();
				n = fr.read();
			}
			System.out.println("File Successfully Decrypted. See path: "+fencpath);
			fw.close();
			fr.close();
		}
		else
			System.out.println("No file selected.");

	}

}