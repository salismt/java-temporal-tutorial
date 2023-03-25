package org.example.greeting;

import org.example.common.GenericResponse;
import org.example.greeting.workflow.GreetingWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    GreetingWorkflow greeting;

    public GreetingController(GreetingWorkflow greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public Mono<ResponseEntity<GenericResponse>> getGreeting(@RequestParam("name") String name) {
        String result = greeting.getGreeting(name);
        return Mono.just(ResponseEntity.ok(GenericResponse.createResponse(result)));
    }
}
