package be.ugent.reactive.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import be.ugent.reactive.model.BlogPost;
import be.ugent.reactive.repository.BlogPostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlogPostDao {
    private final BlogPostRepository repository;

    public BlogPostDao(BlogPostRepository repository) {
        this.repository = repository;
    }

    public Flux<BlogPost> getAll() {
        return this.repository.findAll();
    }

    public Mono<BlogPost> getDoc(ObjectId id) {
        return this.repository.findById(id);
    }

    public Mono<BlogPost> saveDoc(BlogPost blogPost) {
        return this.repository.save(blogPost);
    }

    public Mono<Void> deleteDoc(BlogPost blogPost) {
        return this.repository.delete(blogPost);
    }

    public Mono<Void> deleteDoc(ObjectId id) {
        return this.repository.deleteById(id);
    }

    public Mono<BlogPost> updateDoc(BlogPost blogPost) {
        return this.repository.save(blogPost);
    }
}
