package com.platzi.play;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final PlatziPlayAIService aiService;

    public HelloController(PlatziPlayAIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("")
    public String helloWord() {
        return this.aiService.generateGreeting();
    }

}
