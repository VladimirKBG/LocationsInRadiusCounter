package locationsinradiuscounter;

/**
 * Configuration holder.
 */
public class Config {
    
    private static final Config INSTANCE = new Config();
    
    private Config() { }
    
    /** Return instance of the configuration.
     * @return  instance of the configuration 
     */
    public static Config getInstance() {
        return INSTANCE;
    }
    
    /** Threshold above which the brute-force neighbor search will switch to KD-tree. */
    private final int bruteForceThreshold = 10;
    /** Number of output locations. */
    private final int outputLocationsCount = 10;
    /** Base value for assigning location IDs. */
    private final int locationIdBase = 0;
    /** Relative path to the input data file (UTF-8 text). */
    private final String inputDataPath = "input.txt";
    /** Relative path to the output data file (UTF-8 text). */
    private final String outputDataPath = "output.txt";
    private final int calculationAccuracy = 6;
    
    public int getBruteForceThreshold() {
        return bruteForceThreshold;
    }
    
    public int getOutputLocationsCount() {
        return outputLocationsCount;
    }
    
    public int getLocationIdBase() {
        return locationIdBase;
    }
    
    public String getInputDataPath() {
        return inputDataPath;
    }
    
    public String getOutputDataPath() {
        return outputDataPath;
    }
    
    public int getCalculationAccuracy() {
        return calculationAccuracy;
    }
}
