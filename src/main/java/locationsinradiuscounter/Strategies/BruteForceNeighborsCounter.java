package locationsinradiuscounter.Strategies;

import java.math.BigDecimal;
import java.util.ArrayList;
import locationsinradiuscounter.Models.Location;
import locationsinradiuscounter.parameters.StrategyParameters;

/**
 * Provides a trivial brute-force algorithm to count neighbors in specified range.
 */
public class BruteForceNeighborsCounter {
    /**
     * The O(N^2) in time complexity algorithm. Comparing each pair of locations 
     * from list and check that square of distance between them less than 
     * specified range. In this case neighborsCounter increasing for this both 
     * locations. 
     * @param locations An ArrayList of Location objects for which neighbors counts 
     * will be computed.
     * @param parameters A StrategyParameters instance containing the searching 
     * range.
     * @return An array of int where each element at index i is the count of
     * neighbors found for location with id=i.
     * 
     * 
     */
    public static int[] getNeighborsCount(ArrayList<Location> locations, StrategyParameters parameters) {
        BigDecimal range = parameters.getRange();
        BigDecimal squareRange = range.multiply(range);
        int n = locations.size();
        int[] neighborsCount = new int[n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                BigDecimal dist2 = locations.get(i).squareDistanceTo(locations.get(j));
                if (dist2.compareTo(squareRange) <= 0) {
                    ++neighborsCount[i];
                    ++neighborsCount[j];
                }
            }
        }
        return neighborsCount;
    }
}
