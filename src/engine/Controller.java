package engine;

//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;

//import org.hamcrest.core.IsCollectionContaining;
//import org.hamcrest.core.IsSame;



import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
//import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.DivineSpirit;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import views.GameStartView;
import views.cardView;
import views.gameView;
import views.player1View;
import views.player2View;

public class Controller implements ActionListener,GameListener{
	private Game model;
	private gameView gameViewP1;
	private gameView gameViewP2;
	private player1View player1SelectionView;
	private player2View player2SelectionView;
	private GameStartView gameStart;
	private Hero player1Hero;
	private Hero player2Hero;
	private JButton selectedMinion;
	
	private ArrayList<JButton> handButtons;
	private ArrayList<JButton> currentFieldButtons;
	private ArrayList<JButton> opponentFieldButtons;
	private int currentFieldIndex = 0;
	private int targetIndex;
	
	private JButton magePowerSelectedButton;
	private JButton priestPowerSelectedButton;
	private JButton minionTargetSpellSelected;
	private JButton leechingSpellSelected;
	private JButton heroTargetSpellSelected;
	private JButton divineSpiritSelectedButton;
	private JButton sealOfChampionsSelectedButton;
 
	
	
	public Controller(){
		
	    gameViewP1=new gameView();
	    gameViewP2=new gameView();
	    	    
	    gameViewP1.setTitle("Player 1");
	    gameViewP2.setTitle("Player 2");

	    selectedMinion = new JButton();
	    selectedMinion = null;
	    
	    magePowerSelectedButton = new JButton();
	    magePowerSelectedButton = null;
	    
	    priestPowerSelectedButton = new JButton();
	    priestPowerSelectedButton = null;
	    
	    minionTargetSpellSelected = new JButton();
	    minionTargetSpellSelected =  null;
	    
	    leechingSpellSelected = new JButton();
	    leechingSpellSelected = null;
	    
	    heroTargetSpellSelected = new JButton();
	    heroTargetSpellSelected = null;
	    
	    divineSpiritSelectedButton = new JButton();
	    divineSpiritSelectedButton = null;
	    
	    sealOfChampionsSelectedButton = new JButton();
	    sealOfChampionsSelectedButton = null;
	    
	    
	    handButtons = new ArrayList<JButton>();
	    currentFieldButtons = new ArrayList<JButton>();
	    opponentFieldButtons = new ArrayList<JButton>();
	    

	    targetIndex = 0;
	    
	    
		    //---------- Game start view ------------
		
            gameStart = new GameStartView();		
		    JButton startGame = new JButton("Start Game");
		    startGame.addActionListener(this);
		    startGame.setFont(new Font("", Font.BOLD,80));
		    startGame.setForeground(Color.WHITE);
		    startGame.setBounds(380, 550 , 465, 80);
		    startGame.setContentAreaFilled(false);
		    startGame.setBorderPainted(true);
		    gameStart.getStartGame().add(startGame);
		    
		    JLabel background = new JLabel();
			background.setBounds(0, 0, 1200, 800);
			background.setIcon(new ImageIcon("Images/Hearthstone_background.png"));
			gameStart.getStartGame().add(background);
		    
		
		    //---------player 1 selection view-------------
		    
		    player1SelectionView = new player1View();
		    
		   
			JButton hunter = new JButton();
			hunter.setActionCommand("Hunter");
			hunter.setBounds(43, 280, 200, 270);
			hunter.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			hunter.addActionListener(this);
			player1SelectionView.add(hunter);
			
			hunter.setHorizontalTextPosition(SwingConstants.CENTER);
			hunter.setBorderPainted(false);
		    hunter.setContentAreaFilled(false);
			ImageIcon rexxar = new ImageIcon("Images/Hunter1.png");
			hunter.setIcon(rexxar);
			
			JButton mage = new JButton();
			mage.setActionCommand("Mage");
			mage.setBounds(273, 280, 200, 270);
			mage.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			mage.addActionListener(this);
			player1SelectionView.add(mage);
			
			mage.setBorderPainted(false);
			mage.setContentAreaFilled(false);
			ImageIcon mageImage = new ImageIcon("Images/Mage7.png");
			mage.setHorizontalTextPosition(SwingConstants.CENTER);
			mage.setIcon(mageImage);
			
			JButton paladin = new JButton();
			paladin.setActionCommand("Paladin");
			paladin.setBounds(503, 280, 200, 270);
			paladin.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			paladin.addActionListener(this);
			player1SelectionView.add(paladin);
			
			paladin.setBorderPainted(false);
		    paladin.setContentAreaFilled(false);
			ImageIcon paladinImage = new ImageIcon("Images/Paladin1.png");
			paladin.setHorizontalTextPosition(SwingConstants.CENTER);
			paladin.setIcon(paladinImage);
			
			JButton priest = new JButton();
			priest.setActionCommand("Priest");
			priest.setBounds(733, 275, 200, 270);
			priest.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			priest.addActionListener(this);
			player1SelectionView.add(priest);
			
			priest.setBorderPainted(false);
		    priest.setContentAreaFilled(false);
			ImageIcon priestImage = new ImageIcon("Images/Priest1.png");
			priest.setHorizontalTextPosition(SwingConstants.CENTER);
			priest.setIcon(priestImage);

			JButton warlock = new JButton();
			warlock.setActionCommand("Warlock");
			warlock.setBounds(963, 280, 200, 270);
			warlock.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			warlock.addActionListener(this);
			player1SelectionView.add(warlock);
			
			warlock.setBorderPainted(false);
		    warlock.setContentAreaFilled(false);
			ImageIcon warlockImage = new ImageIcon("Images/Warlock1.png");
			warlock.setHorizontalTextPosition(SwingConstants.CENTER);
			warlock.setIcon(warlockImage);

		    JLabel selectionBackground=new JLabel();
		    selectionBackground.setBounds(0, 0, 1200, 800);
		    selectionBackground.setIcon(new ImageIcon("Images/Player_selection_background.jpg"));
		    player1SelectionView.add(selectionBackground);
		
		   player1SelectionView.revalidate();
		   player1SelectionView.repaint();
			
			
			//----------------- player 2 selection view ------------------------------
			player2SelectionView=new player2View();
			
			JButton hunter2 = new JButton();
			hunter2.setActionCommand("Hunter");
			hunter2.setBounds(43, 280, 200, 270);
			hunter2.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			hunter2.addActionListener(this);
			player2SelectionView.add(hunter2);
			
			hunter2.setBorderPainted(false);
		    hunter2.setContentAreaFilled(false);
			ImageIcon rexxar2 = new ImageIcon("Images/Hunter1.png");
			hunter2.setHorizontalTextPosition(SwingConstants.CENTER);
			hunter2.setIcon(rexxar2);
			
			
			JButton mage2 = new JButton();
			mage2.setActionCommand("Mage");
			mage2.setBounds(273, 280, 200, 270);
			mage2.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			mage2.addActionListener(this);
			player2SelectionView.add(mage2);
			
			mage2.setBorderPainted(false);
		    mage2.setContentAreaFilled(false);
			ImageIcon mageImage2 = new ImageIcon("Images/Mage7.png");
			mage2.setHorizontalTextPosition(SwingConstants.CENTER);
			mage2.setIcon(mageImage2);
			
			JButton paladin2 = new JButton();
			paladin2.setActionCommand("Paladin");
			paladin2.setBounds(503, 280, 200, 270);
			paladin2.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			paladin2.addActionListener(this);
			player2SelectionView.add(paladin2);
			
			paladin2.setBorderPainted(false);
		    paladin2.setContentAreaFilled(false);
			ImageIcon paladinImage2 = new ImageIcon("Images/Paladin1.png");
			paladin2.setHorizontalTextPosition(SwingConstants.CENTER);
			paladin2.setIcon(paladinImage2);
			
			JButton priest2 = new JButton();
			priest2.setActionCommand("Priest");
			priest2.setBounds(733, 275, 200, 270);
			priest2.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			priest2.addActionListener(this);
			player2SelectionView.add(priest2);
			
			priest2.setBorderPainted(false);
		    priest2.setContentAreaFilled(false);
			ImageIcon priestImage2 = new ImageIcon("Images/Priest1.png");
			priest2.setHorizontalTextPosition(SwingConstants.CENTER);
			priest2.setIcon(priestImage2);
			
			JButton warlock2 = new JButton();
			warlock2.setActionCommand("Warlock");
			warlock2.setBounds(963, 280, 200, 270);
			warlock2.setFont(new Font("", Font.ROMAN_BASELINE, 30));
			warlock2.addActionListener(this);
			player2SelectionView.add(warlock2);
			
			warlock2.setBorderPainted(false);
		    warlock2.setContentAreaFilled(false);
			ImageIcon warlockImage2 = new ImageIcon("Images/Warlock1.png");
			warlock2.setHorizontalTextPosition(SwingConstants.CENTER);
			warlock2.setIcon(warlockImage2);


		    JLabel selectionBackground2=new JLabel();
		    selectionBackground2.setBounds(0, 0, 1200, 800);
		    selectionBackground2.setIcon(new ImageIcon("Images/Player_selection_background.jpg"));
		    player2SelectionView.add(selectionBackground2);
			
			
			
			player2SelectionView.revalidate();
			player2SelectionView.repaint();
			
			//actions Layout
			
			gameViewP1.getCurrentPlayerActions().setLayout(new GridLayout(2,1));
			JButton b1 = new JButton("USE HERO POWER");
			b1.addActionListener(this);
			b1.setFont(new Font("", Font.ITALIC, 30));
			gameViewP1.getCurrentPlayerActions().add(b1);
			
	    	JButton b2 = new JButton("END TURN");
	    	b2.addActionListener(this);
	    	b2.setFont(new Font("", Font.ITALIC, 30));
	    	gameViewP1.getCurrentPlayerActions().add(b2);
		    
	    	
	    	gameViewP2.getCurrentPlayerActions().setLayout(new GridLayout(2,1));
			JButton b3 = new JButton("USE HERO POWER");
			b3.addActionListener(this);
			b3.setFont(new Font("", Font.ITALIC, 30));
		    gameViewP2.getCurrentPlayerActions().add(b3);
			
	    	JButton b4 = new JButton("END TURN");
	    	b4.addActionListener(this);
	    	b4.setFont(new Font("", Font.ITALIC, 30));
	    	gameViewP2.getCurrentPlayerActions().add(b4);
	    	
	    	JLabel field1 = new JLabel();
			field1.setBounds(0, 0, 1600, 250);
			field1.setIcon(new ImageIcon("Images/Field1.png"));
			gameViewP1.getCurrentPlayerField().add(field1);
	    	
	}
	
	//------------------------Update Views----------------------------------
	
	
	public void updateGameView(Hero currentPlayer,Hero opponentPlayer, gameView currentGameView){
		//if(opponentPlayer.getCurrentHP()==0)
			//this.onGameOver();
		
		this.updateCurrentHeroInfo(currentPlayer,currentGameView);
		this.updateOpponentHeroInfo(opponentPlayer,currentGameView);
		
		this.updateCurrentHeroHand(currentPlayer,currentGameView);
		this.updateOpponentHeroHand(opponentPlayer,currentGameView);
		
		this.updateCurrentHeroField(currentPlayer,currentGameView);
		this.updateOpponentHeroField(opponentPlayer,currentGameView);
		
	}
	public void updateCurrentHeroInfo(Hero currentPlayer,gameView currentGameView){
		String s="";
		s+="<html><font size=+1> Name: "+currentPlayer.getName()+"<br />";
		s+="Current HP: "+currentPlayer.getCurrentHP()+"<br />";
		s+="Current Mana: "+currentPlayer.getCurrentManaCrystals()+"<br />";
		s+="Total Mana: "+currentPlayer.getTotalManaCrystals()+"<br />";
		s+="Cards in deck: "+currentPlayer.getDeck().size()+"<br />";
		currentGameView.getCurrentPlayerInfo().addActionListener(this);
		currentGameView.getCurrentPlayerInfo().setText(s);
		
	}
	public void updateOpponentHeroInfo(Hero opponentPlayer,gameView currentGameView){
		String s="";
		s+="<html><br><font size=+1>Name: " + opponentPlayer.getName() + "<br />"
	    + "Current HP: " + opponentPlayer.getCurrentHP() +"<br /> "
		+ "Current Mana Crystals: " + opponentPlayer.getCurrentManaCrystals() + "<br />"
	    + "Total Mana Crystals: " +
		opponentPlayer.getTotalManaCrystals() + "<br/"
	    + "Cards left in Hand: " + opponentPlayer.getHand().size() + "<br />"
	    + "Cards left in Deck: " + opponentPlayer.getDeck().size() + "</html>";
		currentGameView.getOpponentPlayerInfo().addActionListener(this);
		currentGameView.getOpponentPlayerInfo().setText(s);
	}
	public void updateCurrentHeroHand(Hero currentPlayer,gameView currentGameView){
		currentGameView.getCurrentPlayerHand().removeAll();
	    handButtons.clear();
		for(int i=0;i<currentPlayer.getHand().size();i++){
			JButton b = new JButton();
			b.setBackground(Color.black);
			b.addActionListener(this);
			if(currentPlayer.getHand().get(i) instanceof Minion ){
				updateMinionText(b,currentPlayer.getHand().get(i));
				//b.setForeground(Color.RED);
			}
			else{
				updateSpellText(b,currentPlayer.getHand().get(i));
			}
			handButtons.add(b);
			currentGameView.getCurrentPlayerHand().add(b);
			
		}
					
			
	}
	public static void updateMinionText(JButton b,Card c){
		Minion m=(Minion)c;
		String s="";
		s+="<html><font size=-2><font color=white>Name: "+m.getName()+"<br />";
		s+="Mana Cost: "+m.getManaCost()+"<br />";
		s+="Rarity: "+m.getRarity()+"<br />";
		s+="Attack: "+m.getAttack()+"<br />";
		s+="Current HP: "+m.getCurrentHP()+"<br />";
		if(m.isTaunt())
			s+="Taunt <br />";
		if(m.isDivine())
			s+="Divine <br />";
		if(!m.isSleeping())
			s+="In Charge <br />";
		b.setText(s);
	}
	public static void updateSpellText(JButton b,Card c){
		Spell sp=(Spell)c;
		String s="";
		s+="<html><font size=-2> <font color=white>Spell: "+sp.getName()+"<br />";
		s+="<html> Rarity: "+sp.getRarity()+"<br />";
		s+="<html> Mana cost: "+sp.getManaCost()+"<br />";
	    b.setText(s);
	}
	public void updateOpponentHeroHand(Hero opponentPlayer,gameView currentGameView){
		currentGameView.getOpponentPlayerHand().removeAll();
		for(int i=0;i<opponentPlayer.getHand().size();i++){
			    JButton b = new cardView();
				currentGameView.getOpponentPlayerHand().add(b);
		}
	}
	public void updateCurrentHeroField(Hero currentPlayer,gameView currentGameView){
		currentGameView.getCurrentPlayerField().removeAll();
		currentFieldButtons.clear();
		for(int i=0;i<currentPlayer.getField().size();i++){
			JButton b=new cardView();
			b.setIcon(new ImageIcon("Images/cardView2.jpg"));
			b.setPreferredSize(new Dimension(130, 170));
			b.setOpaque(true);
			b.addActionListener(this);
			updateMinionText(b, currentPlayer.getField().get(i));
			b.setForeground(Color.RED);
			currentFieldButtons.add(b);
			currentGameView.getCurrentPlayerField().add(b);
		}
	}
	public void updateOpponentHeroField(Hero opponentPlayer,gameView currentGameView){
		currentGameView.getOpponentPlayerField().removeAll();
		opponentFieldButtons.clear();
		for(int i=0;i<opponentPlayer.getField().size();i++){
			JButton b = new cardView();
			b.setIcon(new ImageIcon("Images/cardView2.jpg"));
			b.setPreferredSize(new Dimension(130, 170));
			b.addActionListener(this);
			updateMinionText(b, opponentPlayer.getField().get(i));
			currentGameView.getOpponentPlayerField().add(b);
			opponentFieldButtons.add(b);
		}
	}
	public void reset(){
		magePowerSelectedButton=null;
		priestPowerSelectedButton=null;
		minionTargetSpellSelected=null;
		leechingSpellSelected=null;
		heroTargetSpellSelected=null;
		divineSpiritSelectedButton=null;
		sealOfChampionsSelectedButton=null;
		selectedMinion=null;
	}
	
//-------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
				
		if(b.getActionCommand().equals("Start Game")){
			gameStart.setVisible(false);
			player1SelectionView.setVisible(true);
			return;
		}
		
		if(player1SelectionView.isVisible()){
		  if(b.getActionCommand().equals("Hunter")){
			player1SelectionView.setVisible(false);
			player2SelectionView.setVisible(true);
			try {
				player1Hero = new Hunter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				String s = "Sorry! An error occured";
				JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				String s = "Sorry! An error occured";
				JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
			}
			return;
			}
			else
			 if(b.getActionCommand().equals("Mage")){
				    player1SelectionView.setVisible(false);
					player2SelectionView.setVisible(true);
				try {
					player1Hero = new Mage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					String s = "Sorry! An error occured";
					JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					String s = "Sorry! An error occured";
					JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
				}
				return;
			}
			 else 
				 if(b.getActionCommand().equals("Paladin")){
					    player1SelectionView.setVisible(false);
						player2SelectionView.setVisible(true);
					 try {
						player1Hero = new Paladin();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
					 return;
				 }
				 else
					 if(b.getActionCommand().equals("Priest")){
						    player1SelectionView.setVisible(false);
							player2SelectionView.setVisible(true);
						 try {
							player1Hero=new Priest();
						} catch (IOException | CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! An error occured";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
						 return;
					 }
					 else
						 if(b.getActionCommand().equals("Warlock")){
							    player1SelectionView.setVisible(false);
								player2SelectionView.setVisible(true);
							 try {
								player1Hero=new Warlock();
							} catch (IOException | CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								String s = "Sorry! An error occured";
								JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
							}
							 return;
						 }
		}
		else{
	       if(player2SelectionView.isVisible()){		
			if(b.getActionCommand().equals("Hunter")){
				player2SelectionView.setVisible(false);
				
				try {
					player2Hero = new Hunter();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					String s = "Sorry! An error occured";
					JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					String s = "Sorry! An error occured";
					JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
				else
				 if(b.getActionCommand().equals("Mage")){
						player2SelectionView.setVisible(false);
						
					try {
						player2Hero = new Mage();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				 else 
					 if(b.getActionCommand().equals("Paladin")){
						    player2SelectionView.setVisible(false);
							
						 try {
							player2Hero = new Paladin();
						} catch (IOException | CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! An error occured";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
					 }
					 else
						 if(b.getActionCommand().equals("Priest")){
							    player2SelectionView.setVisible(false);
								
							 try {
								player2Hero = new Priest();
							} catch (IOException | CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								String s = "Sorry! An error occured";
								JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
							}
						 }
						 else
							 if(b.getActionCommand().equals("Warlock")){
								    player2SelectionView.setVisible(false);
								 try {
									player2Hero = new Warlock();
								} catch (IOException | CloneNotSupportedException e1) {
									// TODO Auto-generated catch block
									String s = "Sorry! An error occured";
									JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
								}
							 }
			
	       }
		}
		
		
		//------- Game ------------
		if(!(model instanceof Game)){
	
			try {
				model = new Game(player1Hero, player2Hero);
				model.setListener(this);
			} catch (FullHandException e1) {
				// TODO Auto-generated catch block
				String s = "Sorry! Your hand is full";
				JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				String s = "Sorry! An error occured";
				JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(player1Hero == model.getCurrentHero()){
				gameViewP1.setVisible(true);
		       updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
			}
			else{
				gameViewP2.setVisible(true);
				updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
			}
			
		}
			if(b.getActionCommand().equals("END TURN")){
			    	try {
						model.endTurn();
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
					reset();
					if(gameViewP1.isVisible()){
						gameViewP1.setVisible(false);
						gameViewP2.setVisible(true);
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
					else{
						gameViewP2.setVisible(false);
						gameViewP1.setVisible(true);
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
				    return;
			}
			
			else if(b.getActionCommand().equals("USE HERO POWER") && !(model.getCurrentHero() instanceof Mage)  
					&& !(model.getCurrentHero() instanceof Priest)){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to use your hero power?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						model.getCurrentHero().useHeroPower();
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You already used your hero power";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your field is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
			    }
			    else{
			    	reset();
			    	return;
			    }
				
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			
			else if(magePowerSelectedButton == null && b.getActionCommand().equals("USE HERO POWER")
					&& model.getCurrentHero() instanceof Mage){
				magePowerSelectedButton = b;
			}
			
			else if(opponentFieldButtons.contains(b) && magePowerSelectedButton != null){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to use your hero power on this minion?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						((Mage)(model.getCurrentHero())).useHeroPower(model.getOpponent().getField().get(opponentFieldButtons.indexOf(b)));
						magePowerSelectedButton = null;
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You already used your hero power";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your field is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
			    	if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
			    }
			    else{
			    	reset();
			    	return;
			    }
				
			}
			else if(magePowerSelectedButton != null && (b == gameViewP1.getOpponentPlayerInfo() || b == gameViewP2.getOpponentPlayerInfo())){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to use your hero power on your opponent hero?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						((Mage)(model.getCurrentHero())).useHeroPower(model.getOpponent());
						magePowerSelectedButton = null;
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You already used your hero power";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your field is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
					if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
			    }
			    else{
			    	reset();
			    	return;
			    }
			}
			
			else if(priestPowerSelectedButton == null && b.getActionCommand().equals("USE HERO POWER")
					&& model.getCurrentHero() instanceof Priest){
				priestPowerSelectedButton = b;
			}  
			else if(currentFieldButtons.contains(b) && priestPowerSelectedButton != null){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to use your hero power on this minion?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						((Priest)(model.getCurrentHero())).useHeroPower(model.getCurrentHero().getField().get(currentFieldButtons.indexOf(b)));
						priestPowerSelectedButton=null;
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You already used your hero power";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your field is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
					if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
			    }
			    else{
			    	reset();
			    	return;
			    }
			}
			else if(priestPowerSelectedButton != null && (b == gameViewP1.getCurrentPlayerInfo() || b == gameViewP2.getCurrentPlayerInfo())){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to use your hero power on your hero?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						((Priest)(model.getCurrentHero())).useHeroPower(model.getCurrentHero());
						priestPowerSelectedButton = null;
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You already used your hero power";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your hand is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Your field is full";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! An error occured";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
					if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
			    }
			    else{
			    	reset();
			    	return;
			    }
			}
			
			else if(handButtons.contains(b)){
				int index = handButtons.indexOf(b);
				if(model.getCurrentHero().getHand().get(index) instanceof Minion){
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to summon this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							model.getCurrentHero().playMinion((Minion)model.getCurrentHero().getHand().get(index));
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (FullFieldException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Your field is full";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
					    if(gameViewP1.isVisible()){
						    updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					    }
					    else{
						    updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					    }
				    }
				    else{
				    	reset();
				    	return;
				    }
				
		        }
				else{
					Spell s = (Spell)(model.getCurrentHero().getHand().get(index));
					if(s instanceof FieldSpell){
						String[] options = new String[] {"Yes", "No"};
					    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast this spell?", "Confirmation message",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, options, options[0]);
					    if(response == 0){
					    	try {
								model.getCurrentHero().castSpell((FieldSpell)s);
							} catch (NotYourTurnException e1) {
								// TODO Auto-generated catch block
								String s1 = "Sorry! Not your turn";
								JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								String s1 = "Sorry! You don't have enough mana";
								JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
							}
					    	reset();
							if(gameViewP1.isVisible()){
								updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
							}
							else{
								updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
							}
					    }
					    else{
					    	reset();
					    	return;
					    }
						
					}
					else if(s instanceof AOESpell){
						if(model.getCurrentHero() instanceof Mage){
							String[] options = new String[] {"Yes", "No"};
						    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast this spell?", "Confirmation message",
						        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						        null, options, options[0]);
						    if(response == 0){
						    	try {
									((Mage)(model.getCurrentHero())).castSpell((AOESpell)s, model.getOpponent().getField());
								} catch (NotYourTurnException e1) {
									// TODO Auto-generated catch block
									String s1 = "Sorry! Not your turn";
									JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
								} catch (NotEnoughManaException e1) {
									// TODO Auto-generated catch block
									String s1 = "Sorry! You don't have enough mana";
									JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
								}
						    	reset();
						    }
						    else{
						    	reset();
						    	return;
						    }
							
						}
						else{
							String[] options = new String[] {"Yes", "No"};
						    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast this spell?", "Confirmation message",
						        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						        null, options, options[0]);
						    if(response == 0){
						    	try {
									model.getCurrentHero().castSpell((AOESpell)s, model.getOpponent().getField());
								} catch (NotYourTurnException e1) {
									// TODO Auto-generated catch block
									String s1 = "Sorry! Not your turn";
									JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
								} catch (NotEnoughManaException e1) {
									// TODO Auto-generated catch block
									String s1 = "Sorry! You don't have enough mana";
									JOptionPane.showMessageDialog(new JFrame(), s1, "Error", JOptionPane.ERROR_MESSAGE);
								}
						    	reset();
						    }
						    else{
						    	reset();
						    	return;
						    }	
						}
						if(gameViewP1.isVisible()){
							updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
						}
						else{
							updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
						}
					}

				}
			}
			
			if(handButtons.contains(b) 
			   && model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof MinionTargetSpell
			   && minionTargetSpellSelected == null && !(model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof DivineSpirit) &&
			   !(model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof SealOfChampions)){
				minionTargetSpellSelected = b;
			}
			if(handButtons.contains(b) 
					   && model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof DivineSpirit
					   && divineSpiritSelectedButton == null ){
						divineSpiritSelectedButton = b;
			}
			if(handButtons.contains(b) 
					   && model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof SealOfChampions
					   && sealOfChampionsSelectedButton == null ){
						sealOfChampionsSelectedButton = b;
			}
			if(opponentFieldButtons.contains(b) &&  minionTargetSpellSelected != null && divineSpiritSelectedButton == null &&
					sealOfChampionsSelectedButton == null){
				if(!(model.getCurrentHero() instanceof Mage)){
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							model.getCurrentHero().castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(minionTargetSpellSelected)) ,model.getOpponent().getField().get(opponentFieldButtons.indexOf(b)));
							minionTargetSpellSelected = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				
				}
				else{
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							((Mage)(model.getCurrentHero())).castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(minionTargetSpellSelected)) ,model.getOpponent().getField().get(opponentFieldButtons.indexOf(b)));
							minionTargetSpellSelected = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			if(minionTargetSpellSelected != null && currentFieldButtons.contains(b)){
				String s = "You must cast this spell on your opponent field";
				JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
				reset();
			}
			
			if(currentFieldButtons.contains(b) &&  divineSpiritSelectedButton != null && minionTargetSpellSelected == null){
				if(!(model.getCurrentHero() instanceof Mage)){
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							model.getCurrentHero().castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(divineSpiritSelectedButton)) ,model.getCurrentHero().getField().get(currentFieldButtons.indexOf(b)));
							divineSpiritSelectedButton = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
				else{
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							((Mage)(model.getCurrentHero())).castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(divineSpiritSelectedButton)) ,model.getCurrentHero().getField().get(currentFieldButtons.indexOf(b)));
							divineSpiritSelectedButton = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			if(currentFieldButtons.contains(b) &&  sealOfChampionsSelectedButton != null && minionTargetSpellSelected == null &&
					divineSpiritSelectedButton == null){
				if(!(model.getCurrentHero() instanceof Mage)){
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							model.getCurrentHero().castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(sealOfChampionsSelectedButton)) ,model.getCurrentHero().getField().get(currentFieldButtons.indexOf(b)));
							sealOfChampionsSelectedButton = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't  attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
				else{
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							((Mage)(model.getCurrentHero())).castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(sealOfChampionsSelectedButton)) ,model.getCurrentHero().getField().get(currentFieldButtons.indexOf(b)));
							sealOfChampionsSelectedButton = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You can't attack this target";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }	
				}
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			if(handButtons.contains(b) 
			   && model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof LeechingSpell
			   && leechingSpellSelected == null){
						leechingSpellSelected = b;
			}
			if(opponentFieldButtons.contains(b) &&  leechingSpellSelected != null && minionTargetSpellSelected == null){
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on this minion?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						model.getCurrentHero().castSpell((LeechingSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(leechingSpellSelected)) ,model.getOpponent().getField().get(opponentFieldButtons.indexOf(b)));
						leechingSpellSelected = null;
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You don't have enough mana";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
					if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
			    }
			    else{
			    	reset();
			    	return;
			    }
					
			}
			if(handButtons.contains(b) 
					  && model.getCurrentHero().getHand().get(handButtons.indexOf(b)) instanceof HeroTargetSpell
					  && heroTargetSpellSelected == null){
			heroTargetSpellSelected = b;
			}
			
			if((b == gameViewP1.getOpponentPlayerInfo() || b == gameViewP2.getOpponentPlayerInfo()) && heroTargetSpellSelected != null){
				if(model.getCurrentHero() instanceof Mage){
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on your opponent hero?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							((Mage)(model.getCurrentHero())).castSpell((HeroTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(heroTargetSpellSelected)),model.getOpponent());
							heroTargetSpellSelected = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
				else{
					String[] options = new String[] {"Yes", "No"};
				    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to cast the spell on your opponent hero?", "Confirmation message",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				    if(response == 0){
				    	try {
							model.getCurrentHero().castSpell((HeroTargetSpell)model.getCurrentHero().getHand().get(handButtons.indexOf(heroTargetSpellSelected)),model.getOpponent());
							heroTargetSpellSelected = null;
						} catch (NotYourTurnException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! Not your turn";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							String s = "Sorry! You don't have enough mana";
							JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
						}
				    	reset();
				    }
				    else{
				    	reset();
				    	return;
				    }
				}
			
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			
			if(selectedMinion == null && currentFieldButtons.contains(b)){
				selectedMinion = b;
				currentFieldIndex = currentFieldButtons.indexOf(b);
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
			
			if(selectedMinion != null && opponentFieldButtons.contains(b)){
				targetIndex = opponentFieldButtons.indexOf(b);
				String[] options = new String[] {"Yes", "No"};
			    int response = JOptionPane.showOptionDialog(null, "Are you sure you want to attack this minion?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			    if(response == 0){
			    	try {
						model.getCurrentHero().attackWithMinion(model.getCurrentHero().getField().get(currentFieldIndex), model.getOpponent().getField().get(targetIndex));
						selectedMinion = null;
					} catch (CannotAttackException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You can't attack";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (TauntBypassException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You must attack the taunt minion first";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You can't attack this target";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotSummonedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! This minion is not summoned yet";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
			    }
			    else{
			    	reset();
			    	return;
			    }	
				if(gameViewP1.isVisible()){
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
				}
				else{
					updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
				}
			}
					
			if(selectedMinion != null && (b == gameViewP2.getOpponentPlayerInfo() ||
					b == gameViewP1.getOpponentPlayerInfo())){
				String[] options = new String[] {"Yes", "No"};
			     int response = JOptionPane.showOptionDialog(null, "Are you sure you want to attack your opponent hero?", "Confirmation message",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
				
			    if(response == 0){
			    	try {
			    		selectedMinion = null;
						model.getCurrentHero().attackWithMinion(model.getCurrentHero().getField().get(currentFieldIndex), model.getOpponent());
						
					} catch (CannotAttackException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You can't attack";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! Not your turn";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (TauntBypassException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You must attack the taunt minion first";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotSummonedException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! This minion is not summoned yet";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						// TODO Auto-generated catch block
						String s = "Sorry! You can't attack this target";
						JOptionPane.showMessageDialog(new JFrame(), s, "Error", JOptionPane.ERROR_MESSAGE);
					}
			    	reset();
					if(gameViewP1.isVisible()){
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP1);
					}
					else{
						updateGameView(model.getCurrentHero(), model.getOpponent(), gameViewP2);
					}
					return;
			    }
			    else{
			    	reset();
			    	return;
			    }	
			}
		   gameViewP1.revalidate();
		   gameViewP1.repaint();
			
		   gameViewP2.revalidate();
		   gameViewP2.repaint();	
			
	}
	
	@Override
	public void onGameOver() {
		String s;
		if(gameViewP1.isVisible()){
		         s = "Winner: PLAYER 1!!! Congratulations!";
		}
		else
			s = "Winner: PLAYER 2!!! Congratulations!";
		JOptionPane.showMessageDialog(new JFrame(), s, "Winner", JOptionPane.ERROR_MESSAGE);
		gameViewP1.dispose();
		gameViewP2.dispose();
		System.exit(0);
		
	}
	
	public static void main(String[] args){
		new Controller();
	}

}
