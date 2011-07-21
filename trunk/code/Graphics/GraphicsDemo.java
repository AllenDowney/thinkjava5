/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */

import java.awt.Frame;
import java.awt.Graphics;

public class GraphicsDemo extends Frame {

    public static void main(String[] args) {
	GraphicsDemo gd = new GraphicsDemo();
	gd.setSize(400, 400);
	gd.setVisible(true);
    }

    /*
     * Special method invoked when the Frame needs to be drawn.
     */
    public void paint(Graphics g) {
	g.fillOval(100, 100, 200, 200);              // draw a circle
    }
}
