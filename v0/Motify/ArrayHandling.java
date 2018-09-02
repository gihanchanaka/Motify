import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ArrayHandling {
	public static ArrayList<Reminder> remainingReminders(ArrayList<Reminder> inputArray){
		Calendar calendarNow=Calendar.getInstance();
		int[] timeNow=new int[6];
		timeNow[0]=calendarNow.get(Calendar.YEAR);
		timeNow[1]=calendarNow.get(Calendar.MONTH)+1;
		timeNow[2]=calendarNow.get(Calendar.DAY_OF_MONTH);
		timeNow[3]=calendarNow.get(Calendar.HOUR);
		timeNow[4]=calendarNow.get(Calendar.MINUTE);
		timeNow[5]=calendarNow.get(Calendar.SECOND);
		
		boolean[] toRemove=new boolean[inputArray.size()];
		
		for(int x=0;x<inputArray.size();x++){
			int[] endTime=inputArray.get(x).endTime;
			//for(int y=0;y<6;y++) System.out.println("y="+y+"  "+timeNow[y]+" "+endTime[y]);
			if(endTime[0]<timeNow[0]) toRemove[x]=true;
			else if(endTime[0]==timeNow[0]){
				if(endTime[1]<timeNow[1]) toRemove[x]=true;
				else if(endTime[1]==timeNow[1]){
					if(endTime[2]<timeNow[2]) toRemove[x]=true;
					else if(endTime[2]==timeNow[2]){
						if(endTime[3]<timeNow[3]) toRemove[x]=true;
						else if(endTime[3]==timeNow[3]){
							if(endTime[4]<timeNow[4]) toRemove[x]=true;
							else if(endTime[4]==timeNow[4]){
								if(endTime[5]<timeNow[5]) toRemove[x]=true;
							}
						}
					}
				}
			}
		}
		//for(int x=inputArray.size()-1;x>-1;x--) System.out.println(toRemove[x]);
		for(int x=inputArray.size()-1;x>-1;x--) if(toRemove[x]) inputArray.remove(x);
		return inputArray;
	}
	
	public static ArrayList<Reminder> sortReminders(ArrayList<Reminder> inputArray){
		Collections.sort(inputArray);
		return inputArray;
		
	}
	

}
