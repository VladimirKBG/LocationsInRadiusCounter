package locationsinradiuscounter.Readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import locationsinradiuscounter.Containers.InputDataContainer;
import locationsinradiuscounter.Models.Location;
import locationsinradiuscounter.parameters.ReaderParameters;

/**
 * Reads locations data from a text file.
 */
public class LocationsReader {
    /**
     * Reads header line with locations count and searching range.
     * Then reads subsequent lines with locations coordinates.
     * Assumes space-separated values.
     * @param parameters a ReaderParameters instance containing file path and text encoding
     * @return a InputDataContainer with locations data and searching range
     * @throws IOException if an I/O error occurs or data is invalid
     */
    public static InputDataContainer readFromFile(ReaderParameters parameters) throws IOException {
        Path inputPath = Paths.get(parameters.getPath());
        if (!Files.exists(inputPath)) {
            throw new FileNotFoundException(
                "Expected input data file: " + inputPath.toAbsolutePath()
            );
        }

        try (BufferedReader br = Files.newBufferedReader(inputPath, parameters.getEncoding())) {
            String header = br.readLine();
            if (header == null) {
                throw new IOException("Input file is empty.");
            }

            String[] headerTokens = header.trim().split("\\s+");
            if (headerTokens.length < 2) {
                throw new IOException(
                    "Invalid first line: " + header +
                    " (Two numbers with space-separator expected)."
                );
            }

            int N;
            BigDecimal R;
            try {
                N = Integer.parseInt(headerTokens[0]);
                R = new BigDecimal(headerTokens[1]);
            } catch (NumberFormatException ex) {
                throw new IOException("Invalid first line: can't parse header.", ex);
            }
            if (R.compareTo(BigDecimal.ZERO) < 0 || N < 0) {
                throw new IOException("Expected R, N > 0.");
            }

            InputDataContainer inputData = new InputDataContainer(N, R);

            for (int i = 0; i < inputData.getSize(); i++) {
                String line = br.readLine();
                if (line == null) {
                    throw new IOException(
                        "Only " + i + " lines with coordinates found, but " +
                        inputData.getSize() + " expected."
                    );
                }

                String[] tokens = line.trim().split("\\s+");
                if (tokens.length < 2) {
                    throw new IOException(
                        "Invalid line " + (i + 1) +
                        ": expected two numbers, got " + line + "."
                    );
                }

                try {
                    BigDecimal x = new BigDecimal(tokens[0]);
                    BigDecimal y = new BigDecimal(tokens[1]);
                    inputData.addLocation(new Location(x, y));
                } catch (NumberFormatException ex) {
                    throw new IOException(
                        "Invalid line " + (i + 1) + " with coordinates: can't parse.", ex
                    );
                }
            }
            return inputData;
        }
    }
}
