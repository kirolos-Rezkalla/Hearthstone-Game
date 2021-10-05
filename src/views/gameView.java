package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameView extends JFrame{
	private JLabel base;
	private JPanel currentPlayer;
	private JPanel opponentPlayer;
	private JPanel currentPlayerField;
	private JPanel currentPlayerActions;
	private JButton currentPlayerInfo;
	private JPanel currentPlayerHand;
	private JPanel currentPlayerBase;
	private JLabel text;
	private JPanel opponentPlayerBase;
	private JPanel opponentPlayerHand;
	private JButton opponentPlayerInfo; 
	private JPanel opponentPlayerField;
	

	
	
	public gameView(){
		base=new JLabel();
		currentPlayer=new JPanel();
		opponentPlayer=new JPanel();
		currentPlayerField=new JPanel();
		currentPlayerActions=new JPanel();
		currentPlayerInfo=new JButton();
		currentPlayerHand=new JPanel();
		currentPlayerBase=new JPanel();
		
		opponentPlayerBase = new JPanel();
		opponentPlayerHand = new JPanel();
		opponentPlayerInfo = new JButton();
		opponentPlayerField = new JPanel();
		
		  
		this.setBounds(150, 100 ,1600, 900);
		this.setResizable(false);
		this.setVisible(false);                   //visibility
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(base);
		base.setLayout(new BorderLayout());
		base.add(currentPlayer, BorderLayout.SOUTH);
		base.add(opponentPlayer, BorderLayout.NORTH);
		
		currentPlayer.setLayout(new BorderLayout());
		currentPlayer.add(currentPlayerField,BorderLayout.NORTH);
		currentPlayer.add(currentPlayerBase,BorderLayout.SOUTH);
		currentPlayerBase.setPreferredSize(new Dimension(this.getWidth(), 200));
		currentPlayerActions.setPreferredSize(new Dimension(380, 200));
		currentPlayerInfo.setPreferredSize(new Dimension(200, 200));
		currentPlayerHand.setPreferredSize(new Dimension(380, 200));
		
		opponentPlayer.setLayout(new BorderLayout());
		opponentPlayer.add(opponentPlayerField,BorderLayout.SOUTH);
		opponentPlayer.add(opponentPlayerBase,BorderLayout.NORTH);
		
		opponentPlayerField.setVisible(true);
		opponentPlayerField.setPreferredSize(new Dimension(this.getWidth(),250));
		
		currentPlayerField.setVisible(true);
		currentPlayerField.setPreferredSize(new Dimension(this.getWidth(),250));
		
		opponentPlayerBase.setLayout(new BorderLayout());
		opponentPlayerBase.add(opponentPlayerHand,BorderLayout.EAST);
		opponentPlayerBase.add(opponentPlayerInfo,BorderLayout.WEST);

		currentPlayerBase.setLayout(new GridLayout(1, 3));
		currentPlayerBase.add(currentPlayerActions);
		currentPlayerBase.add(currentPlayerInfo);
		currentPlayerBase.add(currentPlayerHand);
		
		currentPlayerHand.setLayout(new GridLayout(2, 5));
		
    	currentPlayerField.setBackground(Color.getHSBColor(0, 90, 70));
    	opponentPlayerField.setBackground(Color.getHSBColor(0, 90, 70));
    	opponentPlayerBase.setBackground(Color.getHSBColor(0, 90, 70));
    	opponentPlayerHand.setBackground(Color.getHSBColor(0, 90, 70));
    	currentPlayerHand.setBackground(Color.getHSBColor(0, 90, 70));
		
    	text = new JLabel();
    	currentPlayerInfo.add(text);
		text.setBounds(20,20,200,150);
		
		//Image img = Toolkit.getDefaultToolkit().getImage("Herarthsone_background.png");
		//base.setIcon(new ImageIcon("Images/Hearthstone_background.png"));
		//currentPlayerField.setBackground(Color.BLUE);
		
		this.revalidate();
		this.repaint();
		
				
				
	}
	
	

	public JPanel getOpponentPlayerBase() {
		return opponentPlayerBase;
	}

	public void setOpponentPlayerBase(JPanel opponentPlayerBase) {
		this.opponentPlayerBase = opponentPlayerBase;
	}

	public JPanel getOpponentPlayerHand() {
		return opponentPlayerHand;
	}

	public void setOpponentPlayerHand(JPanel opponentPlayerHand) {
		this.opponentPlayerHand = opponentPlayerHand;
	}

	public JButton getOpponentPlayerInfo() {
		return opponentPlayerInfo;
	}


	public void setCurrentPlayerInfo(String s) {
	    text.setText(s);
	}
	
	public void setText(String s){
		text.setText(s);
	}


	public void setText(JLabel text) {
		this.text = text;
	}

	public JLabel getBase() {
		return base;
	}

	public JPanel getCurrentPlayer() {
		return currentPlayer;
	}

	public JPanel getOpponentPlayer() {
		return opponentPlayer;
	}

	public JPanel getCurrentPlayerField() {
		return currentPlayerField;
	}

	public JPanel getCurrentPlayerActions() {
		return currentPlayerActions;
	}

	public JButton getCurrentPlayerInfo() {
		return currentPlayerInfo;
	}

	public JPanel getCurrentPlayerHand() {
		return currentPlayerHand;
	}

	public JPanel getCurrentPlayerBase() {
		return currentPlayerBase;
	}


	public JPanel getOpponentPlayerField() {
		return opponentPlayerField;
	}

	public static void main(String[] args){
		
	}

}
