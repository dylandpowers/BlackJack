import java.util.*;

public class BlackJackMain {
	public Card deck;
	
	public BlackJackMain() {
		deck = new Card();
		deck.initialDraw();
	}
	
	/**
	 * Called when it is the player's turn to hit (always first).
	 * The player can either hit, pass, or view their current hand.
	 * After this is called, the dealer has its turn. 
	 */
	public void playerTurn() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("Would you like to 'hit', 'pass', or 'view hand'?");
			String answer = scanner.next();
			if (answer.equals("hit")) {
				deck.drawCard(true);
				System.out.println("Your new total is " + deck.getPlayerTotal());
				if (deck.getPlayerTotal() > 21 && !deck.hasAce(true)) {
					System.out.println("You lose!");
					break;
				} else if (deck.getPlayerTotal() > 21 && deck.hasAce(true)) {
					deck.changeTotal(10, true);
					System.out.println("You went over 21, your Ace is being used as a 1 now.\n Your new total is " + deck.getPlayerTotal() + "\n");
				}
			} else if (answer.equals("view")) {
				deck.printHand(true);
				scanner.next();
			} else break;
		}
		scanner.close();
	}
	
	
	/**
	 * This is called after the player finishes.
	 * The dealer is designed to hit only when its total is less than 16.
	 * There is a 2-second pause in between decisions made by the dealer.
	 * @throws InterruptedException
	 */
	public void dealerTurn() throws InterruptedException {
		
		while (true) {
			if (deck.getDealerTotal() < 16) {
				System.out.println("The dealer chose to hit.\n");
				deck.drawCard(false);
				if (deck.getDealerTotal() <= 21) {
					Thread.sleep(2000);
				}
				if (deck.getDealerTotal() > 21 && !deck.hasAce(true)) {
					System.out.println("The dealer's total is " + deck.getDealerTotal() + ". You win!");
					break;
				} else if (deck.getDealerTotal() > 21 && deck.hasAce(true)) {
					deck.changeTotal(10, false);
				}
			} else {
				System.out.println("The dealer chose to pass.\n");
				break;
			}
		}
	}
	
	
	/**
	 * Determines the winner based upon the results
	 * of the above two methods.
	 */
	public void determineWinner() {
		if (deck.getDealerTotal() > deck.getPlayerTotal())
			System.out.println("You lose. You had " + deck.getPlayerTotal() + " and the dealer had " + deck.getDealerTotal()+ ".\n");
		else if (deck.getPlayerTotal() > deck.getDealerTotal())
			System.out.println("You win! You had " + deck.getPlayerTotal() + " and the dealer had " + deck.getDealerTotal()+ ".\n");
		else 
			System.out.println("It's a draw! You had " + deck.getPlayerTotal() + " and the dealer had " + deck.getDealerTotal()+ ".\n");
	}
	
	public static void main(String[] args) throws InterruptedException {
		BlackJackMain bjm  = new BlackJackMain();
		bjm.playerTurn();
		bjm.dealerTurn();
		bjm.determineWinner();
	}

}
