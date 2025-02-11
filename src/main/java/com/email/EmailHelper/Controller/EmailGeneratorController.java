package com.email.EmailHelper.Controller;


import com.email.EmailHelper.Entity.EmailRequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {

    public ResponseEntity<String> genrateEmail(@RequestBody EmailRequestEntity emailRequestEntity){
        return ResponseEntity.ok("");
    }
}
