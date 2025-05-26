package locationsinradiuscounter.Containers;

import java.math.BigDecimal;
import java.util.ArrayList;
import locationsinradiuscounter.Models.Location;

/**
 * Holds a List of locations and searching range.
 */
public class InputDataContainer {
    private ArrayList<Location> locations;
    private int size;
    private BigDecimal range;
    
    /**
     * Initializes a new instance of the InputDataContainer class allocate space
     * for locations.
     * @param size  the expected number of locations
     * @param range the range for neighbor-counting algorithms
     */
    public InputDataContainer(int size, BigDecimal range) {
        this.size = size;
        this.range = range;
        this.locations = new ArrayList<>(size);
    }
    
    /**
     * Adds a new location to the container.
     * @param location the Location instance to add
     * @return status of location adding
     */
    public int addLocation(Location location) {
        locations.add(location);
        return 0;
    }
    
    /** 
     * @return locations list 
     */
    public ArrayList<Location> getLocations() {
        return locations;
    }
    
    /** 
     * @return size of locations list 
     */
    public int getSize() {
        return size;
    }
    
    /** 
     * @return searching range 
     */
    public BigDecimal getRange() {
        return range;
    }
}
