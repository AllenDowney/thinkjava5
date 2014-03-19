/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Allen Downey
 * Modified by Allen Downey for an exercise in Think Java (thinkapjava.com)
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChaseRunner
{
    public static int xdiff(Bug bug1, Bug bug2) {
	int x1 = bug1.getLocation().getCol();
	int x2 = bug2.getLocation().getCol();
	return x2 - x1;
    }

    public static int ydiff(Bug bug1, Bug bug2) {
	int y1 = bug1.getLocation().getRow();
	int y2 = bug2.getLocation().getRow();
	return y2 - y1;
    }

    public static double distance(Bug bug1, Bug bug2) {
	int dx = xdiff(bug1, bug2);
	int dy = ydiff(bug1, bug2);
	return Math.sqrt(dx*dx + dy*dy);
    }

    public static void turnToward(Bug bug1, Bug bug2) {
	int dx = xdiff(bug1, bug2);
	int dy = ydiff(bug1, bug2);
	double angle = Math.atan2(dy, dx);
	int degrees = (int) Math.round(angle * 180 / Math.PI);
	bug1.setDirection(degrees + 90);
    }

    public static void moveToward(Bug bug1, Bug bug2) {
	turnToward(bug1, bug2);
	if (bug1.canMove()) {
	    bug1.move();
	}
    }

    public static void moveBugs(Bug bug1, Bug bug2, int n) {
	while (n > 0) {
	    moveToward(bug1, bug2);
	    moveToward(bug2, bug1);
	    n = n-1;
	}
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

	Bug redBug = new Bug();
	world.add(redBug);

	Bug blueBug = new Bug(Color.blue);
	world.add(blueBug);

	System.out.println(distance(redBug, blueBug));
	moveBugs(redBug, blueBug, 5);
	System.out.println(distance(redBug, blueBug));

        world.show();
    }
}
