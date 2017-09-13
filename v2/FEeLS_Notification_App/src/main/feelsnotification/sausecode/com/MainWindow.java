package main.feelsnotification.sausecode.com;

import fileio.java.sausecode.com.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import data.feels.sausecode.com.getDataCalender;
import gui.java.sausecode.com.ButtonCustom;
import gui.java.sausecode.com.ListCustom;
import gui.java.sausecode.com.ListCustomToDo;
import gui.java.sausecode.com.ListDataStructure;

import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import database.java.sausecode.*;
import database.java.sausecode.com.MyDatabase;
import database.java.sausecode.com.Sqlite;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class MainWindow {
//GUI VARIABLES
	private JFrame frameMain;
	private ListCustom listNotifications;
	private ListCustomToDo listToDo;
	private JPanel panelToDo;
	
	private Point mouseCord=null; //to track clicked mouse position
	//ScreenInfor
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int scrWidth =(int) screenSize.getWidth();
	private int scrHeight =(int) screenSize.getHeight();

	private int frameWidth=600*1;
	private int frameHeight=800*1;
	
	private int panelW=600;
	private int panelH=600;
	///
	
//APPLICATION DATA VAIRABLES
	private int interval=5000;
	public String weburl="https://feels.pdn.ac.lk/calendar/export_execute.php?userid=3109&authtoken=e0ce9b773f93c857cd14b1687230d7fe6b45a25a&preset_what=all&preset_time=recentupcoming";
	
	
	public MyDatabase running;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainWindow window;
				try {
					window= new MainWindow();
					window.frameMain.setVisible(true);
					
					//Notification Listening Thread Start
					Thread threadNotifi=new Thread(new Runnable(){
						@Override
						public void run() {
							while(true){
								try {
									String msg=WebFile.readURL(window.weburl); //notification .ics
									getDataCalender gd=new getDataCalender(msg);
									ArrayList<String[]> results=gd.getData();
									WebFile.showData(results);
									
									int uid = -1;
									//list updating:
									//data to update
									ArrayList<ListDataStructure> cld=new ArrayList<ListDataStructure>();
									
									if(results.size()>0){
										uid=Integer.valueOf(results.get(results.size()-1)[0]);
										for(int i=0; i<results.size(); i++){
												cld.add(new ListDataStructure(results.get(i)[6]+" : "+results.get(i)[1],results.get(i)[2],results.get(i)[5],"Add to list"));
										}
										window.listNotifications.updateList(cld);
										
									}else{
										System.out.println("No data");
									}

									//invoking database actitivities
									if(uid!=-1){
										MyDatabase mydb;
										try {
											mydb = new MyDatabase();
											//mydb.createTables();

											int temp=mydb.sqli.selectSingleDataInt("SELECT INTVALUE FROM tblSettings WHERE SETTING='LASTUID'", "INTVALUE");
											if(temp<uid){
												//update databse
												mydb.updateUID(uid);
											}
											System.out.println("Latest uid(Db) is "+temp+" | realtime "+uid);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									}
									
								} catch (Exception e) {e.printStackTrace();}
								
								try {Thread.sleep(window.interval);} catch (InterruptedException e) {e.printStackTrace();}
							}
						}	
					});
					threadNotifi.start();
					//? Notification Listening Thread end
					
				} catch (Exception e) {e.printStackTrace();	}
				
				
				
			}
		});
	}

	public MainWindow() throws Exception {
		initialize();
		
	}
	private void initialize() throws Exception {
		
		frameMain = new JFrame();
		frameMain.getContentPane().setBackground(Color.DARK_GRAY);
		frameMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent cLoca) {
				mouseCord=cLoca.getPoint(); //track initial mouse position(clicked)
			}
			@Override
			public void mouseReleased(MouseEvent cLoca) {
				mouseCord=null;
			}
		});
		frameMain.addMouseMotionListener(new MouseMotionAdapter() { //mouse dragged window
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen(); //current mouse dragger position
                frameMain.setLocation(currCoords.x - mouseCord.x, currCoords.y - mouseCord.y);
			}
		});
		
		frameMain.setBounds(100, 100, 617, 796);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setTitle("FEeLS Notification");
		frameMain.setUndecorated(true);//border less
		frameMain.setOpacity(0.95f);
		frameMain.setLocation(scrWidth-frameMain.getWidth(),scrHeight-frameMain.getHeight()-50);
		frameMain.getContentPane().setLayout(null);
		
		//Data to be added into list
				ArrayList<ListDataStructure> cld=new ArrayList<ListDataStructure>();
				for(int i=0; i<40;i++){
					cld.add(new ListDataStructure("title"+i,"msg "+i,"datetime "+i,"btn "+i));
				}
				
		listNotifications=new ListCustom(600, 600, cld);
		listNotifications.setBackground(Color.DARK_GRAY);
		listNotifications.setBounds(8, 81, 600,600+10);
		frameMain.getContentPane().add(listNotifications);
		
		
		/**
		 * PANEL TO_DO
		 */
		
		panelToDo = new JPanel();
		//panelToDo.setBounds(516, 51, panelW,panelH);
		panelToDo.setBounds(8, 81, panelW,panelH);
		
		listToDo=new ListCustomToDo(600, 600, cld);
		listToDo.setBackground(Color.DARK_GRAY);
		listToDo.setBounds(8, 100, panelToDo.getWidth()-16,panelToDo.getHeight()-100);
		panelToDo.add(listToDo);
		
		
		frameMain.getContentPane().add(panelToDo);
		
		JLabel lblFeelsMotify = new JLabel("FEeLS Notification");
		lblFeelsMotify.setForeground(Color.LIGHT_GRAY);
		lblFeelsMotify.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFeelsMotify.setBounds(12, 13, 228, 33);
		frameMain.getContentPane().add(lblFeelsMotify);
		
		ButtonCustom btnHome = new ButtonCustom("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisibilities("notifications");
			}
		});
		btnHome.setBounds(293, 694, 157, 30);
		frameMain.getContentPane().add(btnHome);
		
		ButtonCustom btncstmTodo = new ButtonCustom("Home");
		btncstmTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisibilities("todo");
			}
		});
		btncstmTodo.setText("ToDo");
		btncstmTodo.setBounds(451, 694, 157, 30);
		//btncstmTodo.setBackground(Color.darkGray);
		frameMain.getContentPane().add(btncstmTodo);
		
		try {
			MyDatabase mydb=new MyDatabase();
			mydb.resetDatabse();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		running=new MyDatabase();
	}
	
	public void setVisibilities(String win){
		panelToDo.setVisible(false);
		listNotifications.setVisible(false);
		switch(win){
		case "todo":
			panelToDo.setVisible(true); break;
		case "notifications":
			listNotifications.setVisible(true);
		}
	}
}
