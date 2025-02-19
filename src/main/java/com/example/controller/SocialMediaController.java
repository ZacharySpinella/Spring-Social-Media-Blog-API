package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account;
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

    @PostMapping("register")
    public @ResponseBody ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        if(accountService.createAccount(account)==1)
        {
            Account acc= accountService.getAccountByUsername(account.getUsername());
            return ResponseEntity.status(200).body(acc);
        }
        return(accountService.createAccount(account)==0) ? ResponseEntity.status(400).body(null) : ResponseEntity.status(409).body(null);
    }

    @PostMapping("login")
    public @ResponseBody ResponseEntity<Account> login(@RequestBody Account account)
    {
        return (accountService.accountLogin(account)==1) ? 
        ResponseEntity.status(200).body(accountService.getAccountByUsername(account.getUsername())) 
        : ResponseEntity.status(401).body(null);
    }

    @PostMapping("messages")
    public @ResponseBody ResponseEntity<Message> createMessage(@RequestBody Message message)
    {
        if(messageService.createMessage(message)==1){
           List<Message> meg= messageService.getMessageByUser(message.getPostedBy());
            Message msg=meg.get(0);

        return ResponseEntity.status(200).body(msg);
        }
        return ResponseEntity.status(400).body(null);
    }

}
