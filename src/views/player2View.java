package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class player2View extends JFrame {
	private JTextArea player2;
	
    public player2View(){
    	this.setBounds(400, 200, 1200, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setLayout(null);
		this.setResizable(false);
		
		player2 = new JTextArea();
		player2.setOpaque(false);
		player2.setEditable(false);
		player2.setText("Player 2");
		player2.setBounds(500, 50, 190, 70);
		player2.setForeground(Color.WHITE);
		player2.setFont(new Font("", Font.CENTER_BASELINE, 50));
		this.add(player2);
		
		this.setTitle("Player's 2 Hero");
		
		this.revalidate();
		this.repaint();
		
    }
	public JTextArea getplayer2() {
		return player2;
	}
	public static void main(String[] args){
		new player2View();
	}
}