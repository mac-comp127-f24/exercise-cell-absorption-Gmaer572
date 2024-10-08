package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private Random rand = new Random();
    private List <Cell> cells;
    private CanvasWindow canvas;
   
    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            for (Cell cell : cells){
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            cell.moveAround(canvasCenter);

            canvas.draw();
            canvas.pause(10);
            
            }
            handleCellInteraction();
        }
    }

    private void populateCells() {
        double size = rand.nextInt(5) + 2;
        cells = new ArrayList();
        for (int i = 0; i < 200; i++){
        Cell cell = new Cell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            cells.add(cell);
            cell.AddCellToCanvas(canvas);
        }
    }
    

    private static double sqr(double x) {
        return x * x;
    }
    private void handleCellInteraction() {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell0 = cells.get(i);
            for (int j = i + 1; j < cells.size(); j++) {
                Cell cell1 = cells.get(j);
                cell0.interactWith(cell1);
            }
        }
    }

    
}
