package locationsinradiuscounter.Writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import locationsinradiuscounter.Containers.ResultContainer;
import locationsinradiuscounter.Models.Location;
import locationsinradiuscounter.parameters.WriterParameters;

/**
 * Write optimal locations IDs and their neighbors counts.
 */
public class OptimalLocationsWriter {
    /**
     * Writes optimal location’s identifiers and its neighbor count to the output file.
     * @param result     a ResultContainer containing the list of optimal locations
     *                   and their neighbor counts.
     * @param parameters a WriterParameters instance specifying the output file path,
     *                   text encoding, and whether to append to or overwrite the file.
     * @return an integer status code.
     */
    public static int writeToFile(ResultContainer result, WriterParameters parameters) {
        Charset encoding = parameters.getEncoding();
        boolean append = parameters.isAppendToFile();
        String path = parameters.getPath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, encoding, append))) {

            for (Location loc : result.getOptimalLocations()) {
                int neighborsCount = result.getNeighborsCount()[loc.getId()];
                writer.write(loc.getId() + " " + neighborsCount + "\n");
            }

        } catch (IOException e) {
            return 1;
        }
        return 0;
    }
}
