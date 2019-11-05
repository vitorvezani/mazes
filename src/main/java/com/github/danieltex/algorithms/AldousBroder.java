package com.github.danieltex.algorithms;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

public class AldousBroder {
    public static Grid on(Grid grid) {
        Random rand = new Random();
        int unvisited = grid.size() - 1;
        Cell cell = grid.randomCell();
        while (unvisited > 0) {
            int neighborSize = cell.neighbors().size();
            int index = rand.nextInt(neighborSize);
            Cell neighbor = cell.neighbors().get(index);
            if (neighbor.links().isEmpty()) {
                cell.link(neighbor);
                unvisited--;
            }
            cell = neighbor;
        }
        return grid;
    }
}