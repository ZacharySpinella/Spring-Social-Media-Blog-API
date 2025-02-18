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
    System.out.println("\n\n"+message.toString()+"\n\tMessageService"+"\n\n");
    boolean exists = accountRepository.existsById(message.getPostedBy());
    System.out.println(exists); 
    if(message.getMessageText().isEmpty()||message.getMessageText().length()>255
    ||!accountRepository.existsById(message.getPostedBy()))
    {
        System.out.println("\n\nNO MESSAGE CREATED\n\tMessageService\n\n");
        return 0;
    }
    System.out.println("\n\n"+message.toString()+"\n\tMessageService"+"\n\n");
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

    public Optional<Integer> deleteMessageById(int messageId)
    {
         if(messageRepository.findById(messageId).isPresent())
         {
            return Optional.empty();
         }
         return Optional.of(messageRepository.deleteMessage(messageId));
    }

    public List<Message> getMessageByUser(int postedBy)
    {
        return messageRepository.findByPostedBy(postedBy);
    }

    public int updateMessage(String messageText, int id)
    {
        if(messageText.isEmpty()||messageText.length()>225||!messageRepository.existsById(id))
        {return 0;}
        return messageRepository.updateMessage(messageText,id);

    }

    public List<Message> getAllByUser(int postedBy)
    {
        return messageRepository.findByPostedBy(postedBy);
    }


    
    
}
