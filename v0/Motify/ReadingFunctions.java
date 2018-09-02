
public class ReadingFunctions {
	public static int getUID(String line){
		return Integer.parseInt(line.substring(line.indexOf(':')+1, line.indexOf('@')));
	}
	
	public static String getSummary(String line){
		return line.substring(line.indexOf(':')+1);
	}
	
	public static String getDescription(String line){
		return getSummary(line);
	}
	
	public static int[] getModifiedTime(String line){
		int n=line.indexOf(':')+1;
		int year=Integer.parseInt(line.substring(n, n+4));
		int month=Integer.parseInt(line.substring(n+4, n+6));
		int day=Integer.parseInt(line.substring(n+6, n+8));
		int hour=Integer.parseInt(line.substring(n+9,n+11));
		int miniute=Integer.parseInt(line.substring(n+11, n+13));
		int second=Integer.parseInt(line.substring(n+13, n+15));
		
		int[] time={year,month,day,hour,miniute,second};
		return time;
	}
	
	public static int[] getEndTime(String line){
		return getModifiedTime(line);
	}
	
	public static String getSubject(String line){
		return getSummary(line);
	}
}
