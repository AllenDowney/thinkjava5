/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */

import java.io.*;

public class Words {

    public static void main(String[] args) {
	
	try {
	    processFile("words.txt");
	} catch(Exception ex) {
	    System.out.println("That didn't work.  Here's why:");
	    ex.printStackTrace();
	}
    }

    public static void processFile(String filename)
        throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(filename);
        BufferedReader in = new BufferedReader(fileReader);

        while (true) {
            String s = in.readLine();
            if (s == null) break;
            System.out.println(s);
        }
    }
}