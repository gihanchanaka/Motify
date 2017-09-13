package motifymain;

import java.io.*;
public class Reminder implements Comparable<Reminder> , Serializable{
	private static final long serialVersionUID = 1L;
	int UID;
	String subject;
	int[] modifiedTime;
	int[] endTime;
	String summary;
	String description;
	
	Reminder(int UID, String subject, int[] modifiedTime, int[] endTime, String summary, String description){
		this.UID=UID;
		this.subject=subject;
		this.modifiedTime=modifiedTime;
		this.endTime=endTime;
		this.summary=summary;
		this.description=description;
	}

	@Override
	public int compareTo(Reminder rem) {
		int ans=0;
		if(this.endTime[0]>rem.endTime[0]) ans=1;
		else if(this.endTime[0]<rem.endTime[0]) ans=-1;
		else{
			if(this.endTime[1]>rem.endTime[1]) ans=1;
			else if(this.endTime[1]<rem.endTime[1]) ans=-1;
			else{
				if(this.endTime[2]>rem.endTime[2]) ans=1;
				else if(this.endTime[2]<rem.endTime[2]) ans=-1;
				else{
					if(this.endTime[3]>rem.endTime[3]) ans=1;
					else if(this.endTime[3]<rem.endTime[3]) ans=-1;
					else{
						if(this.endTime[4]>rem.endTime[4]) ans=1;
						else if(this.endTime[4]<rem.endTime[4]) ans=-1;
						else{
							if(this.endTime[4]>rem.endTime[4]) ans=1;
							else if(this.endTime[4]<rem.endTime[4]) ans=-1;
						}
					}
				}
			}
		}
		return ans;
	}

}
