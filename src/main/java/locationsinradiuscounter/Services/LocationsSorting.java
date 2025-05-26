package locationsinradiuscounter.Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import locationsinradiuscounter.Models.Location;

/**
 * Provides methods for sorting a list of Location objects.
 */
public class LocationsSorting {
    /**
     * Performs an in-place selection sort on the list of locations,
     * ordering them in descending order based on their neighbor counts,
     * and returns the first outputCount elements.
     * @param locations An input list of Location objects.
     * @param neighborsCount An array where the element at index id represents 
     * the neighbors count for the location with id.
     * @param outputCount An output number of locations.
     * @return A new list containing the first outputCount locations
     * sorted in descending order by neighbors count.
     */
    public static ArrayList<Location> selectionSortByNeighbors(
            ArrayList<Location> locations,
            int[] neighborsCount,
            int outputCount) {
        outputCount = Math.min(outputCount, locations.size());
        for (int i = 0; i < outputCount; ++i) {
            int maxIdx = i;
            long maxCount = neighborsCount[locations.get(i).getId()];
            for (int j = i + 1; j < locations.size(); ++j) {
                int cnt = neighborsCount[locations.get(j).getId()];
                if (cnt > maxCount) {
                    maxCount = cnt;
                    maxIdx = j;
                }
            }
            Location tmp = locations.get(i);
            locations.set(i, locations.get(maxIdx));
            locations.set(maxIdx, tmp);
        }
        return new ArrayList<>(locations.subList(0, outputCount));
    }
    
    /**
     * Performs built-in descending sorting by neighbors count, then ascending 
     * by id.
     * @param locations An input list of Location objects.
     * @param neighborsCount An array where the element at index id represents 
     * the neighbors count for the location with id.
     * @param outputCount An output number of locations.
     * @return A new list containing the first outputCount locations
     * sorted in descending order by neighbors count.
     */
    public static ArrayList<Location> defaultSortByNeighbors(
            ArrayList<Location> locations,
            int[] neighborsCount,
            int outputCount) 
    {
        return (ArrayList<Location>) locations.stream()
                .sorted(Comparator
                    .comparingLong((Location loc) -> neighborsCount[loc.getId()])
                    .reversed()
                    .thenComparingInt(Location::getId))
                .limit(outputCount)
                .collect(Collectors.toList());
    }
}
