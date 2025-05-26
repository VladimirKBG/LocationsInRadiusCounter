package locationsinradiuscounter.Models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import locationsinradiuscounter.Config;

/**
 * Holds a 2D location with coordinates (x, y)
 * and methods for computing square of distances to other locations.
 */
public class Location {
    /** 
     * Auto-incrementing ID counter, initialized from config 
     */
    private static int count = Config.getInstance().getLocationIdBase();
    /** 
     * Unique identifier of this location 
     */
    private int id;
    /** 
     * X coordinate 
     */
    private BigDecimal x;
    /** 
     * Y coordinate 
     */
    private BigDecimal y;
    private static final MathContext MATH_CONTEXT = new MathContext(6, RoundingMode.HALF_UP);
    
    /**
     * Initializes a new instance of Location with the given (x, y) coordinates
     * and assigns the next incremental id.
     *
     * @param x X coordinate (must be > 0)
     * @param y Y coordinate (must be > 0)
     */
    public Location(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
        this.id = count++;
    }
    
    /** @return id of location */
    public int getId() {
        return id;
    }
    
    /** @return X coordinate */
    public BigDecimal getX() {
        return x;
    }
    
    /** @return Y coordinate */
    public BigDecimal getY() {
        return y;
    }
    
    /**
     * Calculates the square of the distance to another location.
     *
     * @param other the other location
     * @return the square of the distance
     */
    public BigDecimal squareDistanceTo(Location other) {
        BigDecimal dx = x.subtract(other.x, MATH_CONTEXT);
        BigDecimal dy = y.subtract(other.y, MATH_CONTEXT);
        return dx.multiply(dx, MATH_CONTEXT).add(dy.multiply(dy, MATH_CONTEXT), MATH_CONTEXT);
    }
}
