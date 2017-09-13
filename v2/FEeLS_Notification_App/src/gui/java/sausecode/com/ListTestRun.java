package gui.java.sausecode.com;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ListTestRun {
	private ListCustom listC;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListTestRun window = new ListTestRun();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListTestRun() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Data to be added into list
		ArrayList<ListDataStructure> cld=new ArrayList<ListDataStructure>();
		for(int i=0; i<40;i++){
			cld.add(new ListDataStructure("title"+i,"msg "+i,"datetime "+i,"btn "+i));
		}
		
							//width,height,data,
		listC=new ListCustom(650,400,cld); //assigning list
		
		frame.add(listC);
		
	}

}
