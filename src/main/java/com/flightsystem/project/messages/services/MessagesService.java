package com.flightsystem.project.messages.services;

import com.flightsystem.project.messages.repositories.MessagesRepository;
import com.flightsystem.project.messages.entities.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }


    public MessageEntity findById(long id) {
        return messagesRepository.findById(id).get();
    }

    public List<MessageEntity> findAll() {
        List<MessageEntity> list = new ArrayList<>();
        messagesRepository.findAll().forEach(list::add);
        return list;
    }

    public MessageEntity update(MessageEntity message, long id) {
        message.setMessageId(id);
        return messagesRepository.save(message);
    }

    public MessageEntity add(MessageEntity message) {
        message.setMessageId(null);
        return messagesRepository.save(message);
    }

    public void remove(Long id) {
        messagesRepository.deleteById(id);
    }




}

