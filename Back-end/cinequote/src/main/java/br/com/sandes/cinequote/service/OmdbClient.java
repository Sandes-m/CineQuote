package br.com.sandes.cinequote.service;

import br.com.sandes.cinequote.exception.EnvVarNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class OmdbClient {

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String OMDB_API_KEY = System.getenv("OMDB_API_KEY");

    public String omdbRequest(String obra) {

        if (OMDB_API_KEY == null || OMDB_API_KEY.isEmpty()) {
            throw new EnvVarNotFoundException("Váriavel de ambiente (OMDB_API_KEY) não configurada nesse dispositivo");
        }

        String pathObra = URLEncoder.encode(obra, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + pathObra + OMDB_API_KEY))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }

}
