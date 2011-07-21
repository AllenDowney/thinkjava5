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
import java.awt.Rectangle;

public class Mickey extends Frame {

    public static void main(String[] args) {
	Mickey gd = new Mickey();
	gd.setSize(400, 400);
	gd.setBackground(Color.white);
	gd.setVisible(true);
    }

    public void fillOval(Graphics g, Rectangle bb) {
	g.fillOval(bb.x, bb.y, bb.width, bb.height);
    }

    public void mickey(Graphics g, Rectangle bb) {
	fillOval(g, bb);

	int dx = bb.width/2;
	int dy = bb.height/2;
	Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);

	half.translate(-dx/2, -dx/2);
	fillOval(g, half);

	half.translate(dx*2, 0);
	fillOval(g, half);
    }

    public void paint(Graphics g) {
	Rectangle bb = new Rectangle(100, 150, 200, 200);
	g.setColor(Color.gray);
	mickey(g, bb);
    }
}
