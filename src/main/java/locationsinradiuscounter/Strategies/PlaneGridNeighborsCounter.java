package locationsinradiuscounter.Strategies;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import locationsinradiuscounter.Models.Location;
import locationsinradiuscounter.parameters.StrategyParameters;

/**
 * Provides a grid-based algorithm to count neighbors in specified range.
 */
public class PlaneGridNeighborsCounter {
    
    /**
     * Computes the neighbor count for each Location in the input list. Area 
     * inside BoundingBox splits to 2D grid in manner, that distance only 
     * to points in neighbor cells can be less than specified range.
     * @param locations A List of Location objects for which neighbors will be 
     * counted.
     * @param parameters A StrategyParameters instance containing searching 
     * range and BoundingBox.
     * @return An array of int where each element at index i is the count of
     * neighbors found for location with id=i.
     */
    public static int[] getNeighborsCount(ArrayList<Location> locations, StrategyParameters parameters) {
        BigDecimal range = parameters.getRange();
        BigDecimal squareRange = range.multiply(range);
        BigDecimal[] bbox = parameters.getBoundingBox().getBBox();

        BigDecimal xMin = bbox[0];
        BigDecimal yMin = bbox[1];
        BigDecimal xMax = bbox[2];
        BigDecimal yMax = bbox[3];
        
        int[] neighborsCount = new int[locations.size()];

        MathContext MATH_CONTEXT = parameters.getMathContext();
        
        int gridSizeX = (xMax.subtract(xMin)).divide(range, MATH_CONTEXT).intValue();
        int gridSizeY = (yMax.subtract(yMin)).divide(range, MATH_CONTEXT).intValue();
        BigDecimal dx = (xMax.subtract(xMin)).divide(BigDecimal.valueOf(Math.max(gridSizeX, 1)), MATH_CONTEXT);
        BigDecimal dy = (yMax.subtract(yMin)).divide(BigDecimal.valueOf(Math.max(gridSizeY, 1)), MATH_CONTEXT);

        if (dx.compareTo(BigDecimal.ZERO) <= 0) dx = BigDecimal.valueOf(Double.MAX_VALUE);
        if (dy.compareTo(BigDecimal.ZERO) <= 0) dy = BigDecimal.valueOf(Double.MAX_VALUE);

        List<List<List<Location>>> grid = new ArrayList<>();
        for (int i = 0; i < gridSizeX + 3; ++i) {
            List<List<Location>> column = new ArrayList<>();
            for (int j = 0; j < gridSizeY + 3; ++j) {
                column.add(new ArrayList<>());
            }
            grid.add(column);
        }
        for (Location loc : locations) {
            int x = (loc.getX().subtract(xMin)).divide(dx, MATH_CONTEXT).intValue() + 1;
            int y = (loc.getY().subtract(yMin)).divide(dy, MATH_CONTEXT).intValue() + 1;
            for (int i = x - 1; i <= x + 1; ++i) {
                for (int j = y - 1; j <= y + 1; ++j) {
                    for (Location otherLoc : grid.get(i).get(j)) {
                        if (loc.squareDistanceTo(otherLoc).compareTo(squareRange) <= 0) {
                            ++neighborsCount[loc.getId()];
                            ++neighborsCount[otherLoc.getId()];
                        }
                    }
                }
            }
            grid.get(x).get(y).add(loc);
            }
        return neighborsCount;
    }
}
