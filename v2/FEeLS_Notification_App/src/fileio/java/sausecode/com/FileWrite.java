package fileio.java.sausecode.com;

import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
	public static int writePlainText(String path,String text) throws Exception{
		FileWriter fwriter=new FileWriter(path);
		fwriter.write(text);
		fwriter.close();
		return 0;
	}
	
}
