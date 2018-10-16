import java.util.*;
import javax.swing.*;
import java.io.*;

public class Encryption{

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
			int dot=0,start=0;
			for(int i=0;i<len;i++){
				while(fname.charAt(i)!='.')
					i++;
				dot = i;
				while(fname.charAt(i)!='\\')
					i--;
				start = ++i;
				i = len;
			}
			for(int i=start;i<dot;i++)
				fencname += fname.charAt(i);
			System.out.println("Encrypting....... "+fencname);
			fencname += "ENCRYPTED";
			start--;
			for(int i=0;i<=start;i++)
				fencpath+=fname.charAt(i);
			fr = new FileReader(fname);
			System.out.println("Path: "+fencpath);
			fw = new FileWriter(fencpath+fencname);
			fw.flush();
			n = fr.read();
			while(n!=-1){
				ranint = 1 + rand.nextInt(5);
				switch(ranint){
					case 1 : result = n*3 - 7;
						break;
					case 2 : result = 8 + n*2;
						break;
					case 3 : result = 6 + n*3;
						break;
					case 4 : result = n*2 - 6;
						break;
					case 5 : result = 10 + n*3;
						break;
					default: ;
				}
				n = fr.read();
				fw.write(result+","+ranint+"|");
			}
			System.out.println("File Successfully Encrypted. See path: "+fencpath);
			fw.close();
			fr.close();
		}
		else
			System.out.println("No file selected.");

	}

}