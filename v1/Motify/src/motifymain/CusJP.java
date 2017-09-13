package motifymain;

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

public class CusJP extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel lblTitle;
	private JButton btnDoto;
	private JLabel lblMsg;
	

	
	public CusJP(String title, String msg, String btn) {
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBackground(Color.white);
				btnDoto.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(null);
				btnDoto.setBackground(Color.white);
			}
		});
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(200, 200, 200)));
		setSize(640, 150);
		setMinimumSize(new Dimension(640,150));
		setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		setPreferredSize(new Dimension(640, 150)); //key point
	//	setBounds(0, 0, 640, 150);
		setLayout(null);
		
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 13, 607, 20);
		lblTitle.setText(title);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitle);
		
		btnDoto = new JButton("Do to list");
		btnDoto.setBackground(Color.WHITE);
		btnDoto.setBounds(437, 108, 182, 21);
		btnDoto.setText(btn);
		add(btnDoto);
		
		lblMsg = new JLabel("msg body");
		lblMsg.setBounds(12, 39, 607, 70);
		lblMsg.setText(msg);
		add(lblMsg);

	}

}
