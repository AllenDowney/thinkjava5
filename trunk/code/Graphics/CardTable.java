/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class CardTable extends Frame {
    private Image[][] images;
    private int cardWidth, cardHeight; 
    private Insets insets;

   /*
    * Creates a CardTable.
    * cardset is the String name of the folder that contains the card images.
    */
    public CardTable(String cardset) {
	setBackground(new Color(0x088A4B));
	
	// if the user closes the window, exit.
	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

	// create an array of card images
	String suits = "cdhs";
	images = new Image[4][14];
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
		char c = suits.charAt(suit);
		String s = String.format("%s/%02d%c.gif", cardset, rank, c);
		images[suit][rank] = new ImageIcon(s).getImage();
            }
        }

	// get the width and height of the cards and set the size of
	// the frame accordingly
	cardWidth = images[1][1].getWidth(null);
	cardHeight = images[1][1].getHeight(null);

	// set the size temporarily to get the insets
	setSize(14 * cardWidth, 4 *cardHeight);
	setVisible(true);
        insets = getInsets();
	
	// set the size properly
	setTableSize(14.0, 4.0);
    }

   /*
    * Sets the table size.
    * x and y are in units of card width/height.
    */
    public void setTableSize(double x, double y) {
	setSize((int)(x * cardWidth) + insets.left + insets.right, 
		(int)(y *cardHeight) + insets.top + insets.bottom);
    }

   /*
    * Draws a card at the given coordinates.
    * x and y are in units of card width/height.
    */
    public void drawCard(Graphics g, int suit, int rank, double x, double y) {
	Image image = images[suit][rank];
	g.drawImage(image, 
		    (int)(x * cardWidth) + insets.left, 
		    (int)(y * cardHeight) + insets.top,
		    null);
    }

    /*
     * Special method invoked when the Frame needs to be drawn.
     */
    public void paint(Graphics g) {
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
		double x = rank-1 + suit/5.0;
		double y = suit / 2.0;
		drawCard(g, suit, rank, x, y);
            }
        }
    }

    public static void main(String[] args) {
	String cardset = "cardset-oxymoron";
	CardTable table = new CardTable(cardset);

    }

}
