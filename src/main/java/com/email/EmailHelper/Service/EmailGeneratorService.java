package com.email.EmailHelper.Service;


import com.email.EmailHelper.Entity.EmailRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailGeneratorService {


    @Value("${gemini.api.url")
    private String GeminiAPiUrl;
    @Value("${gemini.api.key")
    private String GeminiApiKey;

    // here what we are doing is , we are using gemini model to querry

    public String generateEmailReply(EmailRequestEntity emailRequest){
        // build the promt
        String promt = buildPromtFunction(emailRequest);
        // craft a request in specific manner which is needed for the aPi
        // Request should be in a format[ we will be using map , as we have stucture something like key value pair
        Map<String,Object> requestBody = Map.of(
                "content", new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text", promt)
                        })
                }
        );


        // Do request and get a response [ you will neeed the Api key ans url ]



        // return that response

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
