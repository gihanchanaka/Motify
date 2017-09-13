package motifymain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class fileWrite {

	public static void main(String[] args) throws Exception {
		zeroFiles();

	}
	
	public static void zeroFiles() throws IOException{
		FileOutputStream fos=new FileOutputStream("RSSdata.dat");
	    ObjectOutputStream oos=new ObjectOutputStream(fos);
	    oos.writeInt(0);
	    
	    FileOutputStream fos2=new FileOutputStream("database.dat");
	    ObjectOutputStream oos2=new ObjectOutputStream(fos2);
	    oos2.writeInt(0);
	}

}
