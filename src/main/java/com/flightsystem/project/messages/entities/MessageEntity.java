package com.flightsystem.project.messages.entities;


import com.flightsystem.project.models.POCO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageEntity implements POCO {


    @Id() @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    private String senderName;
    private String content;


    public long getMessageId(Long id) {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
