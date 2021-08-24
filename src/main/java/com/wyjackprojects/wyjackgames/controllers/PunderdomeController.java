package com.wyjackprojects.wyjackgames.controllers;

import com.wyjackprojects.wyjackgames.Punderdome;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/punderdome")
public class PunderdomeController {
  	private static final Logger log = LoggerFactory.getLogger(PunderdomeController.class);


  @RequestMapping("")
  public ResponseEntity<Punderdome> getPunderdome() {
    Punderdome punderdome = new Punderdome("Wyeth");
    return ResponseEntity.ok().body(punderdome);
  }
}
