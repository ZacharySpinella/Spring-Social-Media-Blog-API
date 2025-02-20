package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService 
{

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository,AccountRepository accountRepository)
    {
        this.messageRepository=messageRepository;
        this.accountRepository=accountRepository;
    }

    public int createMessage(Message message)
    {
    boolean exists = accountRepository.existsById(message.getPostedBy());
    System.out.println(exists); 
    if(message.getMessageText().isEmpty()||message.getMessageText().length()>255
    ||!accountRepository.existsById(message.getPostedBy()))
    {
        return 0;
    }
    messageRepository.save(message);
    return 1;

    }

    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(int id)
    {
        return messageRepository.findById(id);
    }

    public int deleteMessageById(int messageId)
    {
         return getMessageById(messageId).isPresent()? 1:0;
    }

    public List<Message> getMessageByUser(int postedBy)
    {
        return messageRepository.findByPostedBy(postedBy);
    }

    public int updateMessage(String messageText, int id)
    {
        System.out.println("\n\nUpdating message with id: " + id + " and is messageText empty: " + messageText.isEmpty() + ", Message Text "+messageText+
        ", does message exist "+
         messageRepository.existsById(id)+" message length "+messageText.length());

        if(messageText.isEmpty()||messageText.length()>255||!messageRepository.existsById(id))
        {return 0;}
        return messageRepository.updateMessage(messageText,id);

    }

    public List<Message> getAllByUser(int postedBy)
    {
        return messageRepository.findByPostedBy(postedBy);
    }


    
    
}
