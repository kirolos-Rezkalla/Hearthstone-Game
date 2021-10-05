package views;

import java.awt.Dimension;
import java.awt.Rectangle;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;


public class cardView extends JButton{
	private JLabel text; 
	public cardView(){
		text = new JLabel();
		this.setVisible(true);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(80, 120));
		this.add(text);
		text.setBounds(new Rectangle(new Dimension(120,80)));
		
		this.setBorderPainted(true);
	    this.setContentAreaFilled(false);
		ImageIcon cardview = new ImageIcon("Images/cardView.jpg");
		this.setIcon(cardview);
		
		
		this.revalidate();
		this.repaint();
		
	}
	
	public String getInfo(){
		String s="";
		s+="Name:\nMana Cost:\nRarity: ";
		return s;
	}
	public void setText(String s){
		text.setText(s);
	}
	public static void main(String[] args){
		new cardView();
	}

}
