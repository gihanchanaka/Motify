import java.io.*;
import java.util.ArrayList;
public class ArrayFileHandling {
	public static void saveArrayCustomName(ArrayList<Reminder> arrayToSave,String fileName) throws Exception{
		FileOutputStream fos=new FileOutputStream(fileName);
	    ObjectOutputStream oos=new ObjectOutputStream(fos);
	    oos.writeInt(arrayToSave.size());
	    for(int x=0;x<arrayToSave.size();x++) oos.writeObject(arrayToSave.get(x));
	}
	
	public static ArrayList<Reminder> readArrayCustomName(String fileName) throws Exception{
		FileInputStream fis=new FileInputStream(fileName);
		ObjectInputStream ois=new ObjectInputStream(fis);
		int noOfReminders=ois.readInt();
		ArrayList<Reminder> arrayRead=new ArrayList<Reminder>();
		for(int x=0;x<noOfReminders;x++) arrayRead.add((Reminder)ois.readObject());
		arrayRead=ArrayHandling.sortReminders(arrayRead);
		return arrayRead;
	}
	
	public static void saveArray(ArrayList<Reminder> arrayToSave) throws Exception{
		saveArrayCustomName(arrayToSave,"database.dat");
	}
	public static ArrayList<Reminder> readArray() throws Exception{
		return readArrayCustomName("database.dat");
	}
}
