package be.ugent.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import be.ugent.reactive.model.BlogPost;

@Repository
public interface BlogPostRepository extends ReactiveMongoRepository<BlogPost, String> {
}
