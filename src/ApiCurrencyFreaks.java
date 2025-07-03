import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ApiCurrencyFreaks {
    private final String apiKey;
    private final HttpClient client;
    private final Gson gson;

    public ApiCurrencyFreaks(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public class ApiResponse {
        public String result;
        public Map<String, Double> conversion_rates;
    }


    public double obterTaxaCambio(String from, String to) throws Exception {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, from);

        int maxTentativas = 3;
        int tentativa = 0;
        Exception ultimaExcecao = null;

        while (tentativa < maxTentativas) {
            tentativa++;
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .header("Accept", "application/json")
                        .header("User-Agent", "JavaHttpClient")
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Log de resposta
                StringBuilder log = new StringBuilder();
                log.append("=== Requisição ").append(tentativa).append(" ===\n");
                log.append("Data/Hora: ").append(LocalDateTime.now()).append("\n");
                log.append("URL: ").append(url).append("\n");
                log.append("Status HTTP: ").append(response.statusCode()).append("\n");
                response.headers().map().forEach((k, v) -> log.append(k).append(": ").append(String.join(", ", v)).append("\n"));
                log.append("Corpo:\n").append(response.body()).append("\n\n");
                Files.writeString(Paths.get("log.txt"), log.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                // Verifica status HTTP
                if (response.statusCode() != 200) {
                    throw new Exception("Código HTTP inválido: " + response.statusCode());
                }

                // ✅ Mapeamento JSON direto com Gson
                ApiResponse data = gson.fromJson(response.body(), ApiResponse.class);

                if (!"success".equals(data.result)) {
                    throw new Exception("Erro da API: " + data.result);
                }

                if (!data.conversion_rates.containsKey(to)) {
                    throw new Exception("Moeda de destino não encontrada: " + to);
                }

                return data.conversion_rates.get(to);

            } catch (Exception e) {
                ultimaExcecao = e;
                System.out.println("Tentativa " + tentativa + " falhou: " + e.getMessage());
                if (tentativa < maxTentativas) {
                    Thread.sleep(1000); // espera 1 segundo antes de tentar de novo
                }
            }
        }

        throw new Exception("Todas as tentativas falharam.", ultimaExcecao);
    }
}
