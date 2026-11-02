package pl.edu.pwr.hyartsev.np.model;

/**
 * Represents the data of a product ordered by a customer in an order.
 * The class serves as a container for data regarding a product ordered by a customer in an order.
 * @param productCode unique identifier of the purchased product, String field
 * @param quantity quantity of goods purchased, double field
 * @param unit unit of measurement for the purchased goods, String field
 */
public record Product(String productCode, double quantity, String unit) {
}
