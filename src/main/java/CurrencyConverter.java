import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        // API URL con la clave API
        String apiKey = "1a115c7d36957996573604a6";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        // Crear el cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // Obtener la respuesta de la API
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            // Mostrar las tasas de cambio
            System.out.println("Tasa de cambio USD: " + conversionRates.get("USD").getAsDouble());
            System.out.println("Tasa de cambio ARS: " + conversionRates.get("ARS").getAsDouble());
            System.out.println("Tasa de cambio BRL: " + conversionRates.get("BRL").getAsDouble());
            System.out.println("Tasa de cambio COP: " + conversionRates.get("COP").getAsDouble());

            // Crear el scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);
            int option = 0;

            // Menú de opciones
            while (option != 7) {
                System.out.println("\nBienvenido/a al Conversor de Moneda");
                System.out.println("1) Dólar => Peso Argentino");
                System.out.println("2) Peso Argentino => Dólar");
                System.out.println("3) Dólar => Real Brasileño");
                System.out.println("4) Real Brasileño => Dólar");
                System.out.println("5) Dólar => Peso Colombiano");
                System.out.println("6) Peso Colombiano => Dólar");
                System.out.println("7) Salir");

                System.out.print("Elige una opción válida: ");
                option = scanner.nextInt();

                // Realizar las conversiones dependiendo de la opción seleccionada
                if (option == 1) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount * conversionRates.get("ARS").getAsDouble();
                    System.out.println(amount + " USD son equivalentes a " + convertedAmount + " ARS");
                } else if (option == 2) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount / conversionRates.get("ARS").getAsDouble();
                    System.out.println(amount + " ARS son equivalentes a " + convertedAmount + " USD");
                } else if (option == 3) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount * conversionRates.get("BRL").getAsDouble();
                    System.out.println(amount + " USD son equivalentes a " + convertedAmount + " BRL");
                } else if (option == 4) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount / conversionRates.get("BRL").getAsDouble();
                    System.out.println(amount + " BRL son equivalentes a " + convertedAmount + " USD");
                } else if (option == 5) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount * conversionRates.get("COP").getAsDouble();
                    System.out.println(amount + " USD son equivalentes a " + convertedAmount + " COP");
                } else if (option == 6) {
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = amount / conversionRates.get("COP").getAsDouble();
                    System.out.println(amount + " COP son equivalentes a " + convertedAmount + " USD");
                } else if (option != 7) {
                    System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }

            System.out.println("¡Gracias por usar el Conversor de Moneda!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}