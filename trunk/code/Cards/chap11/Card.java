/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */


/*
 * In this version of the Card class, a deck is an Array
 * of Cards, and everything is written as a class method.
 */
public class Card
{
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
     * Prints an array of cards.
     */
    public static void printDeck(Card[] deck) {
	for (int i=0; i<deck.length; i++) {
	    printCard(deck[i]);
	}
    }

    /*
     * Compares two cards: returns 1 if the first card is greater
     * -1 if the seconds card is greater, and 0 if they are the equivalent.
     */
    public static int compareCards(Card c1, Card c2) {

	// first compare the suits
	if (c1.suit > c2.suit) return 1;
	if (c1.suit < c2.suit) return -1;

	// if the suits are the same,
	// use modulus arithmetic to rotate the ranks
	// so that the Ace is greater than the King.
    
	int rank1 = (c1.rank + 11) % 13;
	int rank2 = (c2.rank + 11) % 13;
	
	// compare the rotated ranks

	if (rank1 > rank2) return 1;
	if (rank1 < rank2) return -1;
	return 0;
    }

    /*
     * Return true if the cards are equivalent.
     */
    public boolean sameCard(Card c1, Card c2) {
	return (c1.suit == c2.suit && c1.rank == c2.rank);
    }

    /*
     * Returns index of the first location where card appears in deck.
     * Or -1 if it does not appear.  Uses a linear search.
     */
    public static int findCard (Card[] deck, Card card) {
	for(int i = 0; i< deck.length; i++) {
	    if(deck[i].equals(card)) return i;
	}
	return -1;
    }

    /*
     * Returns index of a location where card appears in deck.
     * Or -1 if it does not appear.  Uses a bisection search.
     *
     * Precondition: the cards must be sorted from lowest to highest.
     */
    public static int findBisect(Card[] deck, Card card, int low, int high) {
	if(high < low) return -1;

	int mid =(high + low) / 2;
	int comp = compareCards(card, deck[mid]);

	if(comp == 0) {
	    return mid;
	} else if(comp < 0) {
	    // card is less than deck[mid]; search the first half
	    return findBisect(deck, card, low, mid-1);
	} else {
	    // card is greater; search the second half
	    return findBisect(deck, card, mid+1, high);
	}
    }

    /*
     * Makes an array of 52 cards.
     */
    public static Card[] buildDeck() {
	Card[] deck = new Card [52];
	
	int index = 0;
	for(int suit = 0; suit <= 3; suit++) {
	    for(int rank = 1; rank <= 13; rank++) {
		deck[index] = new Card(suit, rank);
		index++;
	    }
	}
	return deck;
    }

    /*
     * Shuffles the cards in an array.
     */
    public static void shuffleDeck(Card[] deck) {
	for(int i=0; i<deck.length; i++) {
	    int j = randInt(i, deck.length-1);
	    swapCards(deck, i, j);
	}
    }

    /*
     * Chooses a random integer between low and high, including both. 
     */
    public static int randInt(int low, int high) {
	while(true) {
	    int x = (int)(Math.random() * (high-low+1) + low);
	    if(x >= low && x <= high) return x;
	} 
    }

    /*
     * Sorts an array of cards from low to high.
     */
    public static void sortDeck(Card[] deck) {
	for(int i=0; i<deck.length; i++) {
	    int j = indexLowestCard(deck, i, deck.length-1);
	    swapCards(deck, i, j);
	}
    }

    /*
     * Swaps two cards in an array of cards.
     */
    public static void swapCards(Card[] deck, int i, int j) {
	Card temp = deck[i];
	deck[i] = deck[j];
	deck[j] = temp;
    }

    /*
     * Finds the index of the lowest card between low and high,
     * including both.
     */
    public static int indexLowestCard(Card[] deck, int low, int high) {
	int winner = low;
	for (int i=low+1; i<=high; i++) {
	    if (compareCards(deck[i], deck[winner]) < 0) {
		winner = i;
	    }
	}
	return winner;
    }

    /*
     * Makes a new array of cards with a subset of cards from the original.
     * The old array and new array share references to the same card objects.
     */
    public static Card[] subdeck(Card[] deck, int low, int high) {
	Card[] sub = new Card[high-low+1];
	
	for(int i = 0; i<sub.length; i++) {
	    sub[i] = deck[low+i];
	}
	return sub;
    }

    /*
     * Test code.
     */
    public static void main(String[] args) {
	Card[] deck = buildDeck();
	sortDeck(deck);
	printDeck(deck);
	
	Card card1 = new Card(2, 11);
	Card card2 = new Card(1, 1);

	for(int i=0; i<52; i++) {
	    System.out.println(findBisect(deck, deck[i], 0, 51));
	}
	
	Card[] hand = subdeck(deck, 4, 7);
	printDeck(hand);
    }
}



