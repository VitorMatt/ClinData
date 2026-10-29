package main.java.com.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cidade {

    public static List<String> listarCidades(Estado estado) throws Exception {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"
                + estado.name() + "/municipios?orderBy=nome";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao consultar municípios.");
        }

        List<String> cidades = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "\\{\"id\":\\d+,\"nome\":\"((?:\\\\.|[^\"\\\\])*)\""
        );

        Matcher matcher = pattern.matcher(response.body());

        while (matcher.find()) {
            cidades.add(matcher.group(1));
        }

        List<String> cidadesValidation = new ArrayList<>();

        for (int i=0; i<cidades.size(); i++) {

            if (!cidadesValidation.contains(cidades.get(i))){

                cidadesValidation.add(cidades.get(i));
            }
        }

        cidades = cidadesValidation;

        return cidades;
    }
}