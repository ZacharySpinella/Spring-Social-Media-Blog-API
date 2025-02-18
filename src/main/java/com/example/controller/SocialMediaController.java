package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {
    
    private MessageService messageService;
    private AccountService accountService;

    public SocialMediaController(MessageService messageService, AccountService accountService)
    {
        this.messageService=messageService;
        this.accountService=accountService;
    }

    @PostMapping("messages")
    public @ResponseBody ResponseEntity<List<Message>> createMessage(Message message)
    {
        if(messageService.createMessage(message)==1){
        return ResponseEntity.status(200).body(messageService.getMessageByUser(message.getPostedBy()));
        }
        return ResponseEntity.status(400).body(null);
    }

}
