import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {

    // A URL base da API com a chave
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c246d277b0ee9de7e8f5c549/latest/";

    // Método para obter a taxa de câmbio
    public static double getExchangeRate(String from, String to) throws Exception {
        // A URL final, incluindo a moeda base
        String url = API_URL + from;

        // Criação do cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Enviar a requisição
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar se o status da resposta é 200 OK
        if (response.statusCode() != 200) {
            throw new Exception("Erro ao conectar-se à API: Código de status " + response.statusCode());
        }

        // Verificar se o tipo de conteúdo é JSON
        String contentType = response.headers().firstValue("Content-Type").orElse("");
        if (!contentType.contains("application/json")) {
            throw new Exception("Resposta da API não é um JSON válido.");
        }

        // Depuração: Imprimir a resposta da API
        //System.out.println("Resposta da API: " + response.body());

        // Parse da resposta JSON usando Gson
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

        // Verificar se a chave 'conversion_rates' existe
        if (!jsonResponse.has("conversion_rates")) {
            throw new Exception("A chave 'conversion_rates' não foi encontrada na resposta da API.");
        }

        // Obter o objeto 'conversion_rates'
        JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

        // Verificar se a chave da moeda destino existe
        if (!rates.has(to)) {
            throw new Exception("Moeda " + to + " não encontrada na resposta da API.");
        }

        // Obter a taxa de conversão
        double rate = rates.get(to).getAsDouble();

        // Retornar a taxa de conversão
        return rate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu de opções de conversão
        while (true) {
            System.out.println("=== Conversor de Moedas ===");
            System.out.println("1. USD para BRL");
            System.out.println("2. BRL para USD");
            System.out.println("3. USD para ARS");
            System.out.println("4. ARS para USD");
            System.out.println("5. BOB para BRL");
            System.out.println("6. BRL para BOB");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            if (option == 7) {
                System.out.println("Saindo do programa...");
                break;
            }

            String fromCurrency = "";
            String toCurrency = "";

            switch (option) {
                case 1:
                    fromCurrency = "USD";
                    toCurrency = "BRL";
                    break;
                case 2:
                    fromCurrency = "BRL";
                    toCurrency = "USD";
                    break;
                case 3:
                    fromCurrency = "USD";
                    toCurrency = "ARS";
                    break;
                case 4:
                    fromCurrency = "ARS";
                    toCurrency = "USD";
                    break;
                case 5:
                    fromCurrency = "BOB";
                    toCurrency = "BRL";
                    break;
                case 6:
                    fromCurrency = "BRL";
                    toCurrency = "BOB";
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double amount = scanner.nextDouble();

            try {
                // Obter a taxa de câmbio
                double rate = getExchangeRate(fromCurrency, toCurrency);
                // Calcular o valor convertido
                double convertedAmount = rate * amount;

                // Exibir a taxa de conversão e o valor convertido
                System.out.printf("Taxa de conversão: 1 %s = %.2f %s\n", fromCurrency, rate, toCurrency);
                System.out.printf("%.2f %s é equivalente a %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}

