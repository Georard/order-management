package pl.edu.pwr.hyartsev.np.model;

/**
 * Represents the personal data of the customer who placed an order.
 * The class serves as a container for user data loaded from an order.
 * @param firstName client's name, String field
 * @param lastName client's surname, String field
 * @param email client's email, String field
 * @param phoneNumber client's phone number, String field
 */
public record CustomerData(String firstName, String lastName, String email, String phoneNumber) {
}
