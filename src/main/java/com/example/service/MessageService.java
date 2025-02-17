package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService 
{
    private List<Message> messages= new ArrayList<>();

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository=messageRepository;
    }
    {Message message= new Message(1,"1",1L);}

    public void createMessage(Message message)
    {
    if(!(message.getMessageText().isEmpty()||message.getMessageText().length()>225
    ||!messageRepository.existsById(message.getPostedBy())))
    {
        messageRepository.save(message);
    }

    }

    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }


    
    
}
