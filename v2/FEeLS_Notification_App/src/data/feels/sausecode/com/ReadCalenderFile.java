package data.feels.sausecode.com;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

public class ReadCalenderFile {
	public String read(String url) throws Exception{
		URL urlToRead=new URL(url);
		ReadableByteChannel in=Channels.newChannel(urlToRead.openStream());
		Scanner inp=new Scanner(in);
		String webpage="";
		while(inp.hasNextLine()){
			//System.out.println(inp.nextLine());
			webpage=webpage+"/n"+webpage;
		}
		return webpage;
	}
	
	public String readScaner(String url) throws Exception{
		URL urlToRead=new URL(url);
		Scanner in=new Scanner(urlToRead.openStream());
		String webpage="";
		while(in.hasNextLine()){
			//System.out.println(inp.nextLine());
			webpage=webpage+"/n"+webpage;
		}
		return webpage;
	}
	
	public String readWeb(String url){
		String web="";
		
		return web;
	}
}
