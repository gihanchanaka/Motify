package data.feels.sausecode.com;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class getDataCalender{
	public String[] mydata=new String[7];
	public ArrayList<String[]> data;
	boolean uid=false;
	public String inp;

	
	public getDataCalender(String input){
		data=new ArrayList<String[]>();
		this.inp=input;
	}
	
	
	public ArrayList<String[]> getData(){
		Scanner in=new Scanner(inp);
		String Line="";
        boolean desc=false;
        //boolean uid=false;
        while(in.hasNextLine()){
        	Line=in.nextLine();
        	if(Line.contains("UID:")){
        		//for(int i=0;i<7;i++) mydata[i]="";
        		uid=true;
        		mydata[0]=Line.substring(Line.indexOf(":")+1, Line.indexOf("@"));
        	}else if(Line.contains("SUMMARY:")){
        		mydata[1]=Line.substring(Line.indexOf(":")+1);
        	}else if(Line.contains("DESCRIPTION:")){
        		mydata[2]=Line.substring(Line.indexOf(":")+1);
        		desc=true;
        	}else if(Line.contains("LAST-MODIFIED:")||Line.contains("CLASS:")){
        		mydata[3]=Line.substring(Line.indexOf(":")+1);
        		desc=false;
        	}else if(Line.contains("DTSTAMP:")){
        		mydata[4]=Line.substring(Line.indexOf(":")+1);
        	}else if(Line.contains("DTSTART:")){
        		mydata[5]=Line.substring(Line.indexOf(":")+1);
        	}else if(Line.contains("CATEGORIES:")){
        		mydata[6]=Line.substring(Line.indexOf(":")+1);
        		
        		if(uid){
        			String[] arr={mydata[0],mydata[1],mydata[2],mydata[3],mydata[4],mydata[5],mydata[6]};
            		data.add(arr);
        		}
        		uid=false;

        	}else if(desc){
        		mydata[2]=mydata[2]+"\n"+Line;
        	}
        }
		return data;
	}

	
	
	public int getDataSize(){
		return data.size();
	}
	public boolean isEmpty(){
		return (data.size()==0);
	}
	public String getValue(int index,int value){
		return (data.get(index)[value]);
	}
	
	public int getUID(int index){
		return Integer.valueOf(getValue(index,0));
	}
	public String getCategory(int index){
		return (getValue(index,6));
	}
	public String getSummery(int index){
		return (getValue(index,1));
	}
	public String getDescription(int index){
		return (getValue(index,2));
	}
	
	public String getLMD(int index){
		return (getValue(index,3).substring(0,8));
	}
	public String getLMT(int index){
		return (getValue(index,3).substring(9,15));
	}

	public String getMPD(int index){
		return (getValue(index,4).substring(0,8));
	}
	public String getMPT(int index){
		return (getValue(index,4).substring(9,15));
	}
	public String getRTD(int index){
		return (getValue(index,5).substring(0,8));
	}
	public String getRTT(int index){
		return (getValue(index,5).substring(9,15));
	}
//	public Date getLastModifiedDate(int index){
//		return getDate(index,3);
//	}
//	public Time2 getLastModifiedTime(int index){
//		return getTime(index,3);
//	}
//	public Date getMPDate(int index){
//		return getDate(index,4);
//	}
//	public Time2 getMPTime(int index){
//		return getTime(index,4);
//	}
//	public Date getRTDate(int index){
//		return getDate(index,5);
//	}
//	public Time2 getRTTime(int index){
//		return getTime(index,5);
//	}
	
}

class Time2{
	int hh,mm,ss;
	public Time2(int h,int m,int s){
		this.hh=h;
		this.mm=m;
		this.ss=s;
	}
}

