package org.search;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class WatchModeService implements StreamingService {
    private static final String API_KEY = "wFy1JPJzgWihQNHu1ACzjxqNC1kHRLblv6ETAa6W";
    private static final String BASE_URL = "https://api.watchmode.com/v1/";

    @Override
    public void configurar(Collection<String> configParams) {
        System.out.println("Configurando WatchMode con los siguientes parámetros:");
        for (String param : configParams) {
            System.out.println("- " + param);
        }
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {
        System.out.println("Consultando película '" + query + "' en WatchMode con los parámetros: " + configParams);
        Collection<SearchResult> consultados = new ArrayList<>();

        try {
            String endpoint = BASE_URL + "search/?apiKey=" + API_KEY + "&search_field=name&search_value=" + query;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray titles = jsonResponse.optJSONArray("title_results");

                if (titles != null && titles.length() > 0) {
                    JSONObject title = titles.getJSONObject(0);
                    Integer id = title.getInt("id");

                    String detailsUrl = BASE_URL + "title/" + id + "/details/?apiKey=" + API_KEY + "&append_to_response=sources";
                    URL detailurl = new URL(detailsUrl);
                    HttpURLConnection connDetail = (HttpURLConnection) detailurl.openConnection();
                    connDetail.setRequestMethod("GET");

                    int detailResponseCode = connDetail.getResponseCode();
                    if (detailResponseCode == 200) {
                        BufferedReader detailsIn = new BufferedReader(new InputStreamReader(connDetail.getInputStream()));
                        StringBuilder detailResponse = new StringBuilder();
                        String detailsInputLine;

                        while ((detailsInputLine = detailsIn.readLine()) != null) {
                            detailResponse.append(detailsInputLine);
                        }
                        detailsIn.close();

                        JSONObject detailsJsonResponse = new JSONObject(detailResponse.toString());
                        String name = detailsJsonResponse.getString("title");
                        String description = detailsJsonResponse.optString("plot_overview", "Descripción no disponible");

                        JSONArray sources = detailsJsonResponse.optJSONArray("sources");
                        String urlLink = "No disponible";
                        String platform = "Desconocida";

                        if (sources != null && sources.length() > 0) {
                            JSONObject source = sources.getJSONObject(0);
                            urlLink = source.optString("web_url", "No disponible");
                            platform = source.optString("name", "No disponible");
                        }

                        SearchResult searchResult = new SearchResult(name, description, urlLink, platform);
                        consultados.add(searchResult);
                    } else {
                        System.out.println("Error en la respuesta de detalles: " + detailResponseCode);
                    }
                } else {
                    System.out.println("No se encontraron títulos para la búsqueda: " + query);
                }

            } else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();
                System.out.println("Error: " + errorResponse.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return consultados;
    }

    @Override
    public Collection<SearchResult> buscar(String query, Collection<String> configParams) {
        Collection<SearchResult> resultados = new ArrayList<>();

        try {
            String endpoint = BASE_URL + "search/?apiKey=" + API_KEY + "&search_field=name&search_value=" + query;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray titles = jsonResponse.optJSONArray("title_results");

                if (titles != null) {
                    for (int i = 0; i < titles.length(); i++) {
                        JSONObject title = titles.getJSONObject(i);
                        Integer id = title.getInt("id");

                        String detailsUrl = BASE_URL + "title/" + id + "/details/?apiKey=" + API_KEY + "&append_to_response=sources";
                        URL detailurl = new URL(detailsUrl);
                        HttpURLConnection connDetail = (HttpURLConnection) detailurl.openConnection();
                        connDetail.setRequestMethod("GET");

                        int detailResponseCode = connDetail.getResponseCode();
                        if (detailResponseCode == 200) {
                            BufferedReader detailsIn = new BufferedReader(new InputStreamReader(connDetail.getInputStream()));
                            StringBuilder detailResponse = new StringBuilder();
                            String detailsInputLine;

                            while ((detailsInputLine = detailsIn.readLine()) != null) {
                                detailResponse.append(detailsInputLine);
                            }
                            detailsIn.close();

                            JSONObject detailsJsonResponse = new JSONObject(detailResponse.toString());
                            String name = detailsJsonResponse.getString("title");
                            String description = detailsJsonResponse.optString("plot_overview", "Descripción no disponible");

                            JSONArray sources = detailsJsonResponse.optJSONArray("sources");
                            String urlLink = "No disponible";
                            String platform = "Desconocida";

                            if (sources != null && sources.length() > 0) {
                                JSONObject source = sources.getJSONObject(0);
                                urlLink = source.optString("web_url", "No disponible");
                                platform = source.optString("name", "No disponible");
                            }

                            SearchResult searchResult = new SearchResult(name, description, urlLink, platform);
                            resultados.add(searchResult);
                        }
                    }
                }

            } else {
                System.out.println("Error en la respuesta de la API: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

}