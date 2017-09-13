package fileio.java.sausecode.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class WebFile {
	public static String readURL(String url) throws Exception{ //working fine
		URL web = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(web.openStream()));

        String inputLine;
        String out="";
        while ((inputLine = in.readLine()) != null)
            out=out+inputLine+"\n";
        in.close();
        return out;
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
