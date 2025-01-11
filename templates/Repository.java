package com.example.reactiveapi.repository;

import com.example.reactiveapi.model.ExampleResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveExampleRepository extends ReactiveMongoRepository<ExampleResponse, String> {
}