import java.util.ArrayList;
import java.util.Scanner;

public class TEST {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int t=in.nextInt();
		String s;
		while(t-->0){
			s=in.nextLine();
			
		}
	}
}

class SysOut{
	public void printInlineStringSpace(ArrayList<String> arr){
		for(String s:arr){
			System.out.print(s+" ");
		}
	}
	public void printInlineString(ArrayList<String> arr,String t){
		for(String s:arr){
			System.out.print(s+t);
		}
	}
	public void printInlineInt(ArrayList<Integer> arr){
		for(int s:arr){
			System.out.print(s+" ");
		}
	}
	public void printInlineInt(ArrayList<Integer> arr,String t){
		for(int s:arr){
			System.out.print(s+t);
		}
	}
	public static void printlnStringCase(ArrayList<String> arr,int start){
		int count=start;
		for(String s:arr){
			System.out.println("Case#"+count+": "+ s);
		}
	}
	
	public void printlnString(ArrayList<String> arr){
		for(String s:arr){
			System.out.println(s);
		}
	}
	public void printlnInt(ArrayList<Integer> arr){
		for(int s:arr){
			System.out.println(s);
		}
	}
}
