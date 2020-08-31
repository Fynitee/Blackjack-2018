/*
 * The BlackJack class is the main class of the entire program - The game of BlackJack consists of a player and dealer,
 * of which they are both given two cards. The player and dealer must count their cards to get as close to 21 without
 * exceeding 21. If the dealer or player goes over 21, they "bust" and automatically lose the round. The player is allowed
 * to hit (to draw another card), stand (to stay with his/her set of cards), or fold (to automatically lose the round).
 * 
 * The cards of BlackJack value as follows: The face cards (King, Queen, Jack) value at 10 points, the Ace cards value at
 * 1 or 11 (at which the best choice is automatically detected by the game), and the remaining cards (2-9) value at the
 * points as seen on the card. Initially, the player is only revealed 1/2 of the dealer's card at which the player must
 * decide if they would like to hit, stand, or fold. The winner of the round is determined by counting the total value
 * of the cards for the dealer and the player. When the round is over, the player may click on the deal button to begin 
 * a new round.
 */
import java.awt.*; // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interface

import javax.swing.*; // Using Swing's components and containers

@SuppressWarnings("serial")
public class BlackJack extends JFrame implements ActionListener {

	// Variable declarations.
	public static String hand;
	public static int playerValue, dealerValue;
	public static int round = 1;
	private int dealerHitCount = 0;
	private int playerHitCount = 0;
	private int dealerWins = 0, playerWins = 0, ties = 0;
	private boolean roundOver = false;
	private boolean folded = false;
	private boolean playerHit1, playerHit2, playerHit3, playerHit4, playerHit5, playerHit6, playerHit7, playerHit8, playerHit9 = false;
	private boolean dealerHit1, dealerHit2 = false;

	// Instantiation of ImageIcons for the hit, stand, fold, and deal buttons.
	private static ImageIcon bHit = new ImageIcon("imgBlackJack\\bHit.png");
	private static ImageIcon bStand = new ImageIcon("imgBlackJack\\bStand.png");
	private static ImageIcon bFold = new ImageIcon("imgBlackJack\\bFold.png");
	private static ImageIcon bDeal = new ImageIcon("imgBlackJack\\bDeal.png");

	// Instantiation of the game's JPanel and hit, stand, fold, and deal buttons.
	private static JPanel game = new JPanel();
	private static JButton hit = new JButton(bHit);
	private static JButton stand = new JButton(bStand);
	private static JButton fold = new JButton(bFold);
	private static JButton deal = new JButton(bDeal);
	
	// Declaration of the custom drawing canvas - the inner class that extends the game's JPanel.
	private DrawCanvas canvas;
		
	// Instantiation of images for all 52 cards and a hidden card for the dealer.
	Image hidden = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\Hidden.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);	
	Image dA = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\AceDiamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image cA = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\AceClubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image hA = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\AceHearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image sA = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\AceSpades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d2 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\2Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c2 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\2Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h2 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\2Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s2 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\2Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d3 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\3Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c3 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\3Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h3 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\3Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s3 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\3Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d4 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\4Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c4 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\4Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h4 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\4Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s4 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\4Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d5 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\5Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c5 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\5Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h5 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\5Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s5 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\5Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d6 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\6Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c6 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\6Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h6 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\6Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s6 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\6Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d7 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\7Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c7 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\7Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h7 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\7Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s7 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\7Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d8 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\8Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c8 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\8Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h8 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\8Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s8 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\8Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d9 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\9Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c9 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\9Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h9 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\9Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s9 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\9Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image d10 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\10Diamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image c10 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\10Clubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image h10 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\10Hearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image s10 = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\10Spades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image dJ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\JackDiamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image cJ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\JackClubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image hJ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\JackHearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image sJ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\JackSpades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image dQ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\QueenDiamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image cQ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\QueenClubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image hQ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\QueenHearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image sQ = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\QueenSpades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image dK = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\KingDiamonds.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image cK = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\KingClubs.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image hK = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\KingHearts.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
	Image sK = Toolkit.getDefaultToolkit().getImage("imgBlackJack\\KingSpades.png").getScaledInstance(118, 182, Image.SCALE_DEFAULT);
		
	// Initializing the deck of cards and player cards - The player can only draw a maximum of 11 cards before exceeding 21 in the game.
	CardsDeck playingDeck = new CardsDeck();
	CardsDeck playerCard1 = new CardsDeck();
	CardsDeck playerCard2 = new CardsDeck();
	CardsDeck playerCard3 = new CardsDeck();
	CardsDeck playerCard4 = new CardsDeck();
	CardsDeck playerCard5 = new CardsDeck();
	CardsDeck playerCard6 = new CardsDeck();
	CardsDeck playerCard7 = new CardsDeck();
	CardsDeck playerCard8 = new CardsDeck();
	CardsDeck playerCard9 = new CardsDeck();
	CardsDeck playerCard10 = new CardsDeck();
	CardsDeck playerCard11 = new CardsDeck();

	// Initializing the dealer's cards - The dealer is only allowed to only hit twice.
	CardsDeck dealerCard1 = new CardsDeck();
	CardsDeck dealerCard2 = new CardsDeck();
	CardsDeck dealerCard3 = new CardsDeck();
	CardsDeck dealerCard4 = new CardsDeck();
	
	/**
	 * Constructor
	 * pre: none
	 * post: The BlackJack game runs according to how it is played.
	 */
	public BlackJack() {
		// Setting JFrame settings for the game screen.
		setTitle("BlackJack");
		setLocation(330, 90);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
		
		// Configuring the hit, stand, fold, and deal buttons - the deal button is set to false as the cards have already been distributed.
		hit.setPreferredSize(new Dimension(106, 39));
		stand.setPreferredSize(new Dimension(158, 39));
		fold.setPreferredSize(new Dimension(139, 39));
		deal.setPreferredSize(new Dimension(141, 39));
		deal.setEnabled(false);
		
		// Adding the hit, stand, fold, and deal buttons to the JFrame.
		game.add(hit);
		game.add(stand);
		game.add(fold);
		game.add(deal);
		
		// Adding ActionListeners for the hit, stand, fold, and deal buttons.
		hit.addActionListener(this);
		stand.addActionListener(this);
		fold.addActionListener(this);
		deal.addActionListener(this);

		// The deck of cards is created.
		playingDeck.createDeck();
		
		// The player draws 2 cards initially - this is the first two cards the player will see.
		playerCard1.draw(playingDeck);
		playerCard2.draw(playingDeck);
		
		// The dealer draws 2 cards initially - the player will only see the first card.
		dealerCard1.draw(playingDeck);
		dealerCard2.draw(playingDeck);

		// The player's and dealer's current value is shown on the screen.
		playerValue = playerCard1.totalValue() + playerCard2.totalValue();
		dealerValue = dealerCard1.totalValue();

		// The extra cards that are used for the dealer's or player's hit are drawn. They only appear if the dealer or player chooses to hit.
		dealerCard3.draw(playingDeck);
		dealerCard4.draw(playingDeck);
		playerCard3.draw(playingDeck);
		playerCard4.draw(playingDeck);
		playerCard5.draw(playingDeck);
		playerCard6.draw(playingDeck);
		playerCard7.draw(playingDeck);
		playerCard8.draw(playingDeck);
		playerCard9.draw(playingDeck);
		playerCard10.draw(playingDeck);
		playerCard11.draw(playingDeck);
		
		// The canvas is initialized and set to the size of the screen.
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(700, 550));
		
		// Adding the canvas and game's JPanel onto the JFrame while using the BorderLayout.
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(game, BorderLayout.SOUTH);
		
		// Setting JFrame settings for the game screen.
		pack();
		setVisible(true);
		requestFocus();
	}

	/**
	 * Determines if the dealer wants to hit and returns the value of his/her hand.
	 * pre: The dealer's cards have been initialized.
	 * post: The dealer's hand value is returned.
	 */
	private int dealerAmount() {
		dealerValue = dealerCard1.totalValue() + dealerCard2.totalValue();
		if (dealerValue < 17) { // If the dealer's value is less than 17, the dealer is forced to hit once.
			dealerHit1 = true;
			dealerValue = dealerCard1.totalValue() + dealerCard2.totalValue() + dealerCard3.totalValue();
			dealerHitCount += 1;
		}
		if (dealerValue < 17) { // If the dealer's value is less than 17 after the initial hit, the dealer has to hit again.
			dealerHit2 = true;
			dealerValue = dealerCard1.totalValue() + dealerCard2.totalValue() + dealerCard3.totalValue() + dealerCard4.totalValue();
			dealerHitCount += 1;
		}
		return(dealerValue);
	}
	
	/**
	 * Determines if the player has hit and returns the value of his/her hand.
	 * pre: The player's cards have been initialized.
	 * post: The player's hand value is returned.
	 */
	private void hit() {
		if (playerHitCount == 1) {
			// If the player has chosen to hit once, the third card is added to their total value.
			playerHit1 = true;
			playerValue += playerCard3.totalValue();
		} else if (playerHitCount == 2) {	
			// If the player has chosen to hit 2 times, the fourth card is added to their total value.
			playerHit2 = true;
			playerValue += playerCard4.totalValue();
		} else if (playerHitCount == 3) {
			// If the player has chosen to hit 3 times, the fifth card is added to their total value.
			playerHit3 = true;
			playerValue += playerCard5.totalValue();
		} else if (playerHitCount == 4) {
			// If the player has chosen to hit 4 times, the sixth card is added to their total value.
			playerHit4 = true;
			playerValue += playerCard6.totalValue();
		} else if (playerHitCount == 5) {
			// If the player has chosen to hit 5 times, the seventh card is added to their total value.
			playerHit5 = true;
			playerValue += playerCard7.totalValue();
		} else if (playerHitCount == 6) {
			// If the player has chosen to hit 6 times, the eighth card is added to their total value.
			playerHit6 = true;
			playerValue += playerCard8.totalValue();
		} else if (playerHitCount == 7) {
			// If the player has chosen to hit 7 times, the ninth card is added to their total value.
			playerHit7 = true;
			playerValue += playerCard9.totalValue();
		} else if (playerHitCount == 8) {
			// If the player has chosen to hit 8 times, the tenth card is added to their total value.
			playerHit8 = true;
			playerValue += playerCard10.totalValue();
		} else if (playerHitCount == 9) {
			// If the player has chosen to hit 9 times, the eleventh card is added to their total value.
			playerHit9 = true;
			playerValue += playerCard11.totalValue();
		} 
	}
	
	/**
	 * Determines the winner of the round by returning an integer.
	 * pre: The round has ended.
	 * post: An integer has been returned in order to determine the winner.
	 */
	private int checkWinner() {
		if (playerValue > 21) { // Player busts when their value exceeds 21.
			return(1);
		} else if (dealerValue > 21) { // Dealer busts when their value exceeds 21.
			return(2);
		} else if (playerValue == dealerValue) { // Ties when the player and dealer have equivalent values.
			return(3);
		} else if (playerValue > dealerValue) { // Player wins when their value is higher than the dealer's without busting.
			return(4);
		} else { // Dealer wins when their value is higher than the player without busting.
			return(5);
		}
    }
	
	/**
	 * Determines what button the player has pushed.
	 * pre: A button is pushed.
	 * post: The buttons' and round's configurations are adjusted according to the button that was pushed.
	 */
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == hit) { // If hit was selected by the player.
			// The player's hit count increases by one and the hit method is called upon.
			playerHitCount += 1;			
			hit();
			
			// The hit, stand, and fold buttons are enabled while as deal is disabled.
			hit.setEnabled(true);
			stand.setEnabled(true);
			fold.setEnabled(true);		
			deal.setEnabled(false);
			
			if (playerValue > 21) { // If the player's value exceeds 21, s/he busts,
				// The round ends and the dealer's two cards are revealed.
				roundOver = true;
				dealerWins += 1;
				dealerValue = dealerCard1.totalValue() + dealerCard2.totalValue();
				checkWinner();
				
				// The hit, stand, and fold buttons are disabled while as deal is enabled.
				hit.setEnabled(false);
				stand.setEnabled(false);
				fold.setEnabled(false);		
				deal.setEnabled(true);
			}
			canvas.repaint();
			requestFocus(); // change the focus to JFrame to receive KeyEvent
		}
		if (e.getSource() == stand) { // If stand was selected by the player.
			// The round ends and the dealer can hit if s/he chooses to.
			roundOver = true;
			dealerAmount();
			
			// The winner is determined.
			if (checkWinner() == 1) { // If the dealer has won.
				dealerWins += 1;
			} if (checkWinner() == 2) {	// If the player has won.	
				playerWins += 1;
			} if (checkWinner() == 3) {	// If the dealer and player tied.
				ties += 1;
			} if (checkWinner() == 4) {	// If the player has won.
				playerWins += 1;
			} if (checkWinner() == 5) {	// If the dealer has won.
				dealerWins += 1;
			}
			
			// The hit, stand, and fold buttons are disabled while as deal is enabled.
			hit.setEnabled(false);
			stand.setEnabled(false);
			fold.setEnabled(false);		
			deal.setEnabled(true);
			
			canvas.repaint();
			requestFocus(); // change the focus to JFrame to receive KeyEvent
		}
		if (e.getSource() == fold) { // If fold was selected by the player.
			// The round ends as the player has folded, and the dealer's final value is only of the first card.
			roundOver = true;
			folded = true;
			dealerWins += 1;
			dealerValue = dealerCard1.totalValue();
			
			// The hit, stand, and fold buttons are disabled while as deal is enabled.
			hit.setEnabled(false);
			stand.setEnabled(false);
			fold.setEnabled(false);		
			deal.setEnabled(true);
			
			canvas.repaint();
			requestFocus(); // change the focus to JFrame to receive KeyEvent
		}
		if (e.getSource() == deal) { // If deal was selected by the player.
			// The rounds are completely reset along with their respective variables.
			roundOver = false;
			folded = false;
			
			// Round counter increases by 1.
			round += 1;
			
			// The dealer's hit counts are reset.
			dealerHitCount = 0;
			dealerHit1 = false;
			dealerHit2 = false;
			
			// The player's hit counts are reset.
			playerHitCount = 0;
			playerHit1 = false;
			playerHit2 = false;
			playerHit3 = false;
			playerHit4 = false;
			playerHit5 = false;
			playerHit6 = false;
			playerHit7 = false;
			playerHit8 = false;
			playerHit9 = false;
			
			// The hit, stand, and fold buttons are enabled while as deal is disabled.
			hit.setEnabled(true);
			stand.setEnabled(true);
			fold.setEnabled(true);		
			deal.setEnabled(false);
					
			// The player draws two new cards as their initial cards.
			playerCard1.draw(playingDeck);
			playerCard2.draw(playingDeck);
			
			// The dealer draws two new cards as their initial cards - the player only sees one of them.
			dealerCard1.draw(playingDeck);
			dealerCard2.draw(playingDeck);
			
			// The player's value and dealer's value is recalculated for the new round.
			playerValue = playerCard1.totalValue() + playerCard2.totalValue();
			dealerValue = dealerCard1.totalValue();
			
			// The player's and dealer's 'hit' cards are redrawn. They only appear when the player or dealer chooses to hit.
			dealerCard3.draw(playingDeck);
			dealerCard4.draw(playingDeck);
			playerCard3.draw(playingDeck);
			playerCard4.draw(playingDeck);
			playerCard5.draw(playingDeck);
			playerCard6.draw(playingDeck);
			playerCard7.draw(playingDeck);
			playerCard8.draw(playingDeck);
			playerCard9.draw(playingDeck);
			playerCard10.draw(playingDeck);
			playerCard11.draw(playingDeck);
			
			canvas.repaint();
			requestFocus(); // change the focus to JFrame to receive KeyEvent
		}
	}
	
	/**
	 * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
	 */
	class DrawCanvas extends JPanel {
		@Override
		
		public void paintComponent(Graphics g) { // Configuring paint components and graphics.
			super.paintComponent(g);			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				
			// Instantiating colors.
			Color green = new Color(85, 234, 94);
			Color yellow = new Color(254, 233, 84);
			Color blue = new Color(0, 12, 255);
			Color violet = new Color(106,90,205);
		    
			// Instantiating fonts.
	        Font roundFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 100);
	        Font value = new Font("Tw Cen MT Bold Italic", Font.PLAIN, 30);
			Font number = new Font("Tw Cen MT Bold Italic", Font.PLAIN, 70);
			Font winner = new Font("Bernard MT Condensed", Font.PLAIN, 70);
			Font statement = new Font("Bernard MT Condensed", Font.PLAIN, 30);
			
			// Instantiating and painting a gradient background for the game.
			GradientPaint gp = new GradientPaint(0, 0, green, 0, 275, yellow);
			g2.setPaint(gp);
	        g2.fillRect(0, 0, 700, 550);
	        
	        // Setting the color and font for the number of rounds.
			g2.setFont(roundFont);
			g2.setColor(blue);
					
			// Calculating the number of digits of the round, player value, and dealer value.
			int lengthR = String.valueOf(round).length();
			int lengthP = String.valueOf(playerValue).length();
			int lengthD = String.valueOf(dealerValue).length();
			
			if (lengthR == 1 && roundOver != true) { // If the round has one digit.
				g2.drawString("ROUND " + round, 130, 308);
			} else if (lengthR >= 1 && roundOver != true) { // If the round has two digits.
				g2.drawString("ROUND " + round, 100, 308);
			}
	        
			// Setting the color, font, and text for the player and dealer values.
			g2.setFont(value);
			g2.setColor(blue);
			g2.drawString("YOUR VALUE: ", 50, 393);
			g2.drawString("DEALER VALUE: ", 50, 60);
			
			// Setting the font for the player and dealer values and adjusting the text according to the number of digits.
			g2.setFont(number);
			if (lengthP == 1) { // If the player value has one digit.
				g2.drawString(" " + playerValue, 95, 468);
			} else { // If the player value has more than one digit.
				g2.drawString(" " + playerValue, 75, 468);
			}
			
			if (lengthD == 1) { // If the dealer value has one digit.
				g2.drawString("" + dealerValue, 115, 135);
			} else { // If the dealer value has more than one digit.
				g2.drawString("" + dealerValue, 95, 135);
			}
			
			// The dealer's hidden card is concealed if the round isn't over and if the player folded.
			if (roundOver != true || roundOver == true && folded == true) {
				g.drawImage(hidden, 290, 22, this);
			}
			
			// Declaring the initial position of the player's and dealer's cards.
			int xPosPlayer = 260;
			int xPosDealer = 260;
						
			// PLAYER/DEALER - If the 2 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("2D") || playerCard2.toString().equals("2D") || playerCard3.toString().equals("2D") && playerHit1 == true || playerCard4.toString().equals("2D") && playerHit2 == true  || playerCard5.toString().equals("2D") && playerHit3 == true || playerCard6.toString().equals("2D") && playerHit4 == true || playerCard7.toString().equals("2D") && playerHit5 == true || playerCard8.toString().equals("2D") && playerHit6 == true || playerCard9.toString().equals("2D") && playerHit7 == true || playerCard10.toString().equals("2D") && playerHit8 == true || playerCard11.toString().equals("2D") && playerHit9 == true) {
				g.drawImage(d2, xPosPlayer, 345, this);
				xPosPlayer += 30;
			} 
			if (dealerCard1.toString().equals("2D") || dealerCard2.toString().equals("2D") && roundOver == true && folded != true  || dealerCard3.toString().equals("2D") && dealerHit1 == true || dealerCard4.toString().equals("2D") && dealerHit2 == true) {
				g.drawImage(d2, xPosDealer, 22, this);
				xPosDealer += 30;
			} 		
			// PLAYER/DEALER - If the 2 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("2C") || playerCard2.toString().equals("2C") || playerCard3.toString().equals("2C") && playerHit1 == true || playerCard4.toString().equals("2C") && playerHit2 == true  || playerCard5.toString().equals("2C") && playerHit3 == true || playerCard6.toString().equals("2C") && playerHit4 == true || playerCard7.toString().equals("2C") && playerHit5 == true || playerCard8.toString().equals("2C") && playerHit6 == true || playerCard9.toString().equals("2C") && playerHit7 == true || playerCard10.toString().equals("2C") && playerHit8 == true || playerCard11.toString().equals("2C") && playerHit9 == true) {
				g.drawImage(c2, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("2C") || dealerCard2.toString().equals("2C") && roundOver == true && folded != true  || dealerCard3.toString().equals("2C") && dealerHit1 == true || dealerCard4.toString().equals("2C") && dealerHit2 == true) {
				g.drawImage(c2, xPosDealer, 22, this);
				xPosDealer += 30;
			}		
			// PLAYER/DEALER - If the 2 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("2H") || playerCard2.toString().equals("2H") || playerCard3.toString().equals("2H") && playerHit1 == true || playerCard4.toString().equals("2H") && playerHit2 == true  || playerCard5.toString().equals("2H") && playerHit3 == true || playerCard6.toString().equals("2H") && playerHit4 == true || playerCard7.toString().equals("2H") && playerHit5 == true || playerCard8.toString().equals("2H") && playerHit6 == true || playerCard9.toString().equals("2H") && playerHit7 == true || playerCard10.toString().equals("2H") && playerHit8 == true || playerCard11.toString().equals("2H") && playerHit9 == true) {
				g.drawImage(h2, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("2H") || dealerCard2.toString().equals("2H") && roundOver == true && folded != true || dealerCard3.toString().equals("2H") && dealerHit1 == true || dealerCard4.toString().equals("2H") && dealerHit2 == true) {
				g.drawImage(h2, xPosDealer, 22, this);
				xPosDealer += 30;
			}			
			// PLAYER/DEALER - If the 2 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("2S") || playerCard2.toString().equals("2S") || playerCard3.toString().equals("2S") && playerHit1 == true || playerCard4.toString().equals("2S") && playerHit2 == true  || playerCard5.toString().equals("2S") && playerHit3 == true || playerCard6.toString().equals("2S") && playerHit4 == true || playerCard7.toString().equals("2S") && playerHit5 == true || playerCard8.toString().equals("2S") && playerHit6 == true || playerCard9.toString().equals("2S") && playerHit7 == true || playerCard10.toString().equals("2S") && playerHit8 == true || playerCard11.toString().equals("2S") && playerHit9 == true) {
				g.drawImage(s2, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("2S") || dealerCard2.toString().equals("2S") && roundOver == true && folded != true || dealerCard3.toString().equals("2S") && dealerHit1 == true || dealerCard4.toString().equals("2S") && dealerHit2 == true) {
				g.drawImage(s2, xPosDealer, 22, this);
				xPosDealer += 30;
			}		
			// PLAYER/DEALER - If the 3 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("3D") || playerCard2.toString().equals("3D") || playerCard3.toString().equals("3D") && playerHit1 == true || playerCard4.toString().equals("3D") && playerHit2 == true  || playerCard5.toString().equals("3D") && playerHit3 == true || playerCard6.toString().equals("3D") && playerHit4 == true || playerCard7.toString().equals("3D") && playerHit5 == true || playerCard8.toString().equals("3D") && playerHit6 == true || playerCard9.toString().equals("3D") && playerHit7 == true || playerCard10.toString().equals("3D") && playerHit8 == true || playerCard11.toString().equals("3D") && playerHit9 == true) {
				g.drawImage(d3, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("3D") || dealerCard2.toString().equals("3D") && roundOver == true && folded != true || dealerCard3.toString().equals("3D") && dealerHit1 == true || dealerCard4.toString().equals("3D") && dealerHit2 == true) {
				g.drawImage(d3, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 3 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("3C") || playerCard2.toString().equals("3C") || playerCard3.toString().equals("3C") && playerHit1 == true || playerCard4.toString().equals("3C") && playerHit2 == true || playerCard5.toString().equals("3C") && playerHit3 == true || playerCard6.toString().equals("3C") && playerHit4 == true || playerCard7.toString().equals("3C") && playerHit5 == true || playerCard8.toString().equals("3C") && playerHit6 == true || playerCard9.toString().equals("3C") && playerHit7 == true || playerCard10.toString().equals("4D3C") && playerHit8 == true || playerCard11.toString().equals("3C") && playerHit9 == true) {
				g.drawImage(c3, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("3C") || dealerCard2.toString().equals("3C") && roundOver == true && folded != true || dealerCard3.toString().equals("3C") && dealerHit1 == true || dealerCard4.toString().equals("3C") && dealerHit2 == true) {
				g.drawImage(c3, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 3 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("3H") || playerCard2.toString().equals("3H") || playerCard3.toString().equals("3H") && playerHit1 == true || playerCard4.toString().equals("3H") && playerHit2 == true  || playerCard5.toString().equals("3H") && playerHit3 == true || playerCard6.toString().equals("3H") && playerHit4 == true || playerCard7.toString().equals("3H") && playerHit5 == true || playerCard8.toString().equals("43HD") && playerHit6 == true || playerCard9.toString().equals("3H") && playerHit7 == true || playerCard10.toString().equals("3H") && playerHit8 == true || playerCard11.toString().equals("3H") && playerHit9 == true) {
				g.drawImage(h3, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("3H") || dealerCard2.toString().equals("3H") && roundOver == true && folded != true || dealerCard3.toString().equals("3H") && dealerHit1 == true || dealerCard4.toString().equals("3H") && dealerHit2 == true) {
				g.drawImage(h3, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 3 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("3S") || playerCard2.toString().equals("3S") || playerCard3.toString().equals("3S") && playerHit1 == true || playerCard4.toString().equals("3S") && playerHit2 == true  || playerCard5.toString().equals("3S") && playerHit3 == true || playerCard6.toString().equals("3S") && playerHit4 == true || playerCard7.toString().equals("3S") && playerHit5 == true || playerCard8.toString().equals("3S") && playerHit6 == true || playerCard9.toString().equals("3S") && playerHit7 == true || playerCard10.toString().equals("3S") && playerHit8 == true || playerCard11.toString().equals("3S") && playerHit9 == true) {
				g.drawImage(s3, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("3S") || dealerCard2.toString().equals("3S") && roundOver == true && folded != true || dealerCard3.toString().equals("3S") && dealerHit1 == true || dealerCard4.toString().equals("3S") && dealerHit2 == true) {
				g.drawImage(s3, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 4 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("4D") || playerCard2.toString().equals("4D") || playerCard3.toString().equals("4D") && playerHit1 == true || playerCard4.toString().equals("4D") && playerHit2 == true || playerCard5.toString().equals("4D") && playerHit3 == true || playerCard6.toString().equals("4D") && playerHit4 == true || playerCard7.toString().equals("4D") && playerHit5 == true || playerCard8.toString().equals("4D") && playerHit6 == true || playerCard9.toString().equals("4D") && playerHit7 == true || playerCard10.toString().equals("4D") && playerHit8 == true || playerCard11.toString().equals("4D") && playerHit9 == true) {
				g.drawImage(d4, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("4D") || dealerCard2.toString().equals("4D") && roundOver == true && folded != true || dealerCard3.toString().equals("4D") && dealerHit1 == true || dealerCard4.toString().equals("4D") && dealerHit2 == true) {
				g.drawImage(d4, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 4 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("4C") || playerCard2.toString().equals("4C") || playerCard3.toString().equals("4C") && playerHit1 == true || playerCard4.toString().equals("4C") && playerHit2 == true || playerCard5.toString().equals("4C") && playerHit3 == true || playerCard6.toString().equals("4C") && playerHit4 == true || playerCard7.toString().equals("4C") && playerHit5 == true || playerCard8.toString().equals("4C") && playerHit6 == true || playerCard9.toString().equals("4C") && playerHit7 == true || playerCard10.toString().equals("4C") && playerHit8 == true || playerCard11.toString().equals("4C") && playerHit9 == true) {
				g.drawImage(c4, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("4C") || dealerCard2.toString().equals("4C") && roundOver == true && folded != true  || dealerCard3.toString().equals("4C") && dealerHit1 == true || dealerCard4.toString().equals("4C") && dealerHit2 == true) {
				g.drawImage(c4, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 4 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("4H") || playerCard2.toString().equals("4H") || playerCard3.toString().equals("4H") && playerHit1 == true || playerCard4.toString().equals("4H") && playerHit2 == true || playerCard5.toString().equals("4H") && playerHit3 == true || playerCard6.toString().equals("4H") && playerHit4 == true || playerCard7.toString().equals("4H") && playerHit5 == true || playerCard8.toString().equals("4H") && playerHit6 == true || playerCard9.toString().equals("4H") && playerHit7 == true || playerCard10.toString().equals("4H") && playerHit8 == true || playerCard11.toString().equals("4H") && playerHit9 == true) {
				g.drawImage(h4, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("4H") || dealerCard2.toString().equals("4H") && roundOver == true && folded != true || dealerCard3.toString().equals("4H") && dealerHit1 == true || dealerCard4.toString().equals("4H") && dealerHit2 == true) {
				g.drawImage(h4, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 4 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("4S") || playerCard2.toString().equals("4S") || playerCard3.toString().equals("4S") && playerHit1 == true || playerCard4.toString().equals("4S") && playerHit2 == true || playerCard5.toString().equals("4S") && playerHit3 == true || playerCard6.toString().equals("4S") && playerHit4 == true || playerCard7.toString().equals("4S") && playerHit5 == true || playerCard8.toString().equals("4S") && playerHit6 == true || playerCard9.toString().equals("4S") && playerHit7 == true || playerCard10.toString().equals("4S") && playerHit8 == true || playerCard11.toString().equals("4S") && playerHit9 == true) {
				g.drawImage(s4, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("4S") || dealerCard2.toString().equals("4S") && roundOver == true && folded != true || dealerCard3.toString().equals("4S") && dealerHit1 == true || dealerCard4.toString().equals("4S") && dealerHit2 == true) {
				g.drawImage(s4, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 5 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("5D") || playerCard2.toString().equals("5D") || playerCard3.toString().equals("5D") && playerHit1 == true || playerCard4.toString().equals("5D") && playerHit2 == true || playerCard5.toString().equals("5D") && playerHit3 == true || playerCard6.toString().equals("5D") && playerHit4 == true || playerCard7.toString().equals("5D") && playerHit5 == true || playerCard8.toString().equals("5D") && playerHit6 == true || playerCard9.toString().equals("5D") && playerHit7 == true || playerCard10.toString().equals("5D") && playerHit8 == true || playerCard11.toString().equals("5D") && playerHit9 == true) {
				g.drawImage(d5, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("5D") || dealerCard2.toString().equals("5D") && roundOver == true && folded != true || dealerCard3.toString().equals("5D") && dealerHit1 == true || dealerCard4.toString().equals("5D") && dealerHit2 == true) {
				g.drawImage(d5, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 5 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("5C") || playerCard2.toString().equals("5C") || playerCard3.toString().equals("5C") && playerHit1 == true || playerCard4.toString().equals("5C") && playerHit2 == true || playerCard5.toString().equals("5C") && playerHit3 == true || playerCard6.toString().equals("5C") && playerHit4 == true || playerCard7.toString().equals("5C") && playerHit5 == true || playerCard8.toString().equals("5C") && playerHit6 == true || playerCard9.toString().equals("5C") && playerHit7 == true || playerCard10.toString().equals("5C") && playerHit8 == true || playerCard11.toString().equals("5C") && playerHit9 == true) {
				g.drawImage(c5, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("5C") || dealerCard2.toString().equals("5C") && roundOver == true && folded != true || dealerCard3.toString().equals("5C") && dealerHit1 == true || dealerCard4.toString().equals("5C") && dealerHit2 == true) {
				g.drawImage(c5, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 5 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("5H") || playerCard2.toString().equals("5H") || playerCard3.toString().equals("5H") && playerHit1 == true || playerCard4.toString().equals("5H") && playerHit2 == true || playerCard5.toString().equals("5H") && playerHit3 == true || playerCard6.toString().equals("5H") && playerHit4 == true || playerCard7.toString().equals("5H") && playerHit5 == true || playerCard8.toString().equals("5H") && playerHit6 == true || playerCard9.toString().equals("5H") && playerHit7 == true || playerCard10.toString().equals("5H") && playerHit8 == true || playerCard11.toString().equals("5H") && playerHit9 == true) {
				g.drawImage(h5, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("5H") || dealerCard2.toString().equals("5H") && roundOver == true && folded != true || dealerCard3.toString().equals("5H") && dealerHit1 == true || dealerCard4.toString().equals("5H") && dealerHit2 == true) {
				g.drawImage(h5, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 5 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("5S") || playerCard2.toString().equals("5S") || playerCard3.toString().equals("5S") && playerHit1 == true || playerCard4.toString().equals("5S") && playerHit2 == true || playerCard5.toString().equals("5S") && playerHit3 == true || playerCard6.toString().equals("5S") && playerHit4 == true || playerCard7.toString().equals("5S") && playerHit5 == true || playerCard8.toString().equals("5S") && playerHit6 == true || playerCard9.toString().equals("5S") && playerHit7 == true || playerCard10.toString().equals("5S") && playerHit8 == true || playerCard11.toString().equals("5S") && playerHit9 == true) {
				g.drawImage(s5, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("5S") || dealerCard2.toString().equals("5S") && roundOver == true && folded != true || dealerCard3.toString().equals("5S") && dealerHit1 == true || dealerCard4.toString().equals("5S") && dealerHit2 == true) {
				g.drawImage(s5, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 6 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("6D") || playerCard2.toString().equals("6D") || playerCard3.toString().equals("6D") && playerHit1 == true || playerCard4.toString().equals("6D") && playerHit2 == true || playerCard5.toString().equals("6D") && playerHit3 == true || playerCard6.toString().equals("6D") && playerHit4 == true || playerCard7.toString().equals("6D") && playerHit5 == true || playerCard8.toString().equals("6D") && playerHit6 == true || playerCard9.toString().equals("6D") && playerHit7 == true || playerCard10.toString().equals("6D") && playerHit8 == true || playerCard11.toString().equals("6D") && playerHit9 == true) {
				g.drawImage(d6, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("6D") || dealerCard2.toString().equals("6D") && roundOver == true && folded != true || dealerCard3.toString().equals("6D") && dealerHit1 == true || dealerCard4.toString().equals("6D") && dealerHit2 == true) {
				g.drawImage(d6, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 6 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("6C") || playerCard2.toString().equals("6C") || playerCard3.toString().equals("6C") && playerHit1 == true || playerCard4.toString().equals("6C") && playerHit2 == true || playerCard5.toString().equals("6C") && playerHit3 == true || playerCard6.toString().equals("6C") && playerHit4 == true || playerCard7.toString().equals("6C") && playerHit5 == true || playerCard8.toString().equals("6C") && playerHit6 == true || playerCard9.toString().equals("6C") && playerHit7 == true || playerCard10.toString().equals("6C") && playerHit8 == true || playerCard11.toString().equals("6C") && playerHit9 == true) {
				g.drawImage(c6, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("6C") || dealerCard2.toString().equals("6C") && roundOver == true && folded != true || dealerCard3.toString().equals("6C") && dealerHit1 == true || dealerCard4.toString().equals("6C") && dealerHit2 == true) {
				g.drawImage(c6, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 6 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("6H") || playerCard2.toString().equals("6H") || playerCard3.toString().equals("6H") && playerHit1 == true || playerCard4.toString().equals("6H") && playerHit2 == true || playerCard5.toString().equals("6H") && playerHit3 == true || playerCard6.toString().equals("6H") && playerHit4 == true || playerCard7.toString().equals("6H") && playerHit5 == true || playerCard8.toString().equals("6H") && playerHit6 == true || playerCard9.toString().equals("6H") && playerHit7 == true || playerCard10.toString().equals("6H") && playerHit8 == true || playerCard11.toString().equals("6H") && playerHit9 == true) {
				g.drawImage(h6, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("6H") || dealerCard2.toString().equals("6H") && roundOver == true && folded != true || dealerCard3.toString().equals("6H") && dealerHit1 == true || dealerCard4.toString().equals("6H") && dealerHit2 == true) {
				g.drawImage(h6, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 6 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("6S") || playerCard2.toString().equals("6S") || playerCard3.toString().equals("6S") && playerHit1 == true || playerCard4.toString().equals("6S") && playerHit2 == true || playerCard5.toString().equals("6S") && playerHit3 == true || playerCard6.toString().equals("6S") && playerHit4 == true || playerCard7.toString().equals("6S") && playerHit5 == true || playerCard8.toString().equals("6S") && playerHit6 == true || playerCard9.toString().equals("6S") && playerHit7 == true || playerCard10.toString().equals("6S") && playerHit8 == true || playerCard11.toString().equals("6S") && playerHit9 == true) {
				g.drawImage(s6, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("6S") || dealerCard2.toString().equals("6S") && roundOver == true && folded != true || dealerCard3.toString().equals("6S") && dealerHit1 == true || dealerCard4.toString().equals("6S") && dealerHit2 == true) {
				g.drawImage(s6, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 7 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("7D") || playerCard2.toString().equals("7D") || playerCard3.toString().equals("7D") && playerHit1 == true || playerCard4.toString().equals("7D") && playerHit2 == true || playerCard5.toString().equals("7D") && playerHit3 == true || playerCard6.toString().equals("7D") && playerHit4 == true || playerCard7.toString().equals("7D") && playerHit5 == true || playerCard8.toString().equals("7D") && playerHit6 == true || playerCard9.toString().equals("7D") && playerHit7 == true || playerCard10.toString().equals("7D") && playerHit8 == true || playerCard11.toString().equals("7D") && playerHit9 == true) {
				g.drawImage(d7, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("7D") || dealerCard2.toString().equals("7D") && roundOver == true && folded != true || dealerCard3.toString().equals("7D") && dealerHit1 == true || dealerCard4.toString().equals("7D") && dealerHit2 == true) {
				g.drawImage(d7, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 7 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("7C") || playerCard2.toString().equals("7C") || playerCard3.toString().equals("7C") && playerHit1 == true || playerCard4.toString().equals("7C") && playerHit2 == true || playerCard5.toString().equals("7C") && playerHit3 == true || playerCard6.toString().equals("7C") && playerHit4 == true || playerCard7.toString().equals("7C") && playerHit5 == true || playerCard8.toString().equals("7C") && playerHit6 == true || playerCard9.toString().equals("7C") && playerHit7 == true || playerCard10.toString().equals("7C") && playerHit8 == true || playerCard11.toString().equals("7C") && playerHit9 == true) {
				g.drawImage(c7, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("7C") || dealerCard2.toString().equals("7C") && roundOver == true && folded != true || dealerCard3.toString().equals("7C") && dealerHit1 == true || dealerCard4.toString().equals("7C") && dealerHit2 == true) {
				g.drawImage(c7, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 7 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("7H") || playerCard2.toString().equals("7H") || playerCard3.toString().equals("7H") && playerHit1 == true || playerCard4.toString().equals("7H") && playerHit2 == true || playerCard5.toString().equals("7H") && playerHit3 == true || playerCard6.toString().equals("7H") && playerHit4 == true || playerCard7.toString().equals("7H") && playerHit5 == true || playerCard8.toString().equals("7H") && playerHit6 == true || playerCard9.toString().equals("7H") && playerHit7 == true || playerCard10.toString().equals("7H") && playerHit8 == true || playerCard11.toString().equals("7H") && playerHit9 == true) {
				g.drawImage(h7, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("7H") || dealerCard2.toString().equals("7H") && roundOver == true && folded != true || dealerCard3.toString().equals("7H") && dealerHit1 == true || dealerCard4.toString().equals("7H") && dealerHit2 == true) {
				g.drawImage(h7, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 7 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("7S") || playerCard2.toString().equals("7S") || playerCard3.toString().equals("7S") && playerHit1 == true || playerCard4.toString().equals("7S") && playerHit2 == true || playerCard5.toString().equals("7S") && playerHit3 == true || playerCard6.toString().equals("7S") && playerHit4 == true || playerCard7.toString().equals("7S") && playerHit5 == true || playerCard8.toString().equals("7S") && playerHit6 == true || playerCard9.toString().equals("7S") && playerHit7 == true || playerCard10.toString().equals("7S") && playerHit8 == true || playerCard11.toString().equals("7S") && playerHit9 == true) {
				g.drawImage(s7, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("7S") || dealerCard2.toString().equals("7S") && roundOver == true && folded != true || dealerCard3.toString().equals("7S") && dealerHit1 == true || dealerCard4.toString().equals("7S") && dealerHit2 == true) {
				g.drawImage(s7, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 8 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("8D") || playerCard2.toString().equals("8D") || playerCard3.toString().equals("8D") && playerHit1 == true || playerCard4.toString().equals("8D") && playerHit2 == true || playerCard5.toString().equals("8D") && playerHit3 == true || playerCard6.toString().equals("8D") && playerHit4 == true || playerCard7.toString().equals("8D") && playerHit5 == true || playerCard8.toString().equals("8D") && playerHit6 == true || playerCard9.toString().equals("8D") && playerHit7 == true || playerCard10.toString().equals("8D") && playerHit8 == true || playerCard11.toString().equals("8D") && playerHit9 == true) {
				g.drawImage(d8, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("8D") || dealerCard2.toString().equals("8D") && roundOver == true && folded != true || dealerCard3.toString().equals("8D") && dealerHit1 == true || dealerCard4.toString().equals("8D") && dealerHit2 == true) {
				g.drawImage(d8, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 8 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("8C") || playerCard2.toString().equals("8C") || playerCard3.toString().equals("8C") && playerHit1 == true || playerCard4.toString().equals("8C") && playerHit2 == true || playerCard5.toString().equals("8C") && playerHit3 == true || playerCard6.toString().equals("8C") && playerHit4 == true || playerCard7.toString().equals("8C") && playerHit5 == true || playerCard8.toString().equals("8C") && playerHit6 == true || playerCard9.toString().equals("8C") && playerHit7 == true || playerCard10.toString().equals("8C") && playerHit8 == true || playerCard11.toString().equals("8C") && playerHit9 == true) {
				g.drawImage(c8, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("8C") || dealerCard2.toString().equals("8C") && roundOver == true && folded != true || dealerCard3.toString().equals("8C") && dealerHit1 == true || dealerCard4.toString().equals("8C") && dealerHit2 == true) {
				g.drawImage(c8, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 8 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("8H") || playerCard2.toString().equals("8H") || playerCard3.toString().equals("8H") && playerHit1 == true || playerCard4.toString().equals("8H") && playerHit2 == true || playerCard5.toString().equals("8H") && playerHit3 == true || playerCard6.toString().equals("8H") && playerHit4 == true || playerCard7.toString().equals("8H") && playerHit5 == true || playerCard8.toString().equals("8H") && playerHit6 == true || playerCard9.toString().equals("8H") && playerHit7 == true || playerCard10.toString().equals("8H") && playerHit8 == true || playerCard11.toString().equals("8H") && playerHit9 == true) {
				g.drawImage(h8, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("8H") || dealerCard2.toString().equals("8H") && roundOver == true && folded != true || dealerCard3.toString().equals("8H") && dealerHit1 == true || dealerCard4.toString().equals("8H") && dealerHit2 == true) {
				g.drawImage(h8, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 8 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("8S") || playerCard2.toString().equals("8S") || playerCard3.toString().equals("8S") && playerHit1 == true || playerCard4.toString().equals("8S") && playerHit2 == true || playerCard5.toString().equals("8S") && playerHit3 == true || playerCard6.toString().equals("8S") && playerHit4 == true || playerCard7.toString().equals("8S") && playerHit5 == true || playerCard8.toString().equals("8S") && playerHit6 == true || playerCard9.toString().equals("8S") && playerHit7 == true || playerCard10.toString().equals("8S") && playerHit8 == true || playerCard11.toString().equals("8S") && playerHit9 == true) {
				g.drawImage(s8, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("8S") || dealerCard2.toString().equals("8S") && roundOver == true && folded != true || dealerCard3.toString().equals("8S") && dealerHit1 == true || dealerCard4.toString().equals("8S") && dealerHit2 == true) {
				g.drawImage(s8, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 9 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("9D") || playerCard2.toString().equals("9D") || playerCard3.toString().equals("9D") && playerHit1 == true || playerCard4.toString().equals("9D") && playerHit2 == true || playerCard5.toString().equals("9D") && playerHit3 == true || playerCard6.toString().equals("9D") && playerHit4 == true || playerCard7.toString().equals("9D") && playerHit5 == true || playerCard8.toString().equals("9D") && playerHit6 == true || playerCard9.toString().equals("9D") && playerHit7 == true || playerCard10.toString().equals("9D") && playerHit8 == true || playerCard11.toString().equals("9D") && playerHit9 == true) {
				g.drawImage(d9, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("9D") || dealerCard2.toString().equals("9D") && roundOver == true && folded != true || dealerCard3.toString().equals("9D") && dealerHit1 == true || dealerCard4.toString().equals("9D") && dealerHit2 == true) {
				g.drawImage(d9, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 9 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("9C") || playerCard2.toString().equals("9C") || playerCard3.toString().equals("9C") && playerHit1 == true || playerCard4.toString().equals("9C") && playerHit2 == true || playerCard5.toString().equals("9C") && playerHit3 == true || playerCard6.toString().equals("9C") && playerHit4 == true || playerCard7.toString().equals("9C") && playerHit5 == true || playerCard8.toString().equals("9C") && playerHit6 == true || playerCard9.toString().equals("9C") && playerHit7 == true || playerCard10.toString().equals("9C") && playerHit8 == true || playerCard11.toString().equals("9C") && playerHit9 == true){
				g.drawImage(c9, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("9C") || dealerCard2.toString().equals("9C") && roundOver == true && folded != true || dealerCard3.toString().equals("9C") && dealerHit1 == true || dealerCard4.toString().equals("9C") && dealerHit2 == true) {
				g.drawImage(c9, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 9 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("9H") || playerCard2.toString().equals("9H") || playerCard3.toString().equals("9H") && playerHit1 == true || playerCard4.toString().equals("9H") && playerHit2 == true ||  playerCard5.toString().equals("9H") && playerHit3 == true || playerCard6.toString().equals("9H") && playerHit4 == true || playerCard7.toString().equals("9H") && playerHit5 == true || playerCard8.toString().equals("9H") && playerHit6 == true || playerCard9.toString().equals("9H") && playerHit7 == true || playerCard10.toString().equals("9H") && playerHit8 == true || playerCard11.toString().equals("9H") && playerHit9 == true) {
				g.drawImage(h9, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("9H") || dealerCard2.toString().equals("9H") && roundOver == true && folded != true || dealerCard3.toString().equals("9H") && dealerHit1 == true || dealerCard4.toString().equals("9H") && dealerHit2 == true) {
				g.drawImage(h9, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 9 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("9S") || playerCard2.toString().equals("9S") || playerCard3.toString().equals("9S") && playerHit1 == true || playerCard4.toString().equals("9S") && playerHit2 == true || playerCard5.toString().equals("9S") && playerHit3 == true || playerCard6.toString().equals("9S") && playerHit4 == true || playerCard7.toString().equals("9S") && playerHit5 == true || playerCard8.toString().equals("9S") && playerHit6 == true || playerCard9.toString().equals("9S") && playerHit7 == true || playerCard10.toString().equals("9S") && playerHit8 == true || playerCard11.toString().equals("9S") && playerHit9 == true) {
				g.drawImage(s9, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("9S") || dealerCard2.toString().equals("9S") && roundOver == true && folded != true || dealerCard3.toString().equals("9S") && dealerHit1 == true || dealerCard4.toString().equals("9S") && dealerHit2 == true) {
				g.drawImage(s9, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 10 of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("10D") || playerCard2.toString().equals("10D") || playerCard3.toString().equals("10D") && playerHit1 == true || playerCard4.toString().equals("10D") && playerHit2 == true || playerCard5.toString().equals("10D") && playerHit3 == true || playerCard6.toString().equals("10D") && playerHit4 == true || playerCard7.toString().equals("10D") && playerHit5 == true || playerCard8.toString().equals("10D") && playerHit6 == true || playerCard9.toString().equals("10D") && playerHit7 == true || playerCard10.toString().equals("10D") && playerHit8 == true || playerCard11.toString().equals("10D") && playerHit9 == true) {
				g.drawImage(d10, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("10D") || dealerCard2.toString().equals("10D") && roundOver == true && folded != true || dealerCard3.toString().equals("10D") && dealerHit1 == true || dealerCard4.toString().equals("10D") && dealerHit2 == true) {
				g.drawImage(d10, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 10 of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("10C") || playerCard2.toString().equals("10C") || playerCard3.toString().equals("10C") && playerHit1 == true || playerCard4.toString().equals("10C") && playerHit2 == true || playerCard5.toString().equals("10C") && playerHit3 == true || playerCard6.toString().equals("10C") && playerHit4 == true || playerCard7.toString().equals("10C") && playerHit5 == true || playerCard8.toString().equals("10C") && playerHit6 == true || playerCard9.toString().equals("10C") && playerHit7 == true || playerCard10.toString().equals("10C") && playerHit8 == true || playerCard11.toString().equals("10C") && playerHit9 == true) {
				g.drawImage(c10, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("10C") || dealerCard2.toString().equals("10C") && roundOver == true && folded != true || dealerCard3.toString().equals("10C") && dealerHit1 == true || dealerCard4.toString().equals("10C") && dealerHit2 == true) {
				g.drawImage(c10, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 10 of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("10H") || playerCard2.toString().equals("10H") || playerCard3.toString().equals("10H") && playerHit1 == true || playerCard4.toString().equals("10H") && playerHit2 == true || playerCard5.toString().equals("10H") && playerHit3 == true || playerCard6.toString().equals("10H") && playerHit4 == true || playerCard7.toString().equals("10H") && playerHit5 == true || playerCard8.toString().equals("10H") && playerHit6 == true || playerCard9.toString().equals("10H") && playerHit7 == true || playerCard10.toString().equals("10H") && playerHit8 == true || playerCard11.toString().equals("10H") && playerHit9 == true) {
				g.drawImage(h10, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("10H") || dealerCard2.toString().equals("10H") && roundOver == true && folded != true || dealerCard3.toString().equals("10H") && dealerHit1 == true || dealerCard4.toString().equals("10H") && dealerHit2 == true) {
				g.drawImage(h10, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the 10 of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("10S") || playerCard2.toString().equals("10S") || playerCard3.toString().equals("10S") && playerHit1 == true || playerCard4.toString().equals("10S") && playerHit2 == true || playerCard5.toString().equals("10S") && playerHit3 == true || playerCard6.toString().equals("10S") && playerHit4 == true || playerCard7.toString().equals("10S") && playerHit5 == true || playerCard8.toString().equals("10S") && playerHit6 == true || playerCard9.toString().equals("10S") && playerHit7 == true || playerCard10.toString().equals("10S") && playerHit8 == true || playerCard11.toString().equals("10S") && playerHit9 == true) {
				g.drawImage(s10, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("10S") || dealerCard2.toString().equals("10S") && roundOver == true && folded != true || dealerCard3.toString().equals("10S") && dealerHit1 == true || dealerCard4.toString().equals("10S") && dealerHit2 == true) {
				g.drawImage(s10, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Jack of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("JD") || playerCard2.toString().equals("JD") || playerCard3.toString().equals("JD") && playerHit1 == true || playerCard4.toString().equals("JD") && playerHit2 == true || playerCard5.toString().equals("JD") && playerHit3 == true || playerCard6.toString().equals("JD") && playerHit4 == true || playerCard7.toString().equals("JD") && playerHit5 == true || playerCard8.toString().equals("JD") && playerHit6 == true || playerCard9.toString().equals("JD") && playerHit7 == true || playerCard10.toString().equals("JD") && playerHit8 == true || playerCard11.toString().equals("JD") && playerHit9 == true) {
				g.drawImage(dJ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("JD") || dealerCard2.toString().equals("JD") && roundOver == true && folded != true || dealerCard3.toString().equals("JD") && dealerHit1 == true || dealerCard4.toString().equals("JD") && dealerHit2 == true) {
				g.drawImage(dJ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Jack of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("JC") || playerCard2.toString().equals("JC") || playerCard3.toString().equals("JC") && playerHit1 == true || playerCard4.toString().equals("JC") && playerHit2 == true || playerCard5.toString().equals("JC") && playerHit3 == true || playerCard6.toString().equals("JC") && playerHit4 == true || playerCard7.toString().equals("JC") && playerHit5 == true || playerCard8.toString().equals("JC") && playerHit6 == true || playerCard9.toString().equals("JC") && playerHit7 == true || playerCard10.toString().equals("JC") && playerHit8 == true || playerCard11.toString().equals("JC") && playerHit9 == true) {
				g.drawImage(cJ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("JC") || dealerCard2.toString().equals("JC") && roundOver == true && folded != true || dealerCard3.toString().equals("JC") && dealerHit1 == true || dealerCard4.toString().equals("JC") && dealerHit2 == true) {
				g.drawImage(cJ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Jack of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("JH") || playerCard2.toString().equals("JH") || playerCard3.toString().equals("JH") && playerHit1 == true || playerCard4.toString().equals("JH") && playerHit2 == true || playerCard5.toString().equals("JH") && playerHit3 == true || playerCard6.toString().equals("JH") && playerHit4 == true || playerCard7.toString().equals("JH") && playerHit5 == true || playerCard8.toString().equals("JH") && playerHit6 == true || playerCard9.toString().equals("JH") && playerHit7 == true || playerCard10.toString().equals("JH") && playerHit8 == true || playerCard11.toString().equals("JH") && playerHit9 == true) {
				g.drawImage(hJ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("JH") || dealerCard2.toString().equals("JH") && roundOver == true && folded != true || dealerCard3.toString().equals("JH") && dealerHit1 == true || dealerCard4.toString().equals("JH") && dealerHit2 == true) {
				g.drawImage(hJ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Jack of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("JS") || playerCard2.toString().equals("JS") || playerCard3.toString().equals("JS") && playerHit1 == true || playerCard4.toString().equals("JS") && playerHit2 == true || playerCard5.toString().equals("JS") && playerHit3 == true || playerCard6.toString().equals("JS") && playerHit4 == true || playerCard7.toString().equals("JS") && playerHit5 == true || playerCard8.toString().equals("JS") && playerHit6 == true || playerCard9.toString().equals("JS") && playerHit7 == true || playerCard10.toString().equals("JS") && playerHit8 == true || playerCard11.toString().equals("JS") && playerHit9 == true) {
				g.drawImage(sJ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("JS") || dealerCard2.toString().equals("JS") && roundOver == true && folded != true || dealerCard3.toString().equals("JS") && dealerHit1 == true || dealerCard4.toString().equals("JS") && dealerHit2 == true) {
				g.drawImage(sJ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Queen of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("QD") || playerCard2.toString().equals("QD") || playerCard3.toString().equals("QD") && playerHit1 == true || playerCard4.toString().equals("QD") && playerHit2 == true || playerCard5.toString().equals("QD") && playerHit3 == true || playerCard6.toString().equals("QD") && playerHit4 == true || playerCard7.toString().equals("QD") && playerHit5 == true || playerCard8.toString().equals("QD") && playerHit6 == true || playerCard9.toString().equals("QD") && playerHit7 == true || playerCard10.toString().equals("QD") && playerHit8 == true || playerCard11.toString().equals("QD") && playerHit9 == true) {
				g.drawImage(dQ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("QD") || dealerCard2.toString().equals("QD") && roundOver == true && folded != true || dealerCard3.toString().equals("QD") && dealerHit1 == true || dealerCard4.toString().equals("QD") && dealerHit2 == true) {
				g.drawImage(dQ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Queen of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("QC") || playerCard2.toString().equals("QC") || playerCard3.toString().equals("QC") && playerHit1 == true || playerCard4.toString().equals("QC") && playerHit2 == true || playerCard5.toString().equals("QC") && playerHit3 == true || playerCard6.toString().equals("QC") && playerHit4 == true || playerCard7.toString().equals("QC") && playerHit5 == true || playerCard8.toString().equals("QC") && playerHit6 == true || playerCard9.toString().equals("QC") && playerHit7 == true || playerCard10.toString().equals("QC") && playerHit8 == true || playerCard11.toString().equals("QC") && playerHit9 == true) {
				g.drawImage(cQ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("QC") || dealerCard2.toString().equals("QC") && roundOver == true && folded != true || dealerCard3.toString().equals("QC") && dealerHit1 == true || dealerCard4.toString().equals("QC") && dealerHit2 == true) {
				g.drawImage(cQ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Queen of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("QH") || playerCard2.toString().equals("QH") || playerCard3.toString().equals("QH") && playerHit1 == true || playerCard4.toString().equals("QH") && playerHit2 == true || playerCard5.toString().equals("QH") && playerHit3 == true || playerCard6.toString().equals("QH") && playerHit4 == true || playerCard7.toString().equals("QH") && playerHit5 == true || playerCard8.toString().equals("QH") && playerHit6 == true || playerCard9.toString().equals("QH") && playerHit7 == true || playerCard10.toString().equals("QH") && playerHit8 == true || playerCard11.toString().equals("QH") && playerHit9 == true) {
				g.drawImage(hQ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("QH") || dealerCard2.toString().equals("QH") && roundOver == true && folded != true || dealerCard3.toString().equals("QH") && dealerHit1 == true || dealerCard4.toString().equals("QH") && dealerHit2 == true) {
				g.drawImage(hQ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Queen of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("QS") || playerCard2.toString().equals("QS") || playerCard3.toString().equals("QS") && playerHit1 == true || playerCard4.toString().equals("QS") && playerHit2 == true || playerCard5.toString().equals("QS") && playerHit3 == true || playerCard6.toString().equals("QS") && playerHit4 == true || playerCard7.toString().equals("QS") && playerHit5 == true || playerCard8.toString().equals("QS") && playerHit6 == true || playerCard9.toString().equals("QS") && playerHit7 == true || playerCard10.toString().equals("QS") && playerHit8 == true || playerCard11.toString().equals("QS") && playerHit9 == true) {
				g.drawImage(sQ, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("QS") || dealerCard2.toString().equals("QS") && roundOver == true && folded != true || dealerCard3.toString().equals("QS") && dealerHit1 == true || dealerCard4.toString().equals("QS") && dealerHit2 == true) {
				g.drawImage(sQ, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the King of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("KD") || playerCard2.toString().equals("KD") || playerCard3.toString().equals("KD") && playerHit1 == true || playerCard4.toString().equals("KD") && playerHit2 == true || playerCard5.toString().equals("KD") && playerHit3 == true || playerCard6.toString().equals("KD") && playerHit4 == true || playerCard7.toString().equals("KD") && playerHit5 == true || playerCard8.toString().equals("KD") && playerHit6 == true || playerCard9.toString().equals("KD") && playerHit7 == true || playerCard10.toString().equals("KD") && playerHit8 == true || playerCard11.toString().equals("KD") && playerHit9 == true) {
				g.drawImage(dK, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("KD") || dealerCard2.toString().equals("KD") && roundOver == true && folded != true || dealerCard3.toString().equals("KD") && dealerHit1 == true || dealerCard4.toString().equals("KD") && dealerHit2 == true) {
				g.drawImage(dK, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the King of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("KC") || playerCard2.toString().equals("KC") || playerCard3.toString().equals("KC") && playerHit1 == true || playerCard4.toString().equals("KC") && playerHit2 == true || playerCard5.toString().equals("KC") && playerHit3 == true || playerCard6.toString().equals("KC") && playerHit4 == true || playerCard7.toString().equals("KC") && playerHit5 == true || playerCard8.toString().equals("KC") && playerHit6 == true || playerCard9.toString().equals("KC") && playerHit7 == true || playerCard10.toString().equals("KC") && playerHit8 == true || playerCard11.toString().equals("KC") && playerHit9 == true) {
				g.drawImage(cK, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("KC") || dealerCard2.toString().equals("KC") && roundOver == true && folded != true || dealerCard3.toString().equals("KC") && dealerHit1 == true || dealerCard4.toString().equals("KC") && dealerHit2 == true) {
				g.drawImage(cK, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the King of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("KH") || playerCard2.toString().equals("KH") || playerCard3.toString().equals("KH") && playerHit1 == true || playerCard4.toString().equals("KH") && playerHit2 == true || playerCard5.toString().equals("KH") && playerHit3 == true || playerCard6.toString().equals("KH") && playerHit4 == true || playerCard7.toString().equals("KH") && playerHit5 == true || playerCard8.toString().equals("KH") && playerHit6 == true || playerCard9.toString().equals("KH") && playerHit7 == true || playerCard10.toString().equals("KH") && playerHit8 == true || playerCard11.toString().equals("KH") && playerHit9 == true) {
				g.drawImage(hK, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("KH") || dealerCard2.toString().equals("KH") && roundOver == true && folded != true || dealerCard3.toString().equals("KH") && dealerHit1 == true || dealerCard4.toString().equals("KH") && dealerHit2 == true) {
				g.drawImage(hK, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the King of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("KS") || playerCard2.toString().equals("KS") || playerCard3.toString().equals("KS") && playerHit1 == true || playerCard4.toString().equals("KS") && playerHit2 == true || playerCard5.toString().equals("KS") && playerHit3 == true || playerCard6.toString().equals("KS") && playerHit4 == true || playerCard7.toString().equals("KS") && playerHit5 == true || playerCard8.toString().equals("KS") && playerHit6 == true || playerCard9.toString().equals("KS") && playerHit7 == true || playerCard10.toString().equals("KS") && playerHit8 == true || playerCard11.toString().equals("KS") && playerHit9 == true ) {
				g.drawImage(sK, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("KS") || dealerCard2.toString().equals("KS") && roundOver == true && folded != true || dealerCard3.toString().equals("KS") && dealerHit1 == true || dealerCard4.toString().equals("KS") && dealerHit2 == true) {
				g.drawImage(sK, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			// PLAYER/DEALER - If the Ace of Diamonds is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("AD") || playerCard2.toString().equals("AD") || playerCard3.toString().equals("AD") && playerHit1 == true || playerCard4.toString().equals("AD") && playerHit2 == true) {
				g.drawImage(dA, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("AD") || dealerCard2.toString().equals("AD") && roundOver == true && folded != true || dealerCard3.toString().equals("AD") && dealerHit1 == true || dealerCard4.toString().equals("AD") && dealerHit2 == true) {
				g.drawImage(dA, xPosDealer, 22, this);
				xPosDealer += 30;
			}		
			// PLAYER/DEALER - If the Ace of Clubs is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("AC") || playerCard2.toString().equals("AC") || playerCard3.toString().equals("AC") && playerHit1 == true || playerCard4.toString().equals("AC") && playerHit2 == true  || playerCard5.toString().equals("AD") && playerHit3 == true || playerCard6.toString().equals("AD") && playerHit4 == true || playerCard7.toString().equals("AD") && playerHit5 == true || playerCard8.toString().equals("AD") && playerHit6 == true || playerCard9.toString().equals("AD") && playerHit7 == true || playerCard10.toString().equals("AD") && playerHit8 == true || playerCard11.toString().equals("AD") && playerHit9 == true) {
				g.drawImage(cA, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("AC") || dealerCard2.toString().equals("AC") && roundOver == true && folded != true || dealerCard3.toString().equals("AC") && dealerHit1 == true || dealerCard4.toString().equals("AC") && dealerHit2 == true) {
				g.drawImage(cA, xPosDealer, 22, this);
				xPosDealer += 30;
			}	
			// PLAYER/DEALER - If the Ace of Hearts is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("AH") || playerCard2.toString().equals("AH") || playerCard3.toString().equals("AH") && playerHit1 == true || playerCard4.toString().equals("AH") && playerHit2 == true  || playerCard5.toString().equals("AH") && playerHit3 == true || playerCard6.toString().equals("AH") && playerHit4 == true || playerCard7.toString().equals("AH") && playerHit5 == true || playerCard8.toString().equals("AH") && playerHit6 == true || playerCard9.toString().equals("AH") && playerHit7 == true || playerCard10.toString().equals("AH") && playerHit8 == true || playerCard11.toString().equals("AH") && playerHit9 == true) {
				g.drawImage(hA, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("AH") || dealerCard2.toString().equals("AH") && roundOver == true && folded != true  || dealerCard3.toString().equals("AH") && dealerHit1 == true || dealerCard4.toString().equals("AH") && dealerHit2 == true) {
				g.drawImage(hA, xPosDealer, 22, this);
				xPosDealer += 30;
			}		
			// PLAYER/DEALER - If the Ace of Spades is drawn, the next card will be drawn 30 pixels to the right.
			if (playerCard1.toString().equals("AS") || playerCard2.toString().equals("AS") || playerCard3.toString().equals("AS") && playerHit1 == true || playerCard4.toString().equals("AS") && playerHit2 == true  || playerCard5.toString().equals("AS") && playerHit3 == true || playerCard6.toString().equals("AS") && playerHit4 == true || playerCard7.toString().equals("AS") && playerHit5 == true || playerCard8.toString().equals("AS") && playerHit6 == true || playerCard9.toString().equals("AS") && playerHit7 == true || playerCard10.toString().equals("AS") && playerHit8 == true || playerCard11.toString().equals("AS") && playerHit9 == true) {
				g.drawImage(sA, xPosPlayer, 345, this);
				xPosPlayer += 30;
			}
			if (dealerCard1.toString().equals("AS") || dealerCard2.toString().equals("AS") && roundOver == true && folded != true  || dealerCard3.toString().equals("AS") && dealerHit1 == true || dealerCard4.toString().equals("AS") && dealerHit2 == true) {
				g.drawImage(sA, xPosDealer, 22, this);
				xPosDealer += 30;
			}
			
			// Setting the font when the winner is declared.
			g2.setColor(violet);
			
			// If the player busts, the loser message is displayed.
			if (checkWinner() == 1 && roundOver == true) {
				g2.setFont(winner);
				g2.drawString("YOU BUSTED!", 178, 282);
				g2.setFont(statement);
				if (dealerHit1 == true) {
					g2.drawString("Dealer hits " + dealerHitCount + " time(s). Dealer has won " + dealerWins + " round(s)." , 60, 320);
				} else {
					g2.drawString("Dealer has won " + dealerWins + " rounds.", 199, 320);
				}
			} // If the dealer busts, the winner message is displayed.
			else if (checkWinner() == 2 && roundOver == true && folded != true) { 
				g2.setFont(winner);
				g2.drawString("DEALER BUSTS!", 170, 282);
				g2.setFont(statement);
				if (dealerHit1 == true) {
					g2.drawString("Dealer hits " + dealerHitCount + " time(s). Player has won " + playerWins + " round(s).", 71, 320);
				} else {
					g2.drawString("Player has won " + playerWins + " round(s).", 195, 320);
				}
			} // If the player and dealer tie, the draw message is displayed.
			else if (checkWinner() == 3 && roundOver == true && folded != true) {
				g2.setFont(winner);
				g2.drawString("DRAW!", 262, 282);
				g2.setFont(statement);
				if (dealerHit1 == true) {
					g2.drawString("Dealer hits " + dealerHitCount + " time(s). You have tied " + ties + " time(s).", 95, 320);
				} else {
					g2.drawString("You have tied " + ties + " time(s).", 216, 320);
				}
			} // If the player has a higher amount than the dealer, the winner message is displayed. 
			else if (checkWinner() == 4 && roundOver == true && folded != true) {
				g2.setFont(winner);
				g2.drawString("YOU WIN!", 230, 282);
				g2.setFont(statement);
				if (dealerHit1 == true) {
					g2.drawString("Dealer hits " + dealerHitCount + " time(s). Player has won " + playerWins + " round(s).", 71, 320);
				} else {
					g2.drawString("Player has won " + playerWins + " round(s).", 195, 320);
				}
			} // If the dealer has a higher amount than the player, the loser message is displayed. 
			else if (checkWinner() == 5 && roundOver == true && folded != true){
				g2.setFont(winner);
				g2.drawString("DEALER WINS!", 165, 282);
				g2.setFont(statement);
				if (dealerHit1 == true) {
					g2.drawString("Dealer hits " + dealerHitCount + " time(s). Dealer has won " + dealerWins + " round(s).", 71, 320);
				} else {
					g2.drawString("Dealer has won " + dealerWins + " round(s).", 195, 320);
				}
			}
			// If the player has folded, the loser message is displayed.
			if (folded == true && roundOver == true) {
				g2.setFont(winner);
				g2.drawString("FOLDED! DEALER WINS!", 57, 282);
				g2.setFont(statement);
				g2.drawString("Dealer has won " + dealerWins + " round(s).", 195, 320);
			}
		}
	}
}