package locationsinradiuscounter.parameters;

import java.nio.charset.Charset;

/**
 * Parameters for writing output data to a file.
 */
public class WriterParameters {
    /**
     * The full file path where output data should be written.
     */
    private String path;
    /**
     * The Charset to use when writing data to the file.
     */
    private Charset encoding;
    /**
     * Indicates whether data should be appended to the end of the file,
     * or the file should be overwritten.
     */
    private boolean appendToFile;
    
    /**
     * Initializes a new instance of the WriterParameters class
     * with the file path, encoding, and writing mode.
     *
     * @param path the full file path where output should be written
     * @param encoding the Charset to use for writing data to the file
     * @param appendToFile indicates whether data should be appended to the end of the file
     */
    public WriterParameters(String path, Charset encoding, boolean appendToFile) {
        this.path = path;
        this.encoding = encoding;
        this.appendToFile = appendToFile;
    }
    
    /**
     * @return the full file path where output should be written
     */
    public String getPath() {
        return path;
    }
    
    /**
     * @return the Charset used for writing data
     */
    public Charset getEncoding() {
        return encoding;
    }
    
    /**
     * @return true if data should be appended, false to overwrite
     */
    public boolean isAppendToFile() {
        return appendToFile;
    }
}
