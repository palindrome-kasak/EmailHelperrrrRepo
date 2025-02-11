package com.email.EmailHelper.Service;


import com.email.EmailHelper.Entity.EmailRequestEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {
    // here what we are doing is , we are using gemini model to querry


    // values for gemini api and key [ not used hard coded , using it as passing env variable ]
    @Value("${gemini.api.url}")
    private String GeminiAPiUrl;
    @Value("${gemini.api.key}")
    private String GeminiApiKey;



    private final WebClient webClient;


    // neeeds to check this , understand this use of this why?
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    public String generateEmailReply(EmailRequestEntity emailRequest){
        // build the promt
        System.out.println("here are keys checks!!!");
        System.out.println(GeminiAPiUrl);
        System.out.println(GeminiApiKey);
        String promt = buildPromtFunction(emailRequest);
        // craft a request in specific manner which is needed for the aPi
        // Request should be in a format[ we will be using map , as we have stucture something like key value pair
        System.out.println("here is our promt");
        System.out.println(promt);
        Map<String,Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text", promt)
                        })
                }
        );


        // Do request and get a response [ you will neeed the Api key ans url ]

        System.out.println(GeminiAPiUrl + GeminiApiKey);
        System.out.println("here is out resq body");
        System.out.println(requestBody);

        String response = webClient.post().uri(GeminiAPiUrl + GeminiApiKey).header("Content-Type","application/json").bodyValue(requestBody).retrieve().bodyToMono(String.class).block();





        // extractResponseContent return that response

        return extractResponseContent(response);

    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper Mapper = new ObjectMapper();
            JsonNode rootNode = Mapper.readTree(response);
            return rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

        } catch (Exception e) {
            return "Error processing request : " + e.getMessage();
        }
    }

    private String buildPromtFunction(EmailRequestEntity emailRequest) {
        StringBuilder promt = new StringBuilder();
        promt.append("Generate a professional and concise email reply based on the message below. Maintain a polite tone, address any questions, and include necessary actions or acknowledgments. and dont use subject Line");
        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            promt.append(" usee this ").append(emailRequest.getTone()).append("Tone ." );
        }
        promt.append("\n here is original mail: \n").append(emailRequest.getEmailContent());

        return promt.toString();
    }

}
