/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */


public class Card1 {
    /*
     * Test code.
     */
    public static void main(String[] args) {
	Card[] cards = Card.makeDeck();
	
	// check that findBisect finds each card
	int index;
	for (int i=0; i<52; i++) {
	    index = Card.findBisect(cards, cards[i], 0, cards.length-1);
	    if (index != i) {
		Card.printCard(cards[i]);
		System.out.println("Wrong!");
	    }
	}
    }
}

/*
 * In this version of the Card class, a deck is an array
 * of Cards, and everything is written as a class method.
 */
class Card {
    int suit, rank;

    /*
     * No-argument constructor.
     */
    public Card() { 
	this.suit = 0;  this.rank = 0;
    }

    /*
     * Constructor with arguments.
     */
    public Card(int suit, int rank) { 
	this.suit = suit;  this.rank = rank;
    }

    /*
     * Prints a card in human-readable form.
     */
    public static void printCard(Card c) {
	String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6",
			   "7", "8", "9", "10", "Jack", "Queen", "King" };

	System.out.println(ranks[c.rank] + " of " + suits[c.suit]);
    }

    /*
     * Return true if the cards are equivalent.
     */
    public static boolean sameCard(Card c1, Card c2) {
	return (c1.suit == c2.suit && c1.rank == c2.rank);
    }

    /*
     * Compares two cards: returns 1 if the first card is greater
     * -1 if the seconds card is greater, and 0 if they are the equivalent.
     */
    public static int compareCards(Card c1, Card c2) {

	// first compare the suits
	if (c1.suit > c2.suit) return 1;
	if (c1.suit < c2.suit) return -1;

	// compare the ranks
	if (c1.rank > c2.rank) return 1;
	if (c1.rank < c2.rank) return -1;
	return 0;
    }

    /*
     * Makes an array of 52 cards.
     */
    public static Card[] makeDeck() {
	Card[] cards = new Card [52];
	
	int index = 0;
	for (int suit = 0; suit <= 3; suit++) {
	    for (int rank = 1; rank <= 13; rank++) {
		cards[index] = new Card(suit, rank);
		index++;
 	    }
	}
	return cards;
    }

    /*
     * Prints an array of cards.
     */
    public static void printDeck(Card[] cards) {
	for (int i=0; i<cards.length; i++) {
	    printCard(cards[i]);
	}
    }

    /*
     * Returns index of the first location where card appears in cards.
     * Or -1 if it does not appear.  Uses a linear search.
     */
    public static int findCard (Card[] cards, Card card) {
	for (int i = 0; i< cards.length; i++) {
	    if (sameCard(cards[i], card)) {
		return i;
	    }
	}
	return -1;
    }

    /*
     * Returns index of a location where card appears in cards.
     * Or -1 if it does not appear.  Uses a bisection search.
     *
     * Searches from low to high, including both.
     *
     * Precondition: the cards must be sorted from lowest to highest.
     */
    public static int findBisect(Card[] cards, Card card, int low, int high) {
	if (high < low) return -1;

	int mid = (high + low) / 2;
	int comp = compareCards(card, cards[mid]);

	if (comp == 0) {
	    return mid;
	} else if (comp < 0) {
	    // card is less than cards[mid]; search the first half
	    return findBisect(cards, card, low, mid-1);
	} else {
	    // card is greater; search the second half
	    return findBisect(cards, card, mid+1, high);
	}
    }
}



