package gui.java.sausecode.com;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;

import database.java.sausecode.com.MyDatabase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class ListItemDesign extends JPanel{
	/**
	 * Create the panel.
	 */
	private JLabel lblTitle;
	private ButtonCustomList btnDoto;
	private JLabel lblMsg;
	private JLabel lblDateTime;
	
	private int width=584,height=150;
	private String[] data={"UID","SUMMERY","DESCRIPTION","LAST_MODIFIED","DTSTAMP","DTSTART","CATEGORIES","DEADLINE","DONE"};
	
	public void setDimension(int w,int h){width=w; height=h;};
	public ListItemDesign(String title, String msg,String datetime, String btn) {
		setBackground(Color.darkGray);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBackground(Color.GRAY);
				//btnDoto.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.darkGray);
				//btnDoto.setBackground(Color.white);
			}
		});
		
		setBorder(null);
		setSize(600, 150);
		setMinimumSize(new Dimension(width,height));
		setMaximumSize(new Dimension(Short.MAX_VALUE,height));
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		
		
		lblTitle = new JLabel("Title");
		lblTitle.setForeground(SystemColor.menu);
		lblTitle.setBounds(12, 13, 560, 25);
		lblTitle.setText(title);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitle);
		
		lblMsg = new JLabel("msg body");
		lblMsg.setForeground(SystemColor.desktop);
		lblMsg.setBounds(12, 40, 560, 69);
		lblMsg.setText(msg);
		add(lblMsg);
		
		btnDoto = new ButtonCustomList("Do to list");
		btnDoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					MyDatabase mydb=new MyDatabase();
					mydb.addNewNote(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnDoto.setBounds(475, 112, 97, 25);
		//btnDoto.setBackground(Color.WHITE);
		btnDoto.setText(btn);
		add(btnDoto);
		
		lblDateTime = new JLabel("<dynamic>");
		lblDateTime.setForeground(SystemColor.control);
		lblDateTime.setBounds(12, 112, 457, 25);
		lblDateTime.setText(datetime);
		add(lblDateTime);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(0, 149, 600, 1);
		add(separator);

	}
}

