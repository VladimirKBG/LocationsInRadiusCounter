package locationsinradiuscounter.Models;

import java.util.List;
import java.math.BigDecimal;

/**
 * Holds plane limits within which all locations are contained (xMin, yMin, xMax, yMax).
 */
public class BoundingBox {
    private BigDecimal xMin;
    private BigDecimal xMax;
    private BigDecimal yMin;
    private BigDecimal yMax;
    
    /**
     * Initializes an empty instance.
     */
    public BoundingBox() { }
    
    /**
     * Initializes a new instance of the BoundingBox class based on a list of locations.
     *
     * @param locations A list of Location objects to compute the bounding box for.
     */
    public BoundingBox(List<Location> locations) {
        setBBox(locations);
    }
    
    /**
     * Computes and sets the bounding box coordinates based on the provided locations.
     *
     * @param locations A list of Location objects to compute the bounding box for.
     */
    private void setBBox(List<Location> locations) {
        xMin = xMax = locations.get(0).getX();
        yMin = yMax = locations.get(0).getY();

        for (Location loc : locations) {
            BigDecimal x = loc.getX();
            BigDecimal y = loc.getY();

            if (x.compareTo(xMax) > 0) xMax = x;
            if (y.compareTo(yMax) > 0) yMax = y;
            if (x.compareTo(xMin) < 0) xMin = x;
            if (y.compareTo(yMin) < 0) yMin = y;
        }
    }
    
    /**
     * Returns the bounding box as an array of coordinates.
     *
     * @return A BigDecimal array {xMin, yMin, xMax, yMax}.
     */
    public BigDecimal[] getBBox() {
        return new BigDecimal[]{xMin, yMin, xMax, yMax};
    }
}
