import java.io.File;
import java.util.*;
public class TestingRSS {
	public static void main(String[] args) throws Exception{
		String[] rssLinks=new String[3];
		rssLinks[0]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14885%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F299%2Frss.xml";
		rssLinks[1]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml";
		rssLinks[2]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml";
		ArrayList<NotificationUpdate> ans=RSSHandling.getArrayForAllRSSLinks(rssLinks);
		
		RSSFileHandling.saveRSSData(ans);
		ArrayList<NotificationUpdate> ansBeingRead=RSSFileHandling.readRSSData();
		
		for(int x=0;x<ansBeingRead.size();x++){
			System.out.println(ansBeingRead.get(x).pubTime[0]+" / "+ansBeingRead.get(x).pubTime[1]+" / "+ansBeingRead.get(x).pubTime[2]+" / "+ansBeingRead.get(x).pubTime[3]+" / "+ansBeingRead.get(x).pubTime[4]+" / "+ansBeingRead.get(x).pubTime[5]);
			System.out.println(ansBeingRead.get(x).title);
			System.out.println(ansBeingRead.get(x).description);
			System.out.println(ansBeingRead.get(x).link);
		}
	}
}
