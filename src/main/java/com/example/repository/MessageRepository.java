package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> 
{
    @Modifying
    @Query("INSERT INTO Messages(postedBy,messageText,timePostedEpoch) VALUES(:postedBy,:messageText,:timePostedEpoch)")
    void createMessage(@Param("postedBy") int postedBy,@Param("messageText") String messageText,@Param("timePostedEpoch") long timePostedEpoch);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId=:id")
    int deleteMessage(@Param("id") int id);

    List<Message> findByPostedBy(int postedBy);

    @Modifying
    @Query("UPDATE Message m SET m.messageText=:messageText WHERE m.messageId=:messageId")
    int updateMessage(@Param("messageText") String messageText,@Param("messageId") int messageId);
}
