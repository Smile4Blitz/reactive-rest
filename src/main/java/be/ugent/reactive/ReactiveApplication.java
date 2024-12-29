package be.ugent.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.reactive.model.BlogPost;
import be.ugent.reactive.repository.BlogPostRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Bean
	CommandLineRunner test(BlogPostRepository repository) {
		return args -> {
			repository.deleteAll()
					.thenMany(
							Flux.just("reactive_spring_boot", "reactive_spring_data", "reactive_mongodb")
									.map(title -> new BlogPost(null, title, "content"))
									.flatMap(repository::save))
					.thenMany(repository.findAll())
					.subscribe(b -> System.out.println(b));
		};
	}
}
