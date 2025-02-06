package com.example.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {

    @Value("${app.version}")
    private String version;

    @GetMapping
    @RequestMapping("/")
    public Map getVersion() {
        Map<String, String> versions = new HashMap<>();
        versions.put("app-version", version);
        return versions;
    }

}
