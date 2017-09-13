package fileio.java.sausecode.com;

import java.io.File;
import java.util.ArrayList;

public class FileObject {
	private File f;
	
	public FileObject(String path){
		f=new File(path);
	}
	public File openFile(){
		return f;
	}
	public boolean deleteFile(){
		boolean st=false;
		try{
			f.delete();
			st=true;
		}catch(Exception e){st=false;}
		return st;
	}
	
	
	public static void makeFile(String path) throws Exception{
		FileWrite.writePlainText(path,"");
	}
	public static File openFile(String path) throws Exception{ //make file object representing existing file
		File file=new File(path);
		return file;
	}
	public static boolean makeDir(String path) throws Exception{
		File dir=new File(path);
		return dir.mkdir();
	}
	public static File openDir(String path) throws Exception{
		File dir=new File(path);
		return dir;
	}
	public static String[] listDir(String path) throws Exception{
		boolean st=false;
		if(openDir(path).isDirectory()){
			st=true;
		}
		return openDir(path).list();
	}
	public static boolean isExists(String path) throws Exception{
		boolean st=false;
		if(openFile(path).exists() && !openFile(path).isDirectory()) { 
		    st=true;
		}
		return st;
	}
	public static boolean delete(String path){
		boolean st=false;
		try{
			File dir=new File(path);
			dir.delete();
			st=true;
		}catch(Exception e){
			st=false;
		}
		return st;
	}
}
