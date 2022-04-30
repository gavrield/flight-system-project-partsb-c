package com.flightsystem.project.messages.controllers;


import com.flightsystem.project.messages.entities.MessageEntity;
import com.flightsystem.project.messages.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-us")
public class MessageController {

    @Autowired
    private MessagesService messagesService;

    public MessageController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }


    @GetMapping("/{id}")
    public MessageEntity getMessageById(@PathVariable long id){
        return messagesService.findById(id);
    }

    @GetMapping("/")
    public List<MessageEntity> getAllMessages(){
       return messagesService.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageEntity addMessage(@RequestBody MessageEntity message){
        return messagesService.add(message);
    }

    @PutMapping("/{id}")
    public MessageEntity updateMessage(@RequestBody MessageEntity message, @PathVariable long id){
        return messagesService.update(message, id);
    }

    @DeleteMapping("/{id}")
    public void removeMessage(@PathVariable Long id){
        messagesService.remove(id);
    }
}
