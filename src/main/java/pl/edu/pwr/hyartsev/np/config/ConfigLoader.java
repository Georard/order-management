package pl.edu.pwr.hyartsev.np.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

// Wczytywania konfiguracji (zadanie 3)
public class ConfigLoader {

    private final Configuration config;

    public ConfigLoader() throws ConfigurationException {
        Configurations configs = new Configurations();
        config = configs.properties("appsettings.properties");
    }

    public String getOrdersDirectory() {
        String ordersDirectoryPath = config.getString("orders.directory");

        if (ordersDirectoryPath == null || ordersDirectoryPath.isBlank()) {
            System.err.println("Brak ustawienia orders.directory w konfiguracji!");
            throw new IllegalStateException(
                    "Error: The key 'orders.directory' was not found or is empty!");
        }

        System.out.println("Pomyslnie wczytano sciezke do zamowien: " + ordersDirectoryPath);

        return ordersDirectoryPath;
    }
}
