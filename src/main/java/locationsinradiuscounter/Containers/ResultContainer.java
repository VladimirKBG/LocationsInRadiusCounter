package locationsinradiuscounter.Containers;

import java.util.ArrayList;
import locationsinradiuscounter.Models.Location;

/**
 * Holds the results of the neighbors counting process.
 */
public class ResultContainer {
    /** 
     * All locations that were considered in the neighbor-counting algorithm. 
     */
    private ArrayList<Location> locations;
    /** 
     * An array of neighbors count with indexes corresponding to locations id. 
     */
    private int[] neighborsCount;
    /** 
     * The subset of locations with the highest neighbor counts. 
     */
    private ArrayList<Location> optimalLocations;
    
    /**
     * Initializes a new instance of the ResultContainer class.
     * @param locations all locations that were considered in the neighbor-counting algorithm
     * @param optimalLocations  subset of locations with the highest neighbor counts
     * @param neighborsCount array of neighbors count with indexes corresponding to locations id
     */
    public ResultContainer(ArrayList<Location> locations,
                           ArrayList<Location> optimalLocations,
                           int[] neighborsCount) {
        this.locations = locations;
        this.optimalLocations = optimalLocations;
        this.neighborsCount = neighborsCount;
    }

    public ArrayList<Location> getOptimalLocations() {
        return optimalLocations;
    }
    
    public ArrayList<Location> getLocations() {
        return locations;
    }

    public int[] getNeighborsCount() {
        return neighborsCount;
    }
}
