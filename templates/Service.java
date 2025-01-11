package com.example.reactiveapi.service;

import com.example.reactiveapi.model.ExampleResponse;
import com.example.reactiveapi.repository.ReactiveExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveExampleService {

    private final ReactiveExampleRepository reactiveExampleRepository;

    @Autowired
    public ReactiveExampleService(ReactiveExampleRepository reactiveExampleRepository) {
        this.reactiveExampleRepository = reactiveExampleRepository;
    }

    public Mono<ExampleResponse> getExampleById(String id) {
        return reactiveExampleRepository.findById(id);
    }

    public Flux<ExampleResponse> getAllExamples() {
        return reactiveExampleRepository.findAll();
    }

    public Mono<ExampleResponse> createExample(ExampleResponse exampleResponse) {
        return reactiveExampleRepository.save(exampleResponse);
    }

    public Mono<ExampleResponse> updateExample(String id, ExampleResponse updatedExample) {
        return reactiveExampleRepository.findById(id)
                .flatMap(existingExample -> {
                    existingExample.setName(updatedExample.getName());
                    existingExample.setDescription(updatedExample.getDescription());
                    return reactiveExampleRepository.save(existingExample);
                });
    }

    public Mono<Void> deleteExample(String id) {
        return reactiveExampleRepository.deleteById(id);
    }
}