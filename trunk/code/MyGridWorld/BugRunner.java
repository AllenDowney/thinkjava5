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

import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner
{
    public static void moveBug(Bug bug, int n) {
	if (n == 0) {
	    return;
	}
	if (bug.canMove()) {
	    bug.move();
	} else {
	    bug.turn();
	}
	moveBug(bug, n-1);
    }

    public static void randomBug(Bug bug, int n) {
	if (n == 0) {
	    return;
	}
	int x = (int) (Math.random() * 4);
	bug.setDirection(x * 90);

	if (bug.canMove()) {
	    bug.move();
	}
	randomBug(bug, n-1);
    }

    public static void colorBug(Bug bug) {
	int x = bug.getLocation().getCol();
	int y = bug.getLocation().getRow();
	Color c = new Color(x*255/9, 0, y*255/9);
	bug.setColor(c);
    }

    public static void makeBugs(ActorWorld world, int n) {
	if (n == 0) {
	    return;
	}
	Bug bug = new Bug();
	world.add(bug);
	colorBug(bug);
	makeBugs(world, n-1);
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
	Bug redBug = new Bug();
	world.add(redBug);
	moveBug(redBug, 10);

	Bug blueBug = new Bug(Color.blue);
	world.add(blueBug);
	randomBug(blueBug, 10);

	Color purple = new Color(148, 0, 211);
	Bug purpleBug = new Bug(purple);
	world.add(purpleBug);

	makeBugs(world, 3);

        world.show();
    }
}
