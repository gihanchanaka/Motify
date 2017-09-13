package database.java.sausecode.com;

import java.sql.*;
import java.util.ArrayList;

import fileio.java.sausecode.com.FileObject;

/* 
 * DOCUMENTATION
 * 
 * makeConnection(databaseName);
 * makeTable(tableName,fields,datatypes);
 * execStatment(SqlString); execUpdate(UpdateSqlString);
 * updateData(tableName,condition,fields,datatypes,values);
 * insertInto(tableName,condition,fields,datatypes,values);
 * ArrayList<String[]> res=selectData(begining,fields,datatypes,condition);
 * deleteRecord(tableName,condition);
 * dropTable(tableName);
 * 
 * closeConnection();
 * */

public class Sqlite {
	public static Connection con;
	public static Statement stat;
	public static String DatabaseExtention="db";
	
	public static boolean setDatabaseExtention(String ext){
		DatabaseExtention=ext;
		return true;
	}
	public static boolean isDbExist(String db) throws Exception{;
		return FileObject.isExists(db);
	}
	public static boolean isTableExist(String db,String tb){
		boolean st=false;
		
		return st;
	}
	
	public static int dropTable(String table) throws Exception{
		String str="DROP TABLE IF EXISTS "+table;
		execUpdate(str);
		System.out.println(str+": Drop table");
		return 0;
	}
	
	public static int deleteDb(String fullpath){
		
		return 0;
	}
	
	public static int makeConnection(String dbFile) throws Exception{
		Class.forName("org.sqlite.JDBC");
	    con = DriverManager.getConnection("jdbc:sqlite:"+dbFile+"."+DatabaseExtention);
	    System.out.println(dbFile+": Connection Succeed");
		return 0;
	}
	public static int closeConnection() throws Exception{
		if(!con.isClosed()) con.close(); 
		System.out.println(": Connection Closed");
		return 0;
	}
	public static int makeTable(String table,String[] fields, String[] datatypes) throws Exception{
		String st="CREATE TABLE if not exists "+table+ "(";
		int count=0;
		for(String k:fields) count++; //get structure count
		
		for(int i=0; i<count;i++){
			st=st+fields[i]+" "+datatypes[i];
			if(i!=count-1) st=st+" , ";
		}
		
		st=st+")";
		execStatment(st);
		
		System.out.println(table+ ": Table created");
		return 0;
	}
	
	/**
	NULL. The value is a NULL value.

	1 INTEGER. The value is a signed integer, stored in 1, 2, 3, 4, 6, or 8 bytes depending on the magnitude of the value.

	4 REAL. The value is a floating point value, stored as an 8-byte IEEE floating point number.

	2 TEXT. The value is a text string, stored using the database encoding (UTF-8, UTF-16BE or UTF-16LE).

	3 BLOB. The value is a blob of data, stored exactly as it was input.
	
	5 numeric
	 */
	public static String getDataType(int datatype){
		String dt;
		switch(datatype){
			case 0:
				dt="";
				break;
			case 1:
				dt="INTEGER";
				break;
			case 2:
				dt="TEXT";
				break;
			case 3:
				dt="BLOB";
				break;
			case 4:
				dt="REAL";
				break;
			case 5:
				dt="NUMERIC";
				break;
			default:
				dt="";
				break;
		}
		
		return dt;
	}
	public static int getDataNumber(String dt){
		int dn;
		switch(dt){
			case "":
				dn=0;
				break;
			case "INTEGER":
				dn=1;
				break;
			case "TEXT":
				dn=2;
				break;
			case "BLOB":
				dn=3;
				break;
			case "REAL":
				dn=4;
				break;
			case "NUMERIC":
				dn=5;
				break;
			default:
				dn=0;
				break;
		}
		
		return dn;
	}
	public static int execStatment(String st) throws Exception{
		stat=con.createStatement();
		stat.execute(st);
		stat.close();
		System.out.println(st+": Statement Ran");
		return 0;
	}
	public static int execUpdate(String st) throws Exception{
		stat=con.createStatement();
		stat.executeUpdate(st);
		stat.close();
		System.out.println(st+": Statement Ran");
		return 0;
	}
	
	public static int updateData(String table,String condition,String[] fields,String[] datatypes,String[] values) throws Exception{
		String updates="";
		int count=0;
		for(String f:fields) count++;
		
		for(int k=0; k<count;k++){
			updates=updates+fields[k]+"=";
				if(datatypes[k]=="TEXT"){
					updates=updates+"'"+values[k]+"'";
				}else if(datatypes[k]=="INTEGER"){
					updates=updates+values[k];
				}else{
					updates=updates+"'"+values[k]+"'";
				}
			if(k!=count-1) updates=updates+",";
		}
		//updates=updates+"";
		
		String str="UPDATE "+table +" SET "+ updates+ " "+condition;
		execUpdate(str);
		System.out.println(str+": Update Statement Ran");
		return 0;
	}
	
	
	public static int insertInto(String table,String condition,String[] fields,String[] datatypes,String[] values) throws Exception{
		String fieldnames=" (";
		int count=0;
		for(String f:fields) count++;
		
		for(int k=0; k<count;k++){
			fieldnames=fieldnames+fields[k];
			if(k!=count-1) fieldnames=fieldnames+",";
		}
		fieldnames=fieldnames+")";
		
		String str="INSERT INTO "+table + fieldnames+ " VALUES "+analyzeFieldStructure(datatypes, values)+condition;
		execStatment(str);
		System.out.println(str+": INSERT Statement Ran");
		return 0;
	}
	
	public static String analyzeFieldStructure(String[] datatypes,String[] values){

		int count=0;
		for(String k:datatypes) count++; //get structure count
		
		String out="(";
		for(int k=0; k<count;k++){
			if(k<count-1){
				if(datatypes[k]=="TEXT"){
					out=out+"'"+values[k]+"',";		
				}else{
					out=out+values[k]+",";
				}
			}else{
				if(datatypes[k]=="TEXT"){
					out=out+"'"+values[k]+"')";		
				}else{
					out=out+values[k]+")";
				}
			}
		}
		System.out.println(out);
		return out;
	}
	
	public static ArrayList<String[]> selectData(String condition,String[] fields,String[] datatypes) throws Exception{
		ResultSet rs;
		stat=con.createStatement();
		rs=stat.executeQuery(condition);
		String[] vir=new String[datatypes.length];
		ArrayList<String[]> res=new ArrayList<String[]>();
		while(rs.next()){
			for(int k=0; k<datatypes.length;k++){
				if(datatypes[k]=="TEXT"){
					vir[k]=rs.getString(fields[k]);
				}else if(datatypes[k]=="INTEGER"){
					vir[k]=String.valueOf(rs.getInt(fields[k]));
				}else{
					vir[k]=rs.getString(fields[k]);
				}
			}
			res.add(vir);
			
		}
		rs.close();
		stat.close();
		System.out.println(condition+": SelectQuery Ran");
		return res;
	}
	
	public static int selectSingleDataInt(String condition,String field) throws Exception{
		int res=-1;
		ResultSet rs;
		stat=con.createStatement();
		rs=stat.executeQuery(condition);
		if(rs.next()){
			res=rs.getInt(field);
		}
		rs.close();
		stat.close();
		System.out.println(condition+": SelectSingleDataIntQuery Ran");
		return res;
	}
	
	public static int deleteRecord(String table,String condition) throws Exception{
		String str="DELETE from "+table+" "+condition;
		execUpdate(str);
		System.out.println(str+": Deletion executed");
		return 0;
	}
	
	/**
	public static void main(String[] args) throws Exception {
		
		makeConnection("testdb"); //create connection, create db if not
		
		String[] datatypes={"INTEGER","TEXT"};
		String[] fields={"ID","Name"};
		String[] values={"006023","Cdhandddima"};
		String[] values2={"0022602","sChadndddima"};
		
		String[] valuesUpdate={"006503","ChandimaU3p"};
		
		makeTable("tbl_one", fields, datatypes); //create table if not exists
		
		insertInto("tbl_one", "", fields,datatypes,values); //insert  values into table
		insertInto("tbl_one", "", fields,datatypes,values2); //insert  values into table
		
		//updateData("tbl_one", "where name='Chandima'", fields,datatypes,valuesUpdate); //updates  values in table
		
		//deleteRecord("tbl_one","where name='Chandddima'"); //delete
		
		ArrayList<String[]> myd=selectData("Select * from tbl_one",fields, datatypes); //store selected data into arraylist
		
		for(String[] sa:myd){
			for(String s:sa){
				System.out.print(s+ " ");
			}
			System.out.println();
		}
		dropTable("tbl_one");
		
		closeConnection(); //closing connection
	}
	**/

}
