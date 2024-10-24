package org.search;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Vector;

public class VimeoService implements StreamingService {

    public VimeoService(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    @Override
    public void configurar(Collection<String> configParams) {

        this.accessToken = configParams.iterator().next();
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {

        return buscar(query, configParams);
    }

    @Override
    public Collection<SearchResult> buscar(String query, Collection<String> configParams) {
        Collection<SearchResult> resultados = new ArrayList<>();
        String url = "https://api.vimeo.com/videos?query=" + query;

        // Crear el cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud con el token de acceso
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + this.accessToken)
                .header("Content-Type", "application/json") // Agregar tipo de contenido
                .GET()
                .build();

        try {
            // Enviar la solicitud y recibir la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado y la respuesta para depuración
            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Respuesta de la API: " + response.body());

            // Verificar el código de estado
            if (response.statusCode() == 200) {
                resultados = parseSearchResults(response.body());
            } else {
                System.out.println("Error en la solicitud a la API: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }
    //Parsear los resultados
    private Collection<SearchResult> parseSearchResults(String responseBody) {
        Collection<SearchResult> results = new Vector<>();
        return results;
    }
}
