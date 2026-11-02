package pl.edu.pwr.hyartsev.np.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 *  Public class, which is responsible for configuration input
 */
public class ConfigLoader {

    private final Configuration config;

    /**
     * Simple configuration loading from appsettings.properties via Configurations class tools
     * @throws ConfigurationException when configuration wasn't read correctly
     */
    public ConfigLoader() throws ConfigurationException {
        Configurations configs = new Configurations();
        config = configs.properties("appsettings.properties");
    }

    /**
     * gets orders directory from read-out configuration
     * @throws IllegalStateException when there is no path to the orders directory, or it is empty
     * @return String ordersDirectoryPath which is the direct path to the orders directory
     */
    public String getOrdersDirectory() {
        String ordersDirectoryPath = config.getString("orders.directory");

        if (ordersDirectoryPath == null || ordersDirectoryPath.isBlank()) {
            System.err.println("Brak ustawienia orders.directory w konfiguracji!");
            throw new IllegalStateException(
                    "Wystąpił błąd: 'orders.directory' nie został znaleziony lub jest pusty!");
        }

        System.out.println("Pomyślnie wczytano ścieżkę do zamówień: " + ordersDirectoryPath);

        return ordersDirectoryPath;
    }
}
