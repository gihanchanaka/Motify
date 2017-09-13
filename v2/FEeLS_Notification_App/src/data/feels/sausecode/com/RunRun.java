package data.feels.sausecode.com;

import fileio.java.sausecode.com.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RunRun {

	public static void main(String[] args) throws Exception {
		//readWeb();
		//testGet();
		String url="https://feels.pdn.ac.lk/calendar/export_execute.php?userid=3109&authtoken=e0ce9b773f93c857cd14b1687230d7fe6b45a25a&preset_what=all&preset_time=recentupcoming";
		//System.out.println(WebFile.readURL(url));
		testGet(url);
	}
	
	public static void testGet(String url) throws Exception{
		//File openFile=new File("D:\\area51\\Developer\\earlier-developments\\adt-bundle\\java-works\\FEeLS_Notification\\src\\data\\feels\\sausecode\\com\\icalexport.ics");
		//File openFile=new File(url);
		
		/**	Scanner in=new Scanner(WebFile.readURL(url));
		String inp="";
		while(in.hasNextLine()){
			inp=inp+"\n"+in.nextLine();
		}
		//System.out.println(inp);
		
		**/
		getDataCalender gd=new getDataCalender(WebFile.readURL(url));
		
		showData(gd.getData());
		
		
		
		
	}
	
	public static void readWeb() throws Exception{
		
		String page=WebFile.readURL("https://feels.pdn.ac.lk/calendar/export_execute.php?preset_what=all&preset_time=weeknow&cal_d=&cal_m=&cal_y=&userid=3109&authtoken=e0ce9b773f93c857cd14b1687230d7fe6b45a25a");
		getDataCalender gd=new getDataCalender(page);
		
		showData(gd.getData());
		
	}
	
	public static void showData(ArrayList<String[]> results){
			
		if(results.size()>0){
			for(int i=0; i<results.size(); i++){
				for(int j=0; j<7; j++){
					System.out.println(results.get(i)[j]);
				}
				System.out.println();
			}
		}else{
			System.out.println("No data");
		}
				
				
	}

}
