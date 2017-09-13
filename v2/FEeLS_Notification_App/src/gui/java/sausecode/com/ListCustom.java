package gui.java.sausecode.com;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ListCustom extends JPanel {
	public JPanel myli;
	public JScrollPane scrollPane;

	
	public ListCustom(int width,int height,ArrayList<ListDataStructure> li){
		
		myli=new JPanel();
        for(ListDataStructure d : li){
        	myli.add(new ListItemDesign(d.getData()[0],d.getData()[1],d.getData()[2],d.getData()[3]));
        }
        myli.setSize(width,height);
		myli.setBounds(0, 0, width,height);
		myli.setLayout(new BoxLayout(myli, BoxLayout.PAGE_AXIS));
		myli.setBackground(Color.GRAY);
        
        scrollPane=new JScrollPane(myli);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setPreferredSize(new Dimension(width,height)); // important
        
        setBounds(0, 0, width, height);
        setBackground(Color.darkGray);
        
        new JPanel(null);
        setPreferredSize(new Dimension(width,height));
        add(scrollPane);
	}
	
	public void updateList(ArrayList<ListDataStructure> newlist){
		scrollPane.setVisible(false);
		myli.removeAll();
		for(ListDataStructure d : newlist){
        	myli.add(new ListItemDesign(d.getData()[0],d.getData()[1],d.getData()[2],d.getData()[3]));
        }
		
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		scrollPane.getVerticalScrollBar().setValue(0);
		scrollPane.setVisible(true);
	}
}
