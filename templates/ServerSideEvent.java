@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<String> streamEvents() {
    return Flux.interval(Duration.ofSeconds(1))
               .map(seq -> "Event #" + seq);
}
