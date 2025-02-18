package com.example.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> 
{


    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId=:id")
    int deleteMessage(@Param("id") int id);

    List<Message> findByPostedBy(int postedBy);

    @Modifying
    @Query("UPDATE Message m SET m.messageText=:messageText WHERE m.messageId=:messageId")
    int updateMessage(@Param("messageText") String messageText,@Param("messageId") int messageId);
}
