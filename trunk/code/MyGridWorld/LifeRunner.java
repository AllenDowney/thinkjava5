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

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class LifeRunner
{
    public static void main(String[] args) {
	makeLifeWorld(9, 9);
    }

    /**
     * Makes a Game of Life grid with an r-pentomino.
     */
    public static void makeLifeWorld(int rows, int cols) {
	Grid<Actor> grid = new BoundedGrid<Actor>(rows, cols);
	ActorWorld world = new ActorWorld(grid);
        makeRocks(world);
	makePentomino(world, rows/2, cols/2);
        world.show();
    }

    /**
     * Fills the grid with LifeRocks.
     */
    public static void makeRocks(ActorWorld world) {
	Grid<Actor> grid = world.getGrid();
	int rows = grid.getNumRows();
	int cols = grid.getNumCols();
	for (int i=0; i<rows; i++) {
	    for (int j=0; j<cols; j++) {
		Location loc = new Location(i, j);
		world.add(loc, new LifeRock());
	    }
	}
    }

    /**
     * Makes an r-pentomino centered on the given location.
     */
    public static void makePentomino(ActorWorld world, int x, int y) {
	Grid<Actor> grid = world.getGrid();
	ArrayList<Location> locs = new ArrayList<Location>();
	locs.add(new Location(x-1, y));
	locs.add(new Location(x-1, y+1));
	locs.add(new Location(x,   y-1));
	locs.add(new Location(x,   y));
	locs.add(new Location(x+1, y));

	for (Location loc: locs) {
	    LifeRock rock = (LifeRock) grid.get(loc);
	    rock.setAlive();
	}	 
    }
    
}