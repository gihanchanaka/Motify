package motifymain;

import java.util.ArrayList;
import java.io.FileNotFoundException;
public class TestingReader {
	public static void main(String[] args) throws Exception{
		ArrayList<Reminder> reminders=ReaderOne.readURL("https://feels.pdn.ac.lk/calendar/export_execute.php?userid=2385&authtoken=dce15e9ca0de8df24e32a9da4cb9abdda757298b&preset_what=all&preset_time=monthnow");
		for(int x=0;x<reminders.size();x++){
			Reminder thisReminder=reminders.get(x);
			System.out.println(thisReminder.UID+"-"+thisReminder.subject+"-"+thisReminder.summary);
			System.out.println(thisReminder.description);
			System.out.print(thisReminder.endTime[0]+":"+thisReminder.endTime[1]+":"+thisReminder.endTime[2]+":"+thisReminder.endTime[3]+thisReminder.endTime[4]+":"+thisReminder.endTime[5]);
			System.out.print("<<end time     modified time>>");
			System.out.print(thisReminder.modifiedTime[0]+":"+thisReminder.modifiedTime[1]+":"+thisReminder.modifiedTime[2]+":"+thisReminder.modifiedTime[3]+thisReminder.modifiedTime[4]+":"+thisReminder.modifiedTime[5]);
			System.out.println("");
			System.out.println("");
		}
	}
}
