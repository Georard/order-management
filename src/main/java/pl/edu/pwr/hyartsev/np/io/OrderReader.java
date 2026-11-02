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

// wczytanie wszystkich plików o rozszerzeniu .json z katalogu (Zadanie 4)
public class OrderReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Pomaga otrzymać wszystkie ścieżki do plików .json w wybranym katalogu
     * @param directoryPath ścieżka do katalogu z plikami .json do wczytania
     * @return listę ścieżek do wszystkich plików .json w katalogu
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
            throw new RuntimeException("Wystapil blad podczas przeszukiwania katalogu: " + e.getMessage());
        }
    }

    /**
     *
     * @param jsonFilePath ścieżka do konkretnego pliku .json
     * @return zwraca żądanie o zamówienie
     */
    public OrderRequest readOrder(Path jsonFilePath) {
        try {
            return objectMapper.readValue(jsonFilePath, OrderRequest.class);
        } catch (JacksonException e) {
            throw new RuntimeException("Wystapil blad podczas deserializacji pliku: "
                    + jsonFilePath.getFileName() + ": " + e.getMessage(), e);
        }
    }
}