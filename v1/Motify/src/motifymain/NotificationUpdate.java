package motifymain;


import java.io.Serializable;



public class NotificationUpdate implements Comparable<NotificationUpdate>,Serializable{
	int[] pubTime;
	String title;
	String link;
	String description;
	
	
	NotificationUpdate(String pubTimeText,String title,String link,String description){
		this.pubTime=getPubTime(pubTimeText);
		this.title=title;
		this.link=link;
		this.description=description.replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("&nbsp;", "");
	}
	
	public int[] getPubTime(String timeString){
		int[] time=new int[6];
		time[0]=Integer.parseInt(timeString.substring(12,16));
		time[1]=getMonthNumber(timeString.substring(8,11));
		time[2]=Integer.parseInt(timeString.substring(5,7));
		time[3]=Integer.parseInt(timeString.substring(17,19));
		time[4]=Integer.parseInt(timeString.substring(20,22));
		time[5]=Integer.parseInt(timeString.substring(23,25));
		
		return time;
	}
	
	public int getMonthNumber(String monthText){
		int n=0;
		if(monthText.equals("Jan")) n=1;
		if(monthText.equals("Feb")) n=2;
		if(monthText.equals("Mar")) n=3;
		if(monthText.equals("Apr")) n=4;
		if(monthText.equals("May")) n=5;
		if(monthText.equals("Jun")) n=6;
		if(monthText.equals("Jul")) n=7;
		if(monthText.equals("Aug")) n=8;
		if(monthText.equals("Sep")) n=9;
		if(monthText.equals("Oct")) n=10;
		if(monthText.equals("Nov")) n=11;
		if(monthText.equals("Dec")) n=12;
		return n;
	}

	public int compareTo(NotificationUpdate notification) {
		int ans=0;
		if(this.pubTime[0]>notification.pubTime[0]) ans=1;
		else if(this.pubTime[0]<notification.pubTime[0]) ans=-1;
		else{
			if(this.pubTime[1]>notification.pubTime[1]) ans=1;
			else if(this.pubTime[1]<notification.pubTime[1]) ans=-1;
			else{
				if(this.pubTime[2]>notification.pubTime[2]) ans=1;
				else if(this.pubTime[2]<notification.pubTime[2]) ans=-1;
				else{
					if(this.pubTime[3]>notification.pubTime[3]) ans=1;
					else if(this.pubTime[3]<notification.pubTime[3]) ans=-1;
					else{
						if(this.pubTime[4]>notification.pubTime[4]) ans=1;
						else if(this.pubTime[4]<notification.pubTime[4]) ans=-1;
						else{
							if(this.pubTime[4]>notification.pubTime[4]) ans=1;
							else if(this.pubTime[4]<notification.pubTime[4]) ans=-1;
						}
					}
				}
			}
		}
		return ans;
	}


}
