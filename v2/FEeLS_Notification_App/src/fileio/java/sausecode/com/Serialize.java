package fileio.java.sausecode.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialize {
	public static int saveObject(Object[] ob,String path) throws Exception{
		FileOutputStream fstream=new FileOutputStream(path);
		ObjectOutputStream ostream=new ObjectOutputStream(fstream);
		for(Object co:ob){
			ostream.writeObject(co); //relevent object's classes must implement Serializable
		}
		ostream.close();
		return 0;
	}
	
	public static Object openObject(String path) throws Exception{
		ArrayList<Object> obs=new ArrayList<Object>();
		FileInputStream fistream=new FileInputStream(path);
		ObjectInputStream oistream=new ObjectInputStream(fistream);
		while(oistream.readBoolean()){
			obs.add(oistream.readObject()); //do necessory type castings then
		}
		oistream.close();
		return obs;
	}
}
