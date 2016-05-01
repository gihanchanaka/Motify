import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class RSSFileHandling {
	static public void saveRSSData(ArrayList<NotificationUpdate> arrayToSave) throws Exception{
		FileOutputStream fos=new FileOutputStream("RSSdata.dat");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeInt(arrayToSave.size());
		for(int x=0;x<arrayToSave.size();x++) oos.writeObject(arrayToSave.get(x));
	}
	
	static public ArrayList<NotificationUpdate> readRSSData() throws Exception{
		FileInputStream fis=new FileInputStream("RSSdata.dat");
		ObjectInputStream ois=new ObjectInputStream(fis);
		int N=ois.readInt();
		ArrayList<NotificationUpdate> arrayBeingRead=new ArrayList<NotificationUpdate>();
		for(int x=0;x<N;x++) arrayBeingRead.add((NotificationUpdate) ois.readObject());
		Collections.sort(arrayBeingRead);
		return arrayBeingRead;
	}
}
