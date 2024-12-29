package be.ugent.reactive.controller;

import java.time.Duration;

import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.reactive.model.BlogPost;
import be.ugent.reactive.service.BlogPostDao;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stream")
public class BlogPostsStreamController {
    final BlogPostDao blogPostDao;

    final ReactiveMongoTemplate reactiveMongoTemplate;

    public BlogPostsStreamController(BlogPostDao blogPostDao, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.blogPostDao = blogPostDao;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @GetMapping(value = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<BlogPost> streamBlogPosts() {
        return this.blogPostDao
                .getAll()
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/changes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<ChangeStreamEvent<BlogPost>> changes() {
        return reactiveMongoTemplate.changeStream(BlogPost.class)
                .watchCollection(BlogPost.class)
                .listen();
    }
}
