/* 
 * This program is part of an exercise in Think Java (thinkapjava.com)
 * Copyright(c) 2011 Allen B. Downey (http://allendowney.com)
 *
 * It is based on the AP(r) Computer Science GridWorld Case Study.
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
 * @author Allen B. Downey
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


/**
 * Represents a Rock in Conway's Game of Life.
 * 
 * The color of the rock represents whether the Rock is alive or dead.
 *
 * In order to implement simultaneous updates, act() has two phases:
 * the first time it is invoked, it counts the neighbors; the second
 * time it updates the status of the Rock.
 */
public class LifeRock extends Rock {
    static final Color LIVE_COLOR = Color.blue;
    static final Color DEAD_COLOR = Color.white;

    private int phase;
    private int numNeighbors;

    /**
     * Makes a dead rock.
     */
    public LifeRock() {
	super();
	phase = 1;
	setDead();
    }

    /**
     * Returns true if the Rock is alive.
     */
    public boolean isAlive() {
	return getColor() == LIVE_COLOR;
    }

    /**
     * Makes the Rock alive.
     */
    public void setAlive() {
	setColor(LIVE_COLOR);
    }

    /**
     * Makes the Rock dead.
     */
    public void setDead() {
	setColor(DEAD_COLOR);
    }

    /**
     * Check what phase we're in and calls the appropriate method.
     * Moves to the next phase.
     */
    public void act() {
	if (phase == 1) {
	    numNeighbors = countLiveNeighbors();
	    phase = 2;
	} else {
	    updateStatus();
	    phase = 1;
	}
    }

    /**
     * Counts the number of live neighbors.
     */
    public int countLiveNeighbors() {
	Location loc = getLocation();
	Grid<Actor> grid = getGrid();
	ArrayList<Actor> neighbors = grid.getNeighbors(loc);

	int count = 0;
	for (Actor neighbor: neighbors) {
	    LifeRock rock = (LifeRock) neighbor;
	    if (rock.isAlive()) {
		count++;
	    }
	}
	return count;
    }

    /**
     * Updates the status of the Rock (live or dead) based on the number
     * of neighbors.
     */
    public void updateStatus() {
	if (isAlive()) {
	    if (numNeighbors < 2 || numNeighbors > 3) {
		setDead();
	    }
	} else {
	    if (numNeighbors == 3) {
		setAlive();
	    }
	}
    }
}
