package com.platzi.springboot.controller;

import com.platzi.springboot.configuration.GeneralConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class Test {

    @Autowired
    private GeneralConfiguration configuration;

    Log LOOGGER= LogFactory.getLog(Test.class);

    @GetMapping
    public ResponseEntity<String> getName() {
        LOOGGER.info("into the class");
        return new ResponseEntity<>("hola mi nonbre es:" + configuration.getName() + " y mi apellido " + configuration.getLastName(), HttpStatus.OK);
    }


}
