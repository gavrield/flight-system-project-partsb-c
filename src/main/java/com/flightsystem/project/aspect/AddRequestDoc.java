package com.flightsystem.project.aspect;


import org.springframework.data.annotation.Id;
import java.sql.Date;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("add_request")
public class AddRequestDoc {

    @Id
    private Long id;
    private Date dateTime;

    public AddRequestDoc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
