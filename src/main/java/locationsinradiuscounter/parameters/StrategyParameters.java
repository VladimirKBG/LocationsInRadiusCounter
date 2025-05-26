package locationsinradiuscounter.parameters;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import locationsinradiuscounter.Models.BoundingBox;

/**
 * Holds the parameters used by a neighbors searching strategy.
 */
public class StrategyParameters {
    /**
     * The range within which locations are considered as near.
     */
    private BigDecimal range;
    /**
     * The BoundingBox instance with searching area plane limits.
     */
    private BoundingBox boundingBox;
    private MathContext mc;
    
    /**
     * Initializes a new instance of the StrategyParameters class
     * with the searching range and default bounding box.
     *
     * @param range The range within which locations are considered as near.
     * @param accuracy An accuracy for decimal calculations.
     */
    public StrategyParameters(BigDecimal range, int accuracy) {
        this(range, accuracy, null);
    }
    
    /**
     * Initializes a new instance of the StrategyParameters class
     * with the searching range and optional bounding box.
     *
     * @param range The range within which locations are considered as near.
     * @param accuracy An accuracy for decimal calculations.
     * @param bbox  The BoundingBox instance with searching area plane limits,
     *              or null if bounding box not used.
     */
    public StrategyParameters(BigDecimal range, int accuracy, BoundingBox bbox) {
        this.range = range;
        this.mc = new MathContext(accuracy, RoundingMode.HALF_UP);
        this.boundingBox = (bbox != null) ? bbox : new BoundingBox();
    }
    
    /**
     * @return the range within which locations are considered as near.
     */
    public BigDecimal getRange() {
        return range;
    }
    
    /**
     * @return the BoundingBox instance with searching area plane limits.
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
    
    public MathContext getMathContext() {
        return mc;
    }
}
