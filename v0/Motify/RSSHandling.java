import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
public class RSSHandling {
	
	
	public static NodeList getNodeList(File fileName,String tagName) throws Exception{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
		Document doc=dBuilder.parse(fileName);
		doc.getDocumentElement().normalize();
		NodeList nodeList=doc.getElementsByTagName(tagName);
		return nodeList;
	}
	
	
	public static ArrayList<NotificationUpdate> getNotificationArrayList(NodeList nodeList){
		ArrayList<NotificationUpdate> listOfNotifications=new ArrayList<NotificationUpdate>();
		for(int x=0;x<nodeList.getLength();x++){
			Node thisNode=nodeList.item(x);
			if(thisNode.getNodeType()==javax.xml.soap.Node.ELEMENT_NODE){
				Element thisElement=(Element) thisNode;
				NotificationUpdate thisNotification=new NotificationUpdate(thisElement.getElementsByTagName("pubDate").item(0).getTextContent(),thisElement.getElementsByTagName("title").item(0).getTextContent(),thisElement.getElementsByTagName("link").item(0).getTextContent(),thisElement.getElementsByTagName("description").item(0).getTextContent());
				listOfNotifications.add(thisNotification);
			}
		}
		return listOfNotifications;
	}
	
	public static ArrayList<NotificationUpdate> mergeTwoArrayLists(ArrayList<NotificationUpdate> arrayList1,ArrayList<NotificationUpdate> arrayList2){
		ArrayList<NotificationUpdate> newArrayList=new ArrayList<NotificationUpdate>();
		newArrayList.addAll(arrayList1);
		newArrayList.addAll(arrayList2);
		Collections.sort(newArrayList);
		return newArrayList;
	}
	
	public static void downloadRSS(String[] urlArray){
		for(int x=0;x<urlArray.length;x++){
			try{
			URL urlToDownload=new URL(urlArray[x]);
			ReadableByteChannel in=Channels.newChannel(urlToDownload.openStream());
			FileOutputStream out=new FileOutputStream("rssFile"+x+".xml");
			out.getChannel().transferFrom(in, 0, Long.MAX_VALUE);
			}
			catch(Exception e){
				//
			};
		}
	}
	
	public static ArrayList<NotificationUpdate> getArrayForAllRSSLinks(String[] rssLinks) throws Exception{
		ArrayList<NotificationUpdate> answer=new ArrayList<NotificationUpdate>();
		downloadRSS(rssLinks);
		for(int x=0;x<rssLinks.length;x++){
			File file=new File("rssFile"+x+".xml");
			ArrayList<NotificationUpdate> thisRSSUpdateSet=getNotificationArrayList(getNodeList(file,"item"));
			answer=mergeTwoArrayLists(answer,thisRSSUpdateSet);
		}
		return answer;
	}
	
	public static ArrayList<NotificationUpdate> getArrayForAllRSSLinks(ArrayList<String> rsslinksArrayList) throws Exception{
		String[] rssLinks=new String[rsslinksArrayList.size()];
		for(int x=0;x<rsslinksArrayList.size();x++){
			rssLinks[x]=rsslinksArrayList.get(x);
		}
		return getArrayForAllRSSLinks(rssLinks);
	}
	
	
	
}
