/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */


public class Card3 {
    /*
     * Test code.
     */
    public static void main(String[] args) {
	Deck deck = new Deck();
	
	// check sortDeck
	deck.shuffle();
	deck.sort();
	checkSorted(deck);

	// check that findBisect finds each card
	int index;
	for (int i=0; i<52; i++) {
	    index = deck.findBisect(deck.cards[i], 0, 
				    deck.cards.length-1);
	    if (index != i) {
		System.out.println("Not found!");
	    }
	}
	
	// make a subdeck
	Deck hand = deck.subdeck(8, 12);
	deck.print();

	// check that findBisect doesn't find a card that's not there
	Card badCard = new Card(1, 1);
	index = hand.findBisect(badCard, 0, hand.cards.length-1);
	if (index != -1) {
	    System.out.println("Found?");
	}

	// check mergeSort
	deck.shuffle();
	deck.mergeSort();
	checkSorted(deck);
    }

    /*
     * Checks that the deck is sorted.
     */
    public static void checkSorted(Deck deck) {
	for (int i=0; i<51; i++) {
	    int flag = Card.compareCards(deck.cards[i], deck.cards[i+1]);
	    if (flag != -1) {
		System.out.println("Not sorted!");
	    }
	}
    }
}

/*
 * A Card represents a playing card with rank and suit.
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
    public void print() {
	String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6",
			   "7", "8", "9", "10", "Jack", "Queen", "King" };

	System.out.println(ranks[rank] + " of " + suits[suit]);
    }

    /*
     * Return true if the cards are equivalent.
     */
    public boolean sameCard(Card c2) {
	return (suit == c2.suit && rank == c2.rank);
    }

    /*
     * Compares two cards: returns 1 if the first card is greater
     * -1 if the seconds card is greater, and 0 if they are the equivalent.
     */
    public int compareCards(Card c2) {

	// first compare the suits
	if (suit > c2.suit) return 1;
	if (suit < c2.suit) return -1;

	// if the suits are the same,
	// use modulus arithmetic to rotate the ranks
	// so that the Ace is greater than the King.
	// WARNING: This only works with valid ranks!
	int rank1 = (rank + 11) % 13;
	int rank2 = (c2.rank + 11) % 13;
	
	// compare the rotated ranks

	if (rank1 > rank2) return 1;
	if (rank1 < rank2) return -1;
	return 0;
    }
}


/*
 * A Deck contains an array of cards.
 */
class Deck {
    Card[] cards;

    /*
     * Makes a Deck with room for n Cards (but no Cards yet).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }

    /*
     * Makes an array of 52 cards.
     */
    public Deck() {
	this.cards = new Card [52];
	
	int index = 0;
	for (int suit = 0; suit <= 3; suit++) {
	    for (int rank = 1; rank <= 13; rank++) {
		this.cards[index] = new Card(suit, rank);
		index++;
 	    }
	}
    }

    /*
     * Prints a deck of cards.
     */
    public void print() {
	for (int i=0; i<cards.length; i++) {
	    Card.printCard(cards[i]);
	}
    }

    /*
     * Returns index of the first location where card appears in deck.
     * Or -1 if it does not appear.  Uses a linear search.
     */
    public int findCard (Deck deck, Card card) {
	for (int i = 0; i< cards.length; i++) {
	    if (Card.sameCard(card, cards[i])) {
		return i;
	    }
	}
	return -1;
    }

    /*
     * Returns index of a location where card appears in deck.
     * Or -1 if it does not appear.  Uses a bisection search.
     *
     * Searches from low to high, including both.
     *
     * Precondition: the cards must be sorted from lowest to highest.
     */
    public int findBisect(Deck deck, Card card, int low, int high) {
	if (high < low) return -1;

	int mid = (high + low) / 2;
	int comp = Card.compareCards(card, cards[mid]);

	if (comp == 0) {
	    return mid;
	} else if (comp < 0) {
	    // card is less than cards[mid]; search the first half
	    return findBisect(deck, card, low, mid-1);
	} else {
	    // card is greater; search the second half
	    return findBisect(deck, card, mid+1, high);
	}
    }

    /*
     * Chooses a random integer between low and high, including low,
     * not including high. 
     */
    public int randInt(int low, int high) {
	return 0;
    }

    /*
     * Swaps two cards in a deck of cards.
     */
    public void swapCards(Deck deck, int i, int j) {
    }

    /*
     * Shuffles the cards in a deck.
     */
    public void shuffleDeck(Deck deck) {
    }

    /*
     * Sorts a deck from low to high.
     */
    public void sortDeck(Deck deck) {
    }

    /*
     * Finds the index of the lowest card between low and high,
     * including both.
     */
    public int indexLowestCard(Deck deck, int low, int high) {
	return 0;
    }

    /*
     * Makes a new deck of cards with a subset of cards from the original.
     * The old deck and new deck share references to the same card objects.
     */
    public Deck subdeck(Deck deck, int low, int high) {
	Deck sub = new Deck(high-low+1);
	
	for (int i = 0; i<sub.cards.length; i++) {
	    sub.cards[i] = cards[low+i];
	}
	return sub;
    }

    /*
     * Merges two sorted decks into a new sorted deck.
     */
    public Deck merge(Deck d1, Deck d2) {
	return d1;
    }
	
    /*
     * Sort the Deck using merge sort.
     */
    public Deck mergeSort(Deck deck) {
	return deck;
    }
}
