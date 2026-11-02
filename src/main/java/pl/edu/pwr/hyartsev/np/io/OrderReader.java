package pl.edu.pwr.hyartsev.np.io;

import pl.edu.pwr.hyartsev.np.model.OrderRequest;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Provides functionalities to find all .json files in the directory and read them to the OrderRequest
 */
public class OrderReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Helps retrieve all paths to .json files in a selected directory
     * @param directoryPath path to the directory containing .json files to be loaded
     * @throws RuntimeException when an error occurs while searching the directory
     * @return a list of paths to all .json files in a directory or empty list if there is no directory with such name
     */
    public List<Path> findJsonFiles(String directoryPath) {
        Path dir = Paths.get(directoryPath);

        // jeśli nie istnieje taki folder czy to nie katalog
        if(!Files.exists(dir) || !Files.isDirectory(dir)){
            System.err.println("Nie istnieje katalog za podana sciezka lub podana sciezka nie istnieje");
            return Collections.emptyList();
        }

        // przeszukuje cały katalog z użyciem strumienia
        try (Stream<Path> paths = Files.list(dir)) {
            return paths
                    // odfiltrować zwykłe pliki
                    .filter(Files::isRegularFile)
                    // filtruje pliki tylko formatu .json
                    .filter(path -> path.getFileName().toString().toLowerCase().endsWith(".json"))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Wystąpił błąd podczas przeszukiwania katalogu: " + e.getMessage());
        }
    }

    /**
     * Reads orders from .json files to the OrderRequests
     * @param jsonFilePath path to a specific .json file
     * @throws RuntimeException when an exception during deserialization proces occurs
     * @return returns a request for an order
     */
    public OrderRequest readOrder(Path jsonFilePath) {
        try {
            return objectMapper.readValue(jsonFilePath, OrderRequest.class);
        } catch (JacksonException e) {
            throw new RuntimeException("Wystąpił błąd podczas deserializacji pliku: "
                    + jsonFilePath.getFileName() + ": " + e.getMessage(), e);
        }
    }
}