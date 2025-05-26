package locationsinradiuscounter.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import locationsinradiuscounter.Containers.InputDataContainer;
import locationsinradiuscounter.Containers.ResultContainer;
import locationsinradiuscounter.Models.Location;

/**
 * Finds neighbor counts for locations and selects sorting method.
 */
public class LocationsFinder {
    /**
     * Selects the outputCount locations with greatest neighbors count.
     * Sorting method depends on input and output locations counts.
     *
     * @param input An InputDataContainer containing the locations and 
     * the searching range.
     * @param outputCount The maximum number of locations to return.
     * @return A ResultContainer holding an input set of locations, list of 
     * output locations and array of neighbors count for each location.
     */
    public static ResultContainer findLocationsByNeighborsThenById(InputDataContainer input, int outputCount) {
        BigDecimal range = input.getRange();
        ArrayList<Location> locations = input.getLocations();
        outputCount = Math.min(outputCount, locations.size());
        int[] neighborsCount = LocationsNeighborsCounter.getLocationsWithMaxNeighbors(locations, range);
        ArrayList<Location> outputLocations;
        if (outputCount < Math.log(locations.size()) / Math.log(2)) {
            outputLocations = LocationsSorting.selectionSortByNeighbors(locations, neighborsCount, outputCount);
        } else {
            outputLocations = LocationsSorting.defaultSortByNeighbors(locations, neighborsCount, outputCount);
        }

        return new ResultContainer(locations, outputLocations, neighborsCount);
    }
}
