package locationsinradiuscounter.Services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import locationsinradiuscounter.Config;
import locationsinradiuscounter.Models.BoundingBox;
import locationsinradiuscounter.Models.Location;
import locationsinradiuscounter.Strategies.BruteForceNeighborsCounter;
import locationsinradiuscounter.Strategies.PlaneGridNeighborsCounter;
import locationsinradiuscounter.parameters.StrategyParameters;

/**
 * Provides methods to count neighbors for a list of locations.
 */
public class LocationsNeighborsCounter {
     /**
     * Computes the number of neighbors within the search range for each location.
     *
     * @param locations An input list of Location objects.
     * @param range A search range for neighbor counting.
     * @return An array of ints, where each element corresponds to the number 
     * of neighbors found for the location at the same index in the input list.
     */
    public static int[] getLocationsWithMaxNeighbors(ArrayList<Location> locations, BigDecimal range) {
        int n = locations.size();
        if (n == 0) {
            return new int[0];
        }
        if (n == 1) {
            return new int[]{0};
        }
        int[] neighborsCount;
        int accuracy = Config.getInstance().getCalculationAccuracy();
        StrategyParameters params = new StrategyParameters(range, accuracy);
        int threshold = Config.getInstance().getBruteForceThreshold();
        if (n <= threshold) {
            neighborsCount = BruteForceNeighborsCounter.getNeighborsCount(locations, params);
        } else {
            BoundingBox bbox = new BoundingBox(locations);
            var limits = bbox.getBBox();
            BigDecimal xMin = limits[0];
            BigDecimal yMin = limits[1];
            BigDecimal xMax = limits[2];
            BigDecimal yMax = limits[3];

            MathContext MATH_CONTEXT = new MathContext(accuracy, RoundingMode.HALF_UP);
            BigDecimal widthCells  = xMax.subtract(xMin, MATH_CONTEXT).divide(range, MATH_CONTEXT);
            BigDecimal heightCells = yMax.subtract(yMin, MATH_CONTEXT).divide(range, MATH_CONTEXT);
            BigDecimal cellCountEstimate = widthCells.multiply(heightCells, MATH_CONTEXT);
            if (cellCountEstimate.compareTo(new BigDecimal(n)) > 0) {
                neighborsCount = BruteForceNeighborsCounter.getNeighborsCount(locations, params);
            } else {
                params = new StrategyParameters(range, accuracy, bbox);
                neighborsCount = PlaneGridNeighborsCounter.getNeighborsCount(locations, params);
            }
        }
        return neighborsCount;
    }
}
