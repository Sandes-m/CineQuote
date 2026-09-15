package br.com.sandes.cinequote.service;

import br.com.sandes.cinequote.exception.EnvVarNotFoundException;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.util.List;

public class AiClient {

    private static final String MODELO_AI = "gemini-2.5-flash";
    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");

    public static String buscarInfoObra(String obra) {
        return buscarInfoObra(obra, List.of());
    }

    public static String buscarInfoObra(String obra, List<String> frasesExistentes) {

        if (GEMINI_API_KEY == null || GEMINI_API_KEY.isEmpty()) {
            throw new EnvVarNotFoundException("Váriavel de ambiente (GEMINI_API_KEY) não configurada nesse dispositivo");
        }

        Client client = Client.builder()
                .apiKey(GEMINI_API_KEY)
                .build();

        String instrucaoEvitar = "";
        if (frasesExistentes != null && !frasesExistentes.isEmpty()) {
            instrucaoEvitar = " IMPORTANTE: Evite OBRIGATORIAMENTE as seguintes frases já cadastradas: "
                    + frasesExistentes + ". Encontre uma frase DIFERENTE e inédita dita na produção.";
        }

        GenerateContentResponse response =
                client.models.generateContent(
                        MODELO_AI,
                        "Para a seguinte obra (filme ou série): " + obra + ", encontre uma frase clássica e icônica dita na produção." +
                                instrucaoEvitar +
                                " Retorne OBRIGATORIAMENTE apenas um JSON puro, sem marcações de código Markdown (sem ``` ou ```json), em português PT-BR, sem introduções e sem explicações, no seguinte formato exato: " +
                                "{" +
                                "\"frase\": \"texto da frase icônica\", " +
                                "\"personagem\": \"nome do personagem\", " +
                                "\"contexto\": \"contexto curto da cena em no máximo 250 caracteres\"" +
                                "}",
                        null
                );

        return response.text();
    }

}
