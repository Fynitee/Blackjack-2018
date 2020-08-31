/*
 * The CardsDeck class main goal is to create and shuffle the deck of cards. It also controls
 * the cards that the player and dealer draws and returns the total value of both of their hands.
 */

import java.util.ArrayList;
import java.util.Collections;

public class CardsDeck {
	
	// Variable declarations
	private static String[] suit = { "D", "C", "H", "S" };
	private static String[] number = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private static ArrayList<String> entireDeck = new ArrayList<String>();
	private String hand;
	
	/**
	 * Constructor
	 * pre: none
	 * post: The deck of cards is created.
	 */
	public CardsDeck() {
		createDeck();		
	}
		
	/**
	 * Creates a shuffled deck of cards.
	 * pre: none
	 * post: The shuffled deck of cards is added onto the main array list.
	 */
	public void createDeck() { // The deck of cards is created.
		
		// Instantiating the deck array.
		String[] deck = new String[52];
		
		// The cards are created by pairing the suit and number together.
		for (int i = 0; i < deck.length; i++) {
			deck[i] = number[i % 13] + suit[i / 13];
		}
	
		// The deck is shuffled.
		for (int i = 0; i < deck.length; i++) {
			// The index of the array is randomized.
			int index = (int) (Math.random() * deck.length);

			/* The cards are placed in a temporary deck, the randomized index replaces the original card
			 * and the temporary card replaces the randomized index. */
			String temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;		
		}
		
		// The shuffled deck is added onto the array list.
		Collections.addAll(entireDeck, deck);
	}
	
	/**
	 * The drawn card is retrieved and removed from the deck.
	 * pre: A deck of cards was initialized.
	 * post: The drawn card is retrieved and removed from the deck.
	 */
	public void draw(CardsDeck theDeck) {
		// The string takes the first card of the deck and the deck removes the card.
		hand = theDeck.getCard(0);
		theDeck.removeCard(0);
	}
		
	/**
	 * Returns the card in String format.
	 * pre: none
	 * post: The card in String format has been returned.
	 */
	public String toString() { // The value from the array list is converted into String.
		return(hand);
	}
	
	/**
	 * Removes a card from the deck.
	 * pre: the index of the deck's array list.
	 * post: The card is removed from the deck.
	 */
	public void removeCard(int i) { // A card from the deck can be removed.
		entireDeck.remove(i);
	} 
	
	/**
	 * Retrieves a card from the deck.
	 * pre: the index of the deck's array list.
	 * post: The card is retrieved from the deck.
	 */
	public String getCard(int i) { // A card from the deck can be retrieved.
		return (entireDeck.get(i));
	}
	
	/**
	 * Returns the size of the deck.
	 * pre: none
	 * post: The size of the deck has been returned.
	 */
	public int deckSize() { // The deck size is returned.
		return(entireDeck.size());
	}
	
	/**
	 * Returns the player's and dealer's total value of their hands.
	 * pre: the player and dealer have drawn cards.
	 * post: The total value for the player and dealer's hands have been returned.
	 */
	public int totalValue() {		
		int totalValue = 0;
		int aces = 0;

		// The switch determines which card has been drawn based on the first letter or number.
		switch (hand.charAt(0)) {
			case '2': totalValue += 2; break;
			case '3': totalValue += 3; break;
			case '4': totalValue += 4; break;
			case '5': totalValue += 5; break;
			case '6': totalValue += 6; break;
			case '7': totalValue += 7; break;
			case '8': totalValue += 8; break;
			case '9': totalValue += 9; break;
			case '1': totalValue += 10; break;
			case 'J': totalValue += 10; break;
			case 'Q': totalValue += 10; break;
			case 'K': totalValue += 10; break;
			case 'A': aces += 1; break;
		}/*
		if (totalValue > 10 && aces > 0) {
			totalValue += 1;
		} else if (totalValue <= 10 && aces > 0) {
			totalValue += 11;
		}*/
		
		for (int i = 0; i < aces; i++) {
			if (totalValue > 10) {
				totalValue += 1;
			} else {
				totalValue += 11;
			}
		}
		return(totalValue);
	}
}
