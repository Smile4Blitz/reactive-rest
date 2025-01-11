package com.example.reactiveapi.controller;

import com.example.reactiveapi.service.ReactiveExampleService;
import com.example.reactiveapi.model.ExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/examples")
public class ReactiveExampleController {

    private final ReactiveExampleService reactiveExampleService;

    @Autowired
    public ReactiveExampleController(ReactiveExampleService reactiveExampleService) {
        this.reactiveExampleService = reactiveExampleService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ExampleResponse> getExampleById(@PathVariable String id) {
        return reactiveExampleService.getExampleById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ExampleResponse> getAllExamples() {
        return reactiveExampleService.getAllExamples();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ExampleResponse> createExample(@RequestBody ExampleResponse exampleResponse) {
        return reactiveExampleService.createExample(exampleResponse);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ExampleResponse> updateExample(@PathVariable String id, @RequestBody ExampleResponse exampleResponse) {
        return reactiveExampleService.updateExample(id, exampleResponse);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> deleteExample(@PathVariable String id) {
        return reactiveExampleService.deleteExample(id);
    }
}