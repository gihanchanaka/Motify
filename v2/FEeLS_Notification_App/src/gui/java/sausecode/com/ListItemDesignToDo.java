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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class ListItemDesignToDo extends JPanel{
	/**
	 * Create the panel.
	 */
	private JLabel lblTitle;
	private ButtonCustomList btnDelete;
	private JLabel lblMsg;
	private JLabel lblDateTime;
	
	private int width=584,height=200;
	private JToggleButton tglbtnNewToggleButton;
	
	
	public void setDimension(int w,int h){width=w; height=h;};
	public ListItemDesignToDo(String title, String msg,String datetime, String btn) {
		setBackground(Color.GRAY);
		
	
		
		setBorder(null);
		setSize(width, height);
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
		
		btnDelete = new ButtonCustomList("Do to list");
		btnDelete.setBounds(121, 150, 97, 25);
		//btnDoto.setBackground(Color.WHITE);
		btnDelete.setText("Delete");
		add(btnDelete);
		
		lblDateTime = new JLabel("DateTime");
		lblDateTime.setForeground(SystemColor.control);
		lblDateTime.setBounds(12, 112, 457, 25);
		lblDateTime.setText(datetime);
		add(lblDateTime);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(0, height-2, width, 1);
		add(separator);
		
		ButtonCustomList btnEdit = new ButtonCustomList("Do to list");
		btnEdit.setText("Edit");
		btnEdit.setBounds(12, 150, 97, 25);
		add(btnEdit);
		
		tglbtnNewToggleButton = new JToggleButton("Done!");
		tglbtnNewToggleButton.setBounds(435, 150, 137, 25);
		add(tglbtnNewToggleButton);
		


	}
}

