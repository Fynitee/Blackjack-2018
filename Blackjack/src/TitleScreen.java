/*
 * The TitleScreen class displays the title screen and tutorial screens for the BlackJack
 * program via. JFrames.
 */
import java.awt.event.*;
import javax.swing.*;

public class TitleScreen implements ActionListener {
	
	// Instantiation of JFrames for the title screen and tutorial screens.
	private static JFrame title = new JFrame();
	private static JFrame tutorial1 = new JFrame();
	private static JFrame tutorial2 = new JFrame();
	private static JFrame tutorial3 = new JFrame();
	
	// Instantiation of ImageIcons for the title screen and tutorial screens.
	private static ImageIcon bgTitle = new ImageIcon("imgBlackJack\\title.png");
	private static ImageIcon bgt1 = new ImageIcon("imgBlackJack\\tutorial1.png");
	private static ImageIcon bgt2 = new ImageIcon("imgBlackJack\\tutorial2.png");
	private static ImageIcon bgt3 = new ImageIcon("imgBlackJack\\tutorial3.png");
	
	// Instantiation of ImageIcons for the play, next, previous, back, and start buttons.
	private static ImageIcon bPlay = new ImageIcon("imgBlackJack\\bPlay.png");
	private static ImageIcon bNext = new ImageIcon("imgBlackJack\\bNext.png");
	private static ImageIcon bPrev = new ImageIcon("imgBlackJack\\bPrev.png");
	private static ImageIcon bBack = new ImageIcon("imgBlackJack\\bBack.png");
	private static ImageIcon bStart = new ImageIcon("imgBlackJack\\bStart.png");
	
	// Instantiation of JLabels for the title screen and tutorial screens.
	private static JLabel t = new JLabel(TitleScreen.bgTitle);
	private static JLabel t1 = new JLabel(TitleScreen.bgt1);
	private static JLabel t2 = new JLabel(TitleScreen.bgt2);
	private static JLabel t3 = new JLabel(TitleScreen.bgt3);
	
	// Instantiation of JButtons for the play, back, two next, two previous, and start buttons.
	private static JButton play = new JButton(TitleScreen.bPlay);
	private static JButton back = new JButton(TitleScreen.bBack);
	private static JButton next1 = new JButton(TitleScreen.bNext);
	private static JButton next2 = new JButton(TitleScreen.bNext);
	private static JButton prev1 = new JButton(TitleScreen.bPrev);
	private static JButton prev2 = new JButton(TitleScreen.bPrev);
	private static JButton start = new JButton(TitleScreen.bStart);
	
	/**
	 * Determines what button the player has pushed.
	 * pre: A button is pushed.
	 * post: The screens' visibilities are adjusted according to the button that was pushed.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) { // If play was selected by the player.
			title.setVisible(false);			
			tutorial1.setVisible(true);
		}
		if (e.getSource() == back) { // If back was selected by the player.
			title.setVisible(true);
			tutorial1.setVisible(false);
		}
		if (e.getSource() == next1) { // If next on the first tutorial screen was selected by the player.
			tutorial1.setVisible(false);
			tutorial2.setVisible(true);
		}
		if (e.getSource() == prev1) { // If previous on the second tutorial screen was selected by the player.
			tutorial2.setVisible(false);
			tutorial1.setVisible(true);
		}
		if (e.getSource() == next2) { // If next on the second tutorial screen was selected by the player.
			tutorial2.setVisible(false);
			tutorial3.setVisible(true);
		}
		if (e.getSource() == prev2) { // If previous on the third tutorial screen was selected by the player.
			tutorial3.setVisible(false);			
			tutorial2.setVisible(true);
		}
		if (e.getSource() == start) { // If start was selected by the player.
			tutorial3.setVisible(false);
			
			// BlackJack's main game constructor is initialized.
			new BlackJack();
		}
	}
	
	/**
	 * Constructor
	 * pre: none
	 * post: The title screen and tutorial screens are displayed accordingly.
	 */
	public TitleScreen() {
		// Setting JFrame settings for the title screen.
		title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		title.setLayout(null);
		
		// Configuring the title screen's image location, border, visibility, and play button.
		t.setBounds(-5,-23,700,550);
		t.setBorder(null);
		t.setVisible(true);
		play.setBounds(232, 400, 219, 68);

		// Adding the play button and title screen image onto the JFrame.
		title.getContentPane().add(play);
		title.getContentPane().add(t);
		
		// Setting JFrame settings for the title screen.
		title.setTitle("BlackJack");
		title.setLocation(330, 90);
		title.setResizable(false);
		title.setSize(700, 550);
		title.setVisible(true);
				
		// Configuring the first tutorial screen's image location, border, visibility, back button, and next button.
		t1.setBounds(-5,40,700,550);
		t1.setBorder(null);
		t1.setVisible(true);
		back.setBounds(32, 450, 178, 48);
		next1.setBounds(475, 450, 178, 48);
		
		// Adding the back button, next button, and the first tutorial screen's image onto the JFrame.
		tutorial1.getContentPane().add(back);
		tutorial1.getContentPane().add(next1);
		tutorial1.getContentPane().add(t1);	
		
		// Setting JFrame settings for the first tutorial screen.
		tutorial1.setTitle("BlackJack");
		tutorial1.setLocation(330, 90);
		tutorial1.setResizable(false);
		tutorial1.setSize(700, 550);

		// Configuring the second tutorial screen's image location, border, visibility, next button, and previous button.
		t2.setBounds(-5,40,700,550);
		t2.setBorder(null);
		t2.setVisible(true);
		next2.setBounds(475, 450, 178, 48);
		prev1.setBounds(32, 450, 178, 48);
		
		// Adding the next button, previous button, and the second tutorial screen's image onto the JFrame.
		tutorial2.getContentPane().add(next2);
		tutorial2.getContentPane().add(prev1);
		tutorial2.getContentPane().add(t2);	
		
		// Setting JFrame settings for the second tutorial screen.
		tutorial2.setTitle("BlackJack");
		tutorial2.setLocation(330, 90);
		tutorial2.setResizable(false);
		tutorial2.setSize(700, 550);		
		
		// Configuring the third tutorial screen's image location, border, visibility, previous button, and start button.
		t3.setBounds(-5,40,700,550);
		t3.setBorder(null);
		t3.setVisible(true);
		prev2.setBounds(32, 450, 178, 48);
		start.setBounds(475, 450, 178, 48);
		
		// Adding the previous button, start button, and the third tutorial screen's image onto the JFrame.
		tutorial3.getContentPane().add(prev2);
		tutorial3.getContentPane().add(start);
		tutorial3.getContentPane().add(t3);	
		
		// Setting JFrame settings for the third tutorial screen.
		tutorial3.setTitle("BlackJack");
		tutorial3.setLocation(330, 90);
		tutorial3.setResizable(false);
		tutorial3.setSize(700, 550);

		// Adding ActionListeners for each button.
		play.addActionListener(this);
		back.addActionListener(this);
		next1.addActionListener(this);
		next2.addActionListener(this);
		prev1.addActionListener(this);
		prev2.addActionListener(this);
		start.addActionListener(this);
	}
}
