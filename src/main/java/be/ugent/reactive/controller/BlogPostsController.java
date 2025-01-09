package be.ugent.reactive.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.reactive.exception.BlogPostConflictException;
import be.ugent.reactive.exception.BlogPostNotFoundException;
import be.ugent.reactive.model.BlogPost;
import be.ugent.reactive.service.BlogPostDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
    final BlogPostDao blogPostDao;

    public BlogPostsController(BlogPostDao blogPostDao) {
        this.blogPostDao = blogPostDao;
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<BlogPost> getAll(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "content", required = false) String content) {
        return this.blogPostDao
                .getAll()
                .filter(t -> (title == null
                        || t.getTitle().contains(title) || title.contains(t.getTitle())))
                .filter(t -> (content == null
                        || (t.getContent().contains(content) || content.contains(t.getContent()))));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<BlogPost> getById(@PathVariable(name = "id", required = true) String id) {
        return this.blogPostDao.getDoc(id).switchIfEmpty(Mono.error(new BlogPostNotFoundException()));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<ResponseEntity<BlogPost>> addPost(@RequestBody(required = true) BlogPost blogPost) {
        return this.blogPostDao.saveDoc(blogPost)
                .map(r -> ResponseEntity
                        .created(URI.create("/blogposts/" + r.getId()))
                        .body(r));
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<BlogPost> addPost(
            @PathVariable(name = "id") String id,
            @RequestBody(required = true) BlogPost blogPost)
            throws BlogPostConflictException {
        if (!id.equals(blogPost.getId()))
            throw new BlogPostConflictException();
        return this.blogPostDao.updateDoc(blogPost);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> addPost(@PathVariable(name = "id", required = true) String id) {
        return this.blogPostDao.deleteDoc(id);
    }
}
