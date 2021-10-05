package views;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class player1View extends JFrame {
	private JTextArea player1;
	
    public player1View(){
    	this.setBounds(400, 200, 1200, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setLayout(null);
		this.setResizable(false);
		
		player1 = new JTextArea();
		player1.setOpaque(false);
		player1.setEditable(false);
		player1.setText("Player 1");
		player1.setForeground(Color.WHITE);
		player1.setBounds(500, 50, 200, 70);
		player1.setCaretColor(Color.WHITE);
		player1.setFont(new Font("", Font.CENTER_BASELINE, 50));
		this.add(player1);
		
		this.setTitle("Player's 1 Hero");
		
		this.revalidate();
		this.repaint();
		
    }
	public JTextArea getPlayer1() {
		return player1;
	}
	public static void main(String[] args){
		new player1View();
	}
}