package motifymain;


import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
//import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
//import javax.swing.SwingUtilities;



import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.Canvas;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.ChangeListener;



import javax.swing.event.ChangeEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class Motify {
	public int refreshInterval=10;
	public String userURL;
	ArrayList<Reminder> toAdd=new ArrayList<Reminder>();
	ArrayList<NotificationUpdate> rssList=new ArrayList<NotificationUpdate>();
	
	
	
	private Point mouseCord=null;
	private JFrame frmMotify;
	public JList<String> listNotifications;
	public JList<String> listLabSubmissions;
	public Vector<String> listdataNotifications;
	public Vector<String> listdataLabsubmissions;
	public JButton btnExit;
	public JPanel panelDetails;
	public JButton btnDetailsOk;
	public JLabel lblDetailsDisplay;
	public JPanel panelList;
	public JSlider sliderRefreshInterval;
	public JLabel lblRefreshInterval;
	public JLabel lblDetailsHeader;
	
	///temp
	/**
	rssLinks[0]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14885%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F299%2Frss.xml";
	rssLinks[1]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml";
	rssLinks[2]="https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml";
	**/
	//public String rssLinks[]={"https://feels.pdn.ac.lk/rss/file.php?file=%2F14885%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F299%2Frss.xml","https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml","https://feels.pdn.ac.lk/rss/file.php?file=%2F14988%2Fc282c038bfc7214a38746cc77de8bc45%2Fmod_forum%2F305%2Frss.xml"};
	ArrayList<String> rssLinks;
	//ScreenInfor
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			public int scrWidth =(int) screenSize.getWidth();
			public int scrHeight =(int) screenSize.getHeight();
			///
			
			
			private JLabel btnPreferences;
			private JLabel lblFeelsNotificationSystem;
			private JPanel panelPreferences;
			private JLabel lblUrlFeels;
			private JButton btnPrefSaveSettings;
			private JButton btnPrefeCancel;
			private JTextArea txtPrefUrl;
			private JLabel lblPreferences;
			private JLabel lblNotifications;
			private JLabel lblLabSubmisions;
			private JPanel panelRssInput;
			private JLabel lblRssUrl;
			private JTextArea txtRssUrlsInput;
			private JButton btnRssSave;
			private JButton btnRsssCancel;
			private JLabel btnAddRssUrls;
			
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Motify window = new Motify();
					window.frmMotify.setVisible(true);
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Motify() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		//rssArrayllist update
		rssLinks=loadRssUrls();
		
		//loading url, interval infor
		String ans[];
		try {
			ans = loadConfigurations();
			userURL=ans[0];
			refreshInterval=Integer.valueOf(ans[1]);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			//userURL=ans[0];
			refreshInterval=10;
		}
		
		
		
		//listData
		listdataNotifications=new Vector<String>();
		listdataLabsubmissions=new Vector<String>();
		
		//form parameters
		frmMotify = new JFrame();
		frmMotify.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\area51\\Developer\\Hackathon\\2016aces\\ref\\icon_01.png"));
		frmMotify.getContentPane().setBackground(SystemColor.desktop);
		frmMotify.setTitle("Motify"); 
		frmMotify.setUndecorated(true);//border less
		frmMotify.setBounds(100, 100, 931, 622);
		frmMotify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMotify.setOpacity(0.95f);
		frmMotify.setLocation(scrWidth-frmMotify.getWidth(),scrHeight-frmMotify.getHeight()-50);
		frmMotify.getContentPane().setLayout(null);
		
		panelPreferences = new JPanel();
		panelPreferences.setBounds(0, 96, 931, 500);
		frmMotify.getContentPane().add(panelPreferences);
		panelPreferences.setBackground(Color.DARK_GRAY);
		panelPreferences.setVisible(false);
		panelPreferences.setLayout(null);
		
		lblUrlFeels = new JLabel("Enter Your FEeLS Read URL");
		lblUrlFeels.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUrlFeels.setForeground(SystemColor.controlShadow);
		lblUrlFeels.setBackground(SystemColor.controlShadow);
		lblUrlFeels.setBounds(12, 79, 198, 19);
		panelPreferences.add(lblUrlFeels);
		
		txtPrefUrl = new JTextArea();
		txtPrefUrl.setForeground(SystemColor.control);
		txtPrefUrl.setBackground(SystemColor.textInactiveText);
		txtPrefUrl.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtPrefUrl.setBounds(12, 107, 907, 97);
		panelPreferences.add(txtPrefUrl);
		
		lblRefreshInterval = new JLabel("Refresh Interval (Minutes)");
		lblRefreshInterval.setForeground(SystemColor.controlShadow);
		lblRefreshInterval.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRefreshInterval.setBackground(SystemColor.controlShadow);
		lblRefreshInterval.setBounds(12, 217, 254, 19);
		panelPreferences.add(lblRefreshInterval);
		
		sliderRefreshInterval = new JSlider();  //------------------------------------<<< slider_ Interval here
		sliderRefreshInterval.setValue(refreshInterval);
		sliderRefreshInterval.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//System.out.println("changed");
				lblRefreshInterval.setText("Refresh Interval (Minutes) : "+sliderRefreshInterval.getValue());
			}
		});
		sliderRefreshInterval.setPaintTicks(true);
		sliderRefreshInterval.setSnapToTicks(true);
		sliderRefreshInterval.setForeground(Color.LIGHT_GRAY);
		sliderRefreshInterval.setBackground(Color.DARK_GRAY);
		sliderRefreshInterval.setValue(10);
		sliderRefreshInterval.setMaximum(72);
		sliderRefreshInterval.setMinimum(5);
		sliderRefreshInterval.setBounds(15, 265, 907, 26);
		panelPreferences.add(sliderRefreshInterval);
		
		btnPrefSaveSettings = new JButton("Save Settings");
		btnPrefSaveSettings.addActionListener(new ActionListener() {//-----------------------------------------------------<<saving configurations
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveConfigurations(txtPrefUrl.getText().replace(" ",""), sliderRefreshInterval.getValue());
					hideFrames("main");
					System.out.println("files saved successfully");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPrefSaveSettings.setForeground(Color.WHITE);
		btnPrefSaveSettings.setBackground(Color.DARK_GRAY);
		btnPrefSaveSettings.setBounds(330, 328, 124, 25);
		panelPreferences.add(btnPrefSaveSettings);
		
		btnPrefeCancel = new JButton("Cancel");
		btnPrefeCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //---------------------------------pref cancel event
				hideFrames("main");
			}
		});
		btnPrefeCancel.setForeground(Color.WHITE);
		btnPrefeCancel.setBackground(Color.DARK_GRAY);
		btnPrefeCancel.setBounds(486, 328, 124, 25);
		panelPreferences.add(btnPrefeCancel);
		
		lblPreferences = new JLabel("Preferences");
		lblPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreferences.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreferences.setForeground(Color.LIGHT_GRAY);
		lblPreferences.setBounds(387, 30, 173, 35);
		panelPreferences.add(lblPreferences);
		

		//Panel_Title
		JPanel panelTitle = new JPanel();
//		panelTitle.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				btnPreferences.setForeground(new Color(170,170,170));  //-------------------------------------------- pref buton default
//			}
//		});
		panelTitle.setBackground(Color.DARK_GRAY);
		panelTitle.setBounds(0, 0, 931, 95);
		frmMotify.getContentPane().add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Motify");
		lblTitle.setBounds(21, 8, 145, 51);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(SystemColor.text);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 31));
		
		btnExit = new JButton("...");
		btnExit.setForeground(Color.GRAY);
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(882, 14, 37, 38);
		panelTitle.add(btnExit);
		
		
		//checking button  1 ----------------------------------------------------------------<<<<
		final JButton btnSync = new JButton("Sync");
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					updateListNotifications();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		

		btnSync.setBounds(415, 62, 97, 25);
		panelTitle.add(btnSync);
		
		//checking button  2 ----------------------------------------------------------------<<<<
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateListNotifications2(); 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRefresh.setBounds(551, 62, 97, 25);
		panelTitle.add(btnRefresh);
		
		//Label_Button
		btnPreferences = new JLabel("Preferences");
	
		btnPreferences.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				//System.out.println("pref moved");  ///------------------------------------------------<<<<<<<<<<<<<<<<<<<<<TESTING pref
				btnPreferences.setForeground(new Color(255,255,255));
			}
		});
		btnPreferences.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPreferences.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//------------------------------------- ------------------------------------------- <<< Preferences event
				hideFrames("preferences");
				
//				String ans[];
//				try {
//					ans = loadConfigurations();
//					System.out.println(ans[0]);
//					System.out.println(ans[1]);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnPreferences.setForeground(new Color(170,170,170));  //-------------------------------------------- pref buton default
			}
		});
		btnPreferences.setForeground(SystemColor.activeCaptionBorder);
		btnPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		btnPreferences.setBounds(822, 65, 97, 16);
		panelTitle.add(btnPreferences);
		
		lblFeelsNotificationSystem = new JLabel("FEeLS notification system");
		lblFeelsNotificationSystem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFeelsNotificationSystem.setForeground(SystemColor.activeCaptionBorder);
		lblFeelsNotificationSystem.setBounds(22, 52, 222, 29);
		panelTitle.add(lblFeelsNotificationSystem);
		
		btnAddRssUrls = new JLabel("Add RSS URLs");
		btnAddRssUrls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideFrames("rssinp");//--------------------------------------<<<<<<<<<<<<<<< btnRssUrls event here
				
				
			}
		});
		btnAddRssUrls.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddRssUrls.setForeground(SystemColor.activeCaptionBorder);
		btnAddRssUrls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddRssUrls.setBounds(688, 66, 97, 16);
		panelTitle.add(btnAddRssUrls);
		
		
		//Detail Panel //---------------------------------------- ||| Details Panel
		panelDetails = new JPanel();
		panelDetails.setBounds(0, 96, 931, 500);
		frmMotify.getContentPane().add(panelDetails);
		panelDetails.setBackground(SystemColor.controlShadow);
		panelDetails.setVisible(false);
		panelDetails.setLayout(null);
		
		lblDetailsHeader = new JLabel("Details");
		lblDetailsHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsHeader.setForeground(SystemColor.text);
		lblDetailsHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDetailsHeader.setBounds(24, 13, 883, 25);
		panelDetails.add(lblDetailsHeader);
		
		btnDetailsOk = new JButton("OK");
		btnDetailsOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //-----------------------details ok event
				hideFrames("main");
			}
		});
		btnDetailsOk.setBackground(SystemColor.scrollbar);
		btnDetailsOk.setBounds(378, 465, 136, 25);
		panelDetails.add(btnDetailsOk);
		
		lblDetailsDisplay = new JLabel("Display Data");
		
		lblDetailsDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetailsDisplay.setVerticalAlignment(SwingConstants.TOP);
		lblDetailsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsDisplay.setBackground(SystemColor.windowBorder);
		lblDetailsDisplay.setBounds(12, 42, 883, 408);
		panelDetails.add(lblDetailsDisplay);
		
		panelRssInput = new JPanel();
		panelRssInput.setBackground(Color.GRAY);
		panelRssInput.setVisible(false);
		panelRssInput.setBounds(0, 96, 931, 500);
		frmMotify.getContentPane().add(panelRssInput);
		panelRssInput.setLayout(null);
		
		lblRssUrl = new JLabel("RSS URLs");
		lblRssUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblRssUrl.setForeground(Color.WHITE);
		lblRssUrl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRssUrl.setBounds(296, 13, 316, 16);
		panelRssInput.add(lblRssUrl);
		
		txtRssUrlsInput = new JTextArea();
		txtRssUrlsInput.setForeground(Color.BLACK);
		txtRssUrlsInput.setBackground(Color.LIGHT_GRAY);
		txtRssUrlsInput.setBounds(12, 42, 907, 400);
		panelRssInput.add(txtRssUrlsInput);
		
		btnRssSave = new JButton("Save Settings");
		btnRssSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner in=new Scanner(txtRssUrlsInput.getText());//----------------------------------------------------------------<<< Rss save
				ArrayList<String> vir=new ArrayList<String>();
				
				while(in.hasNextLine()){
					vir.add(in.nextLine());
				}
				try {
					saveRssUrls(vir);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRssSave.setForeground(Color.WHITE);
		btnRssSave.setBackground(Color.DARK_GRAY);
		btnRssSave.setBounds(307, 450, 124, 25);
		panelRssInput.add(btnRssSave);
		
		btnRsssCancel = new JButton("Cancel");
		btnRsssCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   ///-----------------------------------------<<<< Rss cancel btn
				hideFrames("main");
			}
		});
		btnRsssCancel.setForeground(Color.WHITE);
		btnRsssCancel.setBackground(Color.DARK_GRAY);
		btnRsssCancel.setBounds(463, 450, 124, 25);
		panelRssInput.add(btnRsssCancel);
		
		//List Panels
		panelList = new JPanel();
		panelList.setBounds(0, 96, 931, 526);
		frmMotify.getContentPane().add(panelList);
		panelList.setBackground(SystemColor.windowBorder);
		panelList.setLayout(null);
		
			//// list notifications    ---------------------------------------- ||| Notification double click event listener
			listNotifications = new JList(listdataNotifications);
			listNotifications.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JList list = (JList)arg0.getSource();
			        if (arg0.getClickCount() == 2) {

			            // Double-click detected
			            int index = list.locationToIndex(arg0.getPoint());
			            ///call from here to list double clicks   //---------------------------------------- <<< list double click event
			            System.out.println("notification_index is "+index);
			            if(index>=0){
			            	String msg="<html>"+"<br>"+rssList.get(index).description+"<br><br>"+rssList.get(index).link+"<br><br>"+" Deadline "+rssList.get(index).pubTime[2] +"/"+rssList.get(index).pubTime[1] +"/"+rssList.get(index).pubTime[0];
				            lblDetailsDisplay.setText(msg);
				            lblDetailsHeader.setText(rssList.get(index).title);
				            
				            hideFrames("details");
			            }
			            
			        } 
				}
			});
			listNotifications.setForeground(SystemColor.text);
			listNotifications.setBackground(SystemColor.windowBorder);
			listNotifications.setFont(new Font("Dialog", Font.PLAIN, 18));
			listNotifications.setBounds(12, 47, 424, 466);
			
			//listNotifications.setCellRenderer(new MyCellRenderer());  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++NEW REN
			panelList.add(listNotifications);
			
			lblNotifications = new JLabel("Notifications");
			lblNotifications.setForeground(UIManager.getColor("scrollbar"));
			lblNotifications.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNotifications.setBounds(15, 13, 183, 31);
			panelList.add(lblNotifications);
			
			//// lab submissions list ---------------------------------------- ||| lab submissions list
			listLabSubmissions = new JList(listdataLabsubmissions);
			listLabSubmissions.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JList list = (JList)arg0.getSource();
			        if (arg0.getClickCount() == 2) {

			            // Double-click detected
			            int index = list.locationToIndex(arg0.getPoint());
			            ///call from here to list double clicks   //---------------------------------------- <<< list double click event
			            System.out.println("lab_submission index is "+index);
			            
			            
			            
			            if(index>=0){
				            showDetails(index);
				            String msg="<html><br>"+toAdd.get(index).summary+"<br><br>"+toAdd.get(index).description+"<br><br>Deadline : "+toAdd.get(index).endTime[2] +"/"+toAdd.get(index).endTime[1] +"/"+toAdd.get(index).endTime[0];
				            lblDetailsDisplay.setText(msg);
				            lblDetailsHeader.setText(toAdd.get(index).subject);
			            }
			            
			        } 
				}
			});
			listLabSubmissions.setForeground(SystemColor.text);
			listLabSubmissions.setBackground(SystemColor.windowBorder);
			listLabSubmissions.setFont(new Font("Dialog", Font.PLAIN, 18));
			listLabSubmissions.setBounds(463, 47, 456, 466);
			panelList.add(listLabSubmissions);
			
			lblLabSubmisions = new JLabel("Lab Submisions");
			lblLabSubmisions.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblLabSubmisions.setForeground(SystemColor.scrollbar);
			lblLabSubmisions.setBounds(463, 13, 183, 31);
			panelList.add(lblLabSubmisions);
		
		///application exit button --------------------------------------- ||| application exit
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		

		frmMotify.addMouseListener(new MouseListener(){ //------------------------------------------------- <<< Form mouse listener
            public void mouseReleased(MouseEvent e) {
                mouseCord = null;
            }
            public void mousePressed(MouseEvent e) {
                mouseCord = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });
		
		frmMotify.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frmMotify.setLocation(currCoords.x - mouseCord.x, currCoords.y - mouseCord.y);
            }
        });
		
		//opening files  //------------------------------------------------------------------ <<< open saved file
		try {
			toAdd=ArrayFileHandling.readArray();
			for(int x=0;x<toAdd.size();x++){
				listdataLabsubmissions.addElement(toAdd.get(x).subject+" : "+toAdd.get(x).summary+" ; Deadline "+toAdd.get(x).endTime[2] +"/"+toAdd.get(x).endTime[1] +"/"+toAdd.get(x).endTime[0]);
				listLabSubmissions.setListData(listdataLabsubmissions);
			}
			
			rssList=RSSFileHandling.readRSSData();
			for(int x=rssList.size()-1;x>-1;x--){
				listdataNotifications.addElement(rssList.get(x).title+" ; Deadline "+rssList.get(x).pubTime[2] +"/"+rssList.get(x).pubTime[1] +"/"+rssList.get(x).pubTime[0]);
				listNotifications.setListData(listdataNotifications);
			}
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void updateListNotifications() throws Exception{ //internet seen		
		listdataNotifications.clear();
		listNotifications.setListData(listdataNotifications);
		
		listdataLabsubmissions.clear();
		listLabSubmissions.setListData(listdataLabsubmissions);
		//toAdd=ReaderOne.readURL("https://feels.pdn.ac.lk/calendar/export_execute.php?userid=1647&authtoken=933a00e7d100d903eacfef4a944da60dbf9bcb16&preset_what=all&preset_time=monthnow");
		toAdd=ReaderOne.readURL(userURL);
		for(int x=0;x<toAdd.size();x++){
			listdataLabsubmissions.addElement(toAdd.get(x).subject+" : "+toAdd.get(x).summary+" ; Deadline "+toAdd.get(x).endTime[2] +"/"+toAdd.get(x).endTime[1] +"/"+toAdd.get(x).endTime[0]);
			listLabSubmissions.setListData(listdataLabsubmissions);
		}
		
		//save
		ArrayFileHandling.saveArray(toAdd); //------------------------------------------------------------------ <<< save file
		RSSFileHandling.saveRSSData(rssList);
		
		
		rssList=RSSHandling.getArrayForAllRSSLinks(rssLinks);
		for(int x=rssList.size()-1;x>-1;x--){
			listdataNotifications.addElement(rssList.get(x).title+" ; Deadline "+rssList.get(x).pubTime[2] +"/"+rssList.get(x).pubTime[1] +"/"+rssList.get(x).pubTime[0]);
			listNotifications.setListData(listdataNotifications);
		}
	}
	
	public void updateListNotifications2() throws Exception{
		System.out.println(toAdd.size());
		toAdd=ArrayHandling.remainingReminders(toAdd);
		listdataNotifications.clear();
		listNotifications.setListData(listdataNotifications);
			
		listdataLabsubmissions.clear();
		listLabSubmissions.setListData(listdataLabsubmissions);
			
		//toAdd=ReaderOne.readURL("https://feels.pdn.ac.lk/calendar/export_execute.php?userid=2385&authtoken=dce15e9ca0de8df24e32a9da4cb9abdda757298b&preset_what=all&preset_time=monthnow");
		for(int x=0;x<toAdd.size();x++){
			//System.out.println(toAdd.get(x));
			listdataLabsubmissions.addElement(toAdd.get(x).subject+" : "+toAdd.get(x).summary+" ; Deadline "+toAdd.get(x).endTime[2] +"/"+toAdd.get(x).endTime[1] +"/"+toAdd.get(x).endTime[0]);
			listLabSubmissions.setListData(listdataLabsubmissions);
		}
		
		for(int x=rssList.size()-1;x>-1;x--){
			listdataNotifications.addElement(rssList.get(x).title+" ; Deadline "+rssList.get(x).pubTime[2] +"/"+rssList.get(x).pubTime[1] +"/"+rssList.get(x).pubTime[0]);
			listNotifications.setListData(listdataNotifications);
		}
		
	}
	
	
	
	public void showDetails(int index){
		lblDetailsDisplay.setText("Selected index is "+index);
		
		hideFrames("details");
	}
	
	public void hideFrames(String active){
		if(active=="details"){
			listLabSubmissions.setVisible(false);
			listNotifications.setVisible(false);
			panelPreferences.setVisible(false);
			panelDetails.setVisible(true);
			lblLabSubmisions.setVisible(false);
			lblNotifications.setVisible(false);
			panelRssInput.setVisible(false);
		}else if(active=="main"){
			listLabSubmissions.setVisible(true);
			listNotifications.setVisible(true);
			panelDetails.setVisible(false);
			panelPreferences.setVisible(false);
			lblLabSubmisions.setVisible(true);
			lblNotifications.setVisible(true);
			panelRssInput.setVisible(false);
		}else if(active=="preferences"){
			listLabSubmissions.setVisible(false);
			listNotifications.setVisible(false);
			panelPreferences.setVisible(true);
			panelDetails.setVisible(false);
			lblLabSubmisions.setVisible(false);
			lblNotifications.setVisible(false);
			panelRssInput.setVisible(false);
		}else if(active=="rssinp"){
			listLabSubmissions.setVisible(false);
			listNotifications.setVisible(false);
			panelPreferences.setVisible(false);
			panelDetails.setVisible(false);
			lblLabSubmisions.setVisible(false);
			lblNotifications.setVisible(false);
			panelRssInput.setVisible(true);
		}
	}
	
	
	///----------------------------------------------------------------------------------------- Preferences part here
	
	public String[] loadConfigurations() throws FileNotFoundException{
		Scanner in=new Scanner(new File("motify_preps.inf"));
		String[] ans=new String[2];
		ans[0]=in.nextLine(); ans[1]=in.nextLine();
		return ans;
		
	}
	public void saveConfigurations(String url, int interval) throws IOException{
										//line 1, line 2
		List<String> lines = Arrays.asList(url, String.valueOf(interval));
		Path file = Paths.get("motify_preps.inf");
		Files.write(file, lines, Charset.forName("UTF-8"));
		//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}
	
	//--------------------------------------------------------------------------<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Rss urls
	public ArrayList<String> loadRssUrls() throws FileNotFoundException{
		Scanner in=new Scanner(new File("motify_rss.inf"));
		ArrayList<String> virt=new ArrayList<String>();
		

		while(in.hasNext()){
			String st=in.nextLine();
			virt.add(st);
			System.out.println(st);
		}
		
		
		return virt;
		
		
	}
	
	public void saveRssUrls(ArrayList<String> inp) throws IOException{
										
		List<String> lines =inp;
		Path file = Paths.get("motify_rss.inf");
		Files.write(file, lines, Charset.forName("UTF-8"));
		//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		System.out.println("Saved successfully");
	}
}
