package com.email.EmailHelper.Controller;


import com.email.EmailHelper.Entity.EmailRequestEntity;
import com.email.EmailHelper.Service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin
public class EmailGeneratorController {

    @Autowired
    private final EmailGeneratorService emailGeneratorService;

//    @Autowired  // ✅ This ensures dependency injection
//    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
//        this.emailGeneratorService = emailGeneratorService;
//    }

    @PostMapping("/generate")
    public ResponseEntity<String> genrateEmail(@RequestBody EmailRequestEntity emailRequestEntity){
        String response = emailGeneratorService.generateEmailReply(emailRequestEntity);
        return ResponseEntity.ok(response);
    }
}

// depency injection , type , why automwired worked , didnt worked ?
// feign , in java =>
// logger vs sout
// all anotations

