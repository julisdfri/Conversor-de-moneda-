import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ConversorMonedas {

    private static final String API_KEY = "449877fecf1e17b530050e50";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monedaBase = "USD";
        boolean repetir = true; // Variable para controlar la repetición

        do {
            double monto;

            System.out.println("Bienvenido al Conversor de Monedas!");

            // Obtener el monto del usuario
            while (true) {
                try {
                    System.out.print("Ingrese el monto a convertir desde " + monedaBase + ": ");
                    monto = Double.parseDouble(scanner.nextLine());
                    if (monto <= 0) {
                        System.out.println("El monto debe ser mayor que cero.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                }
            }

            // Obtener las monedas deseadas del usuario
            String[] monedasDeseadas = obtenerMonedasDeseadas(scanner);

            try {
                // Obtener tasas de cambio
                String jsonRespuesta = obtenerTasasDeCambio(monedaBase);
                Map<String, Double> tasas = parsearJson(jsonRespuesta);
                Map<String, Double> tasasFiltradas = filtrarMonedas(tasas, monedasDeseadas);

                // Mostrar resultados
                mostrarResultados(tasasFiltradas, monto);

            } catch (Exception e) {
                System.err.println("¡Ups! Algo salió mal: " + e.getMessage());
            }

            // Preguntar al usuario si quiere repetir
            System.out.print("¿Desea realizar otra conversión? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();
            if (!respuesta.equals("s")) {
                repetir = false;
            }
        } while (repetir);

        System.out.println("¡Gracias por usar el Conversor de Monedas!");
        scanner.close();
    }

    // Obtener monedas deseadas del usuario
    public static String[] obtenerMonedasDeseadas(Scanner scanner) {
        String[] monedasDeseadas;
        while (true) {
            System.out.print(
                    "Ingrese las Monedas Deseadas (Separadas Por Comas, ej: EUR,GBP,JPY,MXN,CAD,): ");
            String input = scanner.nextLine().toUpperCase();
            monedasDeseadas = input.split(",");
            boolean valid = true;
            for (String moneda : monedasDeseadas) {
                if (moneda.trim().isEmpty()) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                break;
            }
            System.out.println(
                    "Entrada inválida. Por favor, ingrese monedas válidas separadas por comas.");
        }
        return monedasDeseadas;
    }

    // Obtener tasas de cambio de la API
    public static String obtenerTasasDeCambio(String monedaBase) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaBase;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Parsear el JSON con Gson
    public static Map<String, Double> parsearJson(String json) throws Exception {
        Gson gson = new Gson();
        JsonObject root = gson.fromJson(json, JsonObject.class);

        if (!"success".equals(root.get("result").getAsString())) {
            String errorType =
                    root.has("error-type") ? root.get("error-type").getAsString() : "Unknown Error";
            throw new Exception("La API respondió con un error: " + errorType);
        }

        JsonObject rates = root.getAsJsonObject("conversion_rates");
        Map<String, Double> tasasDeCambio = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entries = rates.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            tasasDeCambio.put(entry.getKey(), entry.getValue().getAsDouble());
        }

        return tasasDeCambio;
    }

    // Filtrar las monedas deseadas
    public static Map<String, Double> filtrarMonedas(
            Map<String, Double> tasas, String[] monedasDeseadas) {
        Map<String, Double> tasasFiltradas = new HashMap<>();
        for (String moneda : monedasDeseadas) {
            moneda = moneda.trim(); // Eliminar espacios en blanco
            if (tasas.containsKey(moneda)) {
                tasasFiltradas.put(moneda, tasas.get(moneda));
            } else {
                System.out.println("La moneda " + moneda + " no está disponible.");
            }
        }
        return tasasFiltradas;
    }

    // Mostrar los resultados
    public static void mostrarResultados(Map<String, Double> tasas, double monto) {
        if (tasas.isEmpty()) {
            System.out.println("No se encontraron tasas para las monedas especificadas.");
            return;
        }

        System.out.println("Resultados de la conversión:");
        for (Map.Entry<String, Double> entry : tasas.entrySet()) {
            String moneda = entry.getKey();
            double tasa = entry.getValue();
            double resultado = monto * tasa;
            System.out.printf("%.2f USD = %.2f %s (Tasa: %.4f)\n", monto, resultado, moneda, tasa);
        }
    }
}


