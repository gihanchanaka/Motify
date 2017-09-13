package motifymain;

import java.util.Calendar;

public class TestingCalendar {
	public static void main(String[] args){
		Calendar calendarNow=Calendar.getInstance();
		int[] timeNow=new int[6];
		timeNow[0]=calendarNow.get(Calendar.YEAR);
		timeNow[1]=calendarNow.get(Calendar.MONTH);
		timeNow[2]=calendarNow.get(Calendar.DAY_OF_MONTH);
		timeNow[3]=calendarNow.get(Calendar.HOUR);
		timeNow[4]=calendarNow.get(Calendar.MINUTE);
		timeNow[5]=calendarNow.get(Calendar.SECOND);
		
		for(int x=0;x<6;x++) System.out.println(timeNow[x]); 
	}
}
