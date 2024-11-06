package org.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import java.util.Vector;

public class VimeoService implements StreamingService {

    public VimeoService(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    @Override
    public void configurar(Collection<String> configParams) {
        for (String param : configParams) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                switch (keyValue[0]) {
                    case "accessToken":
                        this.accessToken = keyValue[1];
                        break;
                    // Agregar mas de una configuracion
                    default:
                        System.out.println("Parámetro de configuración no reconocido: " + keyValue[0]);
                }
            } else {
                System.out.println("Parámetro de configuración inválido: " + param);
            }
        }
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {
        return buscar(query, configParams);
    }

    @Override
    public Collection<SearchResult> buscar(String query, Collection<String> configParams) {
        Collection<SearchResult> resultados = new ArrayList<>();
        try {
            // Codificar la consulta para asegurarse de que la URL sea válida
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String url = "https://api.vimeo.com/videos?query=" + encodedQuery;

            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construir la solicitud con el token de acceso
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + this.accessToken)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            // Enviar la solicitud y recibir la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado y el cuerpo de la respuesta
            System.out.println("Código de estado: " + response.statusCode());//imprimo el codigo de resultado
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
        Collection<SearchResult> results = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convertir la cadena JSON a un nodo
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.path("data"); // Verifica si esta es la ruta correcta

            // Solo procede si hay datos
            if (dataNode.isArray() && dataNode.size() > 0) {
                // Iterar sobre los nodos de datos
                for (JsonNode videoNode : dataNode) {
                    String title = videoNode.path("name").asText(); // Obtener el título
                    String description = videoNode.path("description").asText(); // Obtener la descripción
                    String link = videoNode.path("link").asText(); // Obtener el enlace
                    String platform = "Vimeo"; // Asumimos que todos son de Vimeo

                    // Crear un nuevo SearchResult y añadirlo a la colección
                    SearchResult searchResult = new SearchResult(title, description, link, platform);
                    results.add(searchResult);
                }
            } else {
                System.out.println("No se encontraron resultados en la búsqueda.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
