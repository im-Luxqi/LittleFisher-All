package com.littlefisher.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class Test002Controller {

    @Value("${test.name}")
    private String name;


    @GetMapping("/test")
    public String test() {
        return name;
    }
}
