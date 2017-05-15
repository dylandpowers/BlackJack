import java.util.*;

public class Card {
	
	private String[] myCards = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private LinkedList<String> myDeck;
	private LinkedList<String> myHand;
	private LinkedList<String> dealerHand;
	private int myTotal = 0;
	private int dealerTotal = 0;
	private boolean myAce = false;
	private boolean dealerAce = false;
	
	//Constructor to build a new deck.
	//Deck is represented as a 52-card list
	public Card() {
		reset();
	}
	
	/* Sets cards to standard card deck.
	 * Should also be performed after a hand.
	 */
	public void reset() {
		
		dealerHand = new LinkedList<String>();
		myHand = new LinkedList<String>();
		myDeck = new LinkedList<String>();
		for (String s : myCards) {
			for (int i = 0; i < 4; i++) {
				myDeck.add(s);
			}
		}
		Collections.shuffle(myDeck);
	}
	
	//Returns a single card from the deck.
	public String getCard() {
		return myDeck.removeFirst();
	}
	
	//Returns player's total
	public int getPlayerTotal() {
		return myTotal;
	}
	
	//Returns dealer's total
	public int getDealerTotal() {
		return dealerTotal;
	}
	
	//Return's size of player's hand
	public int getHandSize() {
		return myHand.size();
	}
	
	//Returns if player has ace 
	public boolean hasAce(boolean player) {
		if (player) return myAce;
		return dealerAce;
	}
	
	
	/**
	 * Changes the total of the player based on the cards
	 * they are holding.
	 * @param card the card whose value is to be added
	 * @param player indicates whether or not this person is a player
	 */
	public void changeTotal(String card, boolean player) {
		if (player) {
			if (card.equals("Jack") || card.equals("Queen") || card.equals("King")) myTotal += 10;
			else if (!card.equals("Ace")) myTotal += Integer.parseInt(card);
			else {
				myAce = true;
				myTotal += 11;
			}
			
		} else {
			if (card.equals("Jack") || card.equals("Queen") || card.equals("King")) dealerTotal += 10;
			else if (!card.equals("Ace")) dealerTotal += Integer.parseInt(card);
			else {
				dealerAce = true;
				dealerTotal += 11;
			}
		}
	}
	
	
	/**
	 * Changes the total, this is a method used to 
	 * decrement a player's total by 10 whenever they 
	 * have an Ace and go over 23
	 * @param value total will be [decremented] by this value
	 * @param player indicates whether or not this acts on a player or dealer
	 */
	public void changeTotal(int value, boolean player) {
		if (player) myTotal -= value;
		else dealerTotal -= value;
	}

	/**
	 * Draws a card from the deck.
	 * @param player indicates whether or not this person is a player
	 */
	public String drawCard(boolean player) {
		String card;
		if (player) {
			card = myDeck.removeFirst();
			myHand.add(card);
			changeTotal(card, true);
			System.out.println("You drew a " + card);
		} else {
			card = myDeck.removeFirst();
			dealerHand.add(card);
			changeTotal(card, false);
		}
		return card;
	}
	/**
	 * Method to get first draw of the deck.
	 * Removes the first two cards, which should be 
	 * random due to shuffling.
	 * @return total from first two cards.
	 */
	public void initialDraw() {
		
		String card1 = drawCard(true);
		String card2 = drawCard(true);
		String dealer1 = drawCard(false);
		String dealer2 = drawCard(false);
		
		//Prints hands
		System.out.println("The dealer drew a " + dealer1 + " and the other card is hidden.");
		if (!myAce) {
			System.out.println("\nYour total is " + myTotal);
		} else {
			System.out.println("\nAn ace counts as either 11 or 1.\n" + 
					"Using 11, your total is " + myTotal);
					
		}
		
	}
	
	//Prints hands after a hit 
	public void printHand(boolean player) {
		if (player) {
			System.out.print("You have the following cards:");
			for (int i = 0; i < myHand.size(); i++) {
				System.out.print(myHand.get(i) + " ");
			}
			System.out.println();
		} else {
			for (int i = 0; i < dealerHand.size(); i++) {
				System.out.print(dealerHand.get(i));
			}
		}
	}
	
}
