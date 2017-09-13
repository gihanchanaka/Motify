package database.java.sausecode.com;

import fileio.java.sausecode.com.FileObject;

public class MyDatabase {
	private String dbName="feelsdb";
	private int latestUID;
	public Sqlite sqli;
	public MyDatabase() throws Exception{
		/**
		if(FileObject.isExists(dbName)){
			try {Sqlite.makeConnection(dbName);}catch (Exception e) {System.err.println("Connection Error: "); e.printStackTrace();}
			latestUID=Sqlite.selectSingleDataInt("SELECT INTVALUE FROM tblSettings WHERE SETTING='LASTUID'","INTVALUE");
			System.out.println(latestUID);
		}else{
			try {Sqlite.makeConnection(dbName);}catch (Exception e) {System.err.println("Connection Error: "); e.printStackTrace();}
			System.out.println("DATABASE ISN'T EXISTS");
		}
		 **/
			sqli=new Sqlite();
			try {sqli.makeConnection(dbName);}catch (Exception e) {System.err.println("Connection Error: "); e.printStackTrace();}
			//latestUID=Sqlite.selectSingleDataInt("SELECT INTVALUE FROM tblSettings WHERE SETTING='LASTUID'","INTVALUE");
			//System.out.println(latestUID);
	
	}
	public int updateUID(int uid) throws Exception{
		String[] tblSettingsFields={"INTVALUE"};
		String[] tblSettingsDatatypes={"INTEGER"};
		String[] tblData={String.valueOf(uid)};
		sqli.updateData("tblSettings", "WHERE SETTING='LASTUID'", tblSettingsFields, tblSettingsDatatypes, tblData);
		return 0;
	}
	public int resetDatabse() throws Exception{
		sqli.dropTable("*");
		//making tables
		//** tblSettings **//
		String[] tblSettingsFields={"SETTING","TEXTVALUE","INTVALUE","DESCRIPTION"};
		String[] tblSettingsDatatypes={"TEXT","TEXT","INTEGER","TEXT"};
		
		sqli.makeTable("tblSettings", tblSettingsFields, tblSettingsDatatypes);
		
		String[] values={"LASTUID","","1500","Latest UID"};
		sqli.insertInto("tblSettings", "", tblSettingsFields, tblSettingsDatatypes, values);
		
		//** tblNotifications **//
				String[] tblNotificationsFields={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES"};
				String[] tblNotificationsDatatypes={"INTEGER","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};
				Sqlite.makeTable("tblNotifications", tblNotificationsFields, tblNotificationsDatatypes);
		
		//** tblNotes **//
				String[] tblNotesFields={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES","DEADLINE","DONE"};	
				String[] tblNotesDatatypes={"INTEGER","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","INTEGER","TEXT"};
				Sqlite.makeTable("tblNotes", tblNotesFields, tblNotesDatatypes);
		return 0;
	}
	
	public int createTables() throws Exception{
		//Sqlite.dropTable("*");
		//making tables
		//** tblSettings **//
		String[] tblSettingsFields={"SETTING","TEXTVALUE","INTVALUE","DESCRIPTION"};
		String[] tblSettingsDatatypes={"TEXT","TEXT","INTEGER","TEXT"};
		sqli.makeTable("tblSettings", tblSettingsFields, tblSettingsDatatypes);
		
		String[] values={"LASTUID","","1500","Latest UID"};
		sqli.insertInto("tblSettings", "", tblSettingsFields, tblSettingsDatatypes, values);
		
		//** tblNotifications **//
				String[] tblNotificationsFields={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES"};
				String[] tblNotificationsDatatypes={"INTEGER","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};
				sqli.makeTable("tblNotifications", tblNotificationsFields, tblNotificationsDatatypes);
		
		//** tblNotes **//
				String[] tblNotesFields={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES","DEADLINE","DONE"};	
				String[] tblNotesDatatypes={"INTEGER","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};
				sqli.makeTable("tblNotes", tblNotesFields, tblNotesDatatypes);
		return 0;
	}
	
	public boolean addNewNote(String[] values) throws Exception{
		String[] tblNotesFields={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES","DEADLINE","DONE"};	
		String[] tblNotesDatatypes={"INTEGER","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};
		
		Sqlite.insertInto("tblNotes", "", tblNotesFields,tblNotesDatatypes, values);
		return true;
	}
	
}
