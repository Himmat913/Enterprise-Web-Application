import java.util.Optional;

public class Q2_OptionalEmail {
    public static void main(String[] args) {
        Optional<String> email = Optional.of("himantveer@example.com");
        Optional<String> noEmail = Optional.empty();

        System.out.println("Optional with email: " + email);
        System.out.println("Empty optional: " + noEmail);

        email.ifPresent(e -> System.out.println("Email: " + e));
        noEmail.ifPresent(e -> System.out.println("Email: " + e));

        String defaultEmail = noEmail.orElse("noemail@example.com");
        System.out.println("Email with default: " + defaultEmail);

        try {
            String value = noEmail.orElseThrow(() -> new IllegalArgumentException("Email address is missing"));
            System.out.println(value);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        String present = email.orElseThrow(() -> new IllegalArgumentException("Email address is missing"));
        System.out.println("Email found: " + present);
    }
}
