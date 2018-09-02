import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import java.util.ArrayList;
public class ReaderOne {
	
	@SuppressWarnings("resource")
	
	public static ArrayList<Reminder> readURL(String url) throws Exception{
		URL urlToRead=new URL(url);
		ReadableByteChannel in=Channels.newChannel(urlToRead.openStream());
		FileOutputStream out=new FileOutputStream("icalexport.ics");
		out.getChannel().transferFrom(in, 0, Long.MAX_VALUE);
		
	return readFile("icalexport.ics");
	}
	
	public static ArrayList<Reminder> readFile(String fileName) throws Exception {
		File fileToReadFrom=new File(fileName);
		//System.out.println("THE FILE IS AT "+fileToReadFrom.getCanonicalPath());
		Scanner sc=new Scanner(fileToReadFrom);
		ArrayList<Reminder> reminders=new ArrayList<Reminder>();
			
		while(sc.hasNext()){
			String inputLine=sc.nextLine();
			if(inputLine.equals("BEGIN:VEVENT")){
				Reminder currentReminder;
				
				int UID=ReadingFunctions.getUID(sc.nextLine());
				//System.out.println("UID READING"+UID);
				
				String summary=ReadingFunctions.getSummary(sc.nextLine());
				//System.out.println("SUMMARY IS:"+summary);
				
				String description=ReadingFunctions.getDescription(sc.nextLine());
				//System.out.println(description+"!!!!!!!!!!!");
				String descriptionNextLine=sc.next();
				//System.out.println("$$$$"+descriptionNextLine);
				if(!descriptionNextLine.equals("CLASS:PUBLIC")){
					//System.out.println("FFFFFUCK");
					while(!(descriptionNextLine.equals("CLASS:PUBLIC"))){
						description=description+descriptionNextLine;
						descriptionNextLine=sc.nextLine();
						//System.out.println("description:"+description);
					}
				}
				
				String aa=sc.nextLine();
				if(aa.equals("")) aa=sc.nextLine();
				//System.out.println("****"+aa+"000");
				int[] dateModified=ReadingFunctions.getModifiedTime(aa);
				
				//System.out.println("date modified added :P :P");
				sc.nextLine();
				
				String bb=sc.nextLine();
				//System.out.println("END TIME IS"+bb);
				int[] endTime=ReadingFunctions.getEndTime(bb);
				//System.out.println("end time insterted");
				
				String subject=ReadingFunctions.getSubject(sc.nextLine());
				//System.out.println("subject insterted");
				currentReminder=new Reminder(UID,subject,dateModified,endTime,summary,description);
					
				reminders.add(currentReminder);
			}
		}
		return reminders;

	}
}
