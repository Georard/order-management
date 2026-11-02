package pl.edu.pwr.hyartsev.np;


import org.apache.commons.configuration2.ex.ConfigurationException;
import pl.edu.pwr.hyartsev.np.config.ConfigLoader;
import pl.edu.pwr.hyartsev.np.io.OrderReader;
import pl.edu.pwr.hyartsev.np.model.OrderRequest;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            // zadanie 3
            ConfigLoader configLoader = new ConfigLoader();
            String ordersDirectory = configLoader.getOrdersDirectory();

            // zadanie 4
            OrderReader orderReader = new OrderReader();
            List<Path> jsonFiles = orderReader.findJsonFiles(ordersDirectory);

            // zadanie 5
            for (Path jsonFile : jsonFiles) {
                OrderRequest order = orderReader.readOrder(jsonFile);

                order.assignDeterministicOrderNumber();

                System.out.println("Plik: " + jsonFile.getFileName() + " -> indywidualny numer zamówienia: " + order.getOrderNumber());
            }

        } catch (ConfigurationException e) {
            throw new RuntimeException("Nie udalo sie wczytac konfiguracji: " + e.getMessage(), e);
        }
    }
}
