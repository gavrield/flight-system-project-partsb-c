package com.flightsystem.project.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Aspect
@Component
public class AddAspect {

    @Autowired
    public AddRequestsRepo repo;
    @Autowired
    private SerialNumRepo serialNumRepo;


    @After("execution(* com.flightsystem.project.controllers.*.add*(..))")
    public void addMethodsLog(){
        SerialNumber sn = serialNumRepo.findById("serial-number").orElse(new SerialNumber("serial-number", 0l));
        System.err.println("before add method sn: " + sn.getSn());
        AddRequestDoc doc = new AddRequestDoc();
        doc.setId(sn.getSn());
        sn.setSn(sn.getSn() + 1);
        doc.setDateTime(Date.valueOf(LocalDate.now()));
        repo.save(doc);
        serialNumRepo.save(sn); // updating the database for next app upload
    }


}
