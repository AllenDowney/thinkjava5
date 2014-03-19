/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Adapted from http://en.wikibooks.org/wiki/Java_Programming/Canvas
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class MyCanvas extends Canvas {

    public static void main(String[] args) {
	// make the frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// add the canvas
	Canvas canvas = new MyCanvas();
        canvas.setSize(400, 400);
        frame.getContentPane().add(canvas);

	// show the frame
	frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
	// draw a circle
	g.fillOval(100, 100, 200, 200);
    }
}

