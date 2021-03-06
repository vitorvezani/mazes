package com.github.danieltex.samples;

import static com.github.danieltex.algorithms.MazeCarvers.SIDE_WINDER;

import javax.imageio.ImageIO;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.ColoredGrid;
import com.github.danieltex.grid.Distances;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColoringLongest {
    public static void main(String[] args) {
        final int rows = 30;
        final int columns = 30;
        final ColoredGrid grid = new ColoredGrid(rows, columns);
        SIDE_WINDER.on(grid);
        
        final Cell start = grid.cellAt(0, 0);
        final Distances distances = start.distances();
        final Cell newStart = distances.max();
        final Distances newDistances = newStart.distances();
        final Cell goal = newDistances.max();
        final Distances maxPath = newDistances.pathTo(goal);

        grid.setDistances(maxPath);

        final BufferedImage img = grid.toPng();
        
        final String filename = "colorizedLongest.png";
        try {
            ImageIO.write(img, "png", new File(filename));
            System.out.println("Saved to " + filename);
        } catch(IOException ex) {
            System.out.println("Faile to write to " + filename);
            ex.printStackTrace();
        }
        
        System.out.println("Dead ends: " + grid.deadEnds().size());
    }
}