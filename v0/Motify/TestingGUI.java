import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;

public class TestingGUI implements ActionListener{
	
	TestingGUI() throws Exception{
		ArrayList<Reminder> reminders=ReaderOne.readURL("https://feels.pdn.ac.lk/calendar/export_execute.php?userid=2385&authtoken=dce15e9ca0de8df24e32a9da4cb9abdda757298b&preset_what=all&preset_time=monthnow");
		JFrame mainFrame=new JFrame();
		mainFrame.setBounds(400, 400, 400, 400);
		mainFrame.setVisible(true);
		ArrayFileHandling.saveArray(reminders);
		ArrayList<Reminder> arrayAfterReading=ArrayFileHandling.readArray();
		//reminders=ArrayHandling.remainingReminders(reminders);
		
		for(int x=0;x<arrayAfterReading.size();x++){
			JButton button=new JButton(""+arrayAfterReading.get(x).UID+" : "+arrayAfterReading.get(x).summary);
			button.setBounds(50, 50*x, 400, 50);
			button.setEnabled(true);
			button.addActionListener(this);
			mainFrame.getContentPane().add(button);
		}
	}
	
	public static void main(String args[]) throws Exception{
		TestingGUI x=new TestingGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame thisActivity=new JFrame(e.getSource().toString());
		thisActivity.setBounds(400,400,400,400);
		JTextField textOnActivity=new JTextField();
		textOnActivity.setText("");
		thisActivity.setVisible(true);
		
	}
	
	
	
	
}
