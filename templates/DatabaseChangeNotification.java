@Bean
public ApplicationRunner databaseChangeListener(ReactiveMongoTemplate template) {
    return args -> {
        template.changeStream("collectionName", Document.class)
                .doOnNext(change -> System.out.println("Change detected: " + change))
                .subscribe();
    };
}
