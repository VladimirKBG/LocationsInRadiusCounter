package locationsinradiuscounter.parameters;

import java.nio.charset.Charset;

/**
 * Parameters required for reading a file.
 */
public class ReaderParameters {
    private String path;
    private Charset encoding;
    
    /**
     * Initializes a new instance of ReaderParameters with the specified file path and text encoding.
     *
     * @param path     relative path to the file
     * @param encoding Charset to use when reading the file
     */
    public ReaderParameters(String path, Charset encoding) {
        this.path = path;
        this.encoding = encoding;
    }
    
    public String getPath() {
        return path;
    }
    
    public Charset getEncoding() {
        return encoding;
    }
}
