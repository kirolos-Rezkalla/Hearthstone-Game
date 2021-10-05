package views;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameStartView extends JFrame{
	private JPanel startGame;
	
	public GameStartView(){
		this.setBounds(400, 200, 1200, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		startGame = new JPanel();
		startGame.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		startGame.setLayout(null);
		this.setTitle("HearthStone");
		this.add(startGame);
		
		this.revalidate();
		this.repaint();
	}
	public JPanel getStartGame() {
		return startGame;
	}
	public static void main(String[] args){
		new GameStartView();
	}
	

}
