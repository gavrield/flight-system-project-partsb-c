package com.flightsystem.project.messages.repositories;
import com.flightsystem.project.messages.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends CrudRepository<MessageEntity, Long>{
}
