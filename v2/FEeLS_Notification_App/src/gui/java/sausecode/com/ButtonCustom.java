package gui.java.sausecode.com;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonCustom extends JButton {
	private Color background_normal=Color.gray; //getColor(105,105,105);
	private Color background_mousemove=getColor(110, 110, 110);
	private Color background_mouseclick=getColor(115, 115, 115);
	
	private Color forecolor_normal=getColor(200, 200, 200);
	private Color forecolor_mousemove=getColor(200, 200, 200);
	private Color forecolor_mouseclick=getColor(200, 200, 200);
	
	private Color border_normal=getColor(200, 200, 200);
	private Color border_mousemove=getColor(200, 200, 200);
	private Color border_mouseclick=getColor(200, 200, 200);
	private int border_size=1;
	
	
	public ButtonCustom(String text){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBackground(background_mousemove);
				setForeground(forecolor_mousemove);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				setBackground(background_normal);
				setForeground(forecolor_normal);
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setBackground(background_mouseclick);
				setForeground(forecolor_mouseclick);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				setBackground(background_mouseclick);
				setForeground(forecolor_mouseclick);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				setBackground(background_normal);
				setForeground(forecolor_normal);
			}
		});
		setText(text);
		setFocusPainted(false); //remove dotted out line when selected
		setProperties();
	}
	public void setProperties(){
		this.setBackground(Color.GRAY);
		this.setForeground(forecolor_normal);
		this.setBorder(new MatteBorder(border_size, border_size, border_size,border_size,border_normal));
	}

	public Color getColor(int r,int g,int b){
		return (Color) new Color(r,g,b);
	}
}
