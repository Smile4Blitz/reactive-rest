@Controller
public class ReactiveWebSocketHandler {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Flux<String> handleChat(Flux<String> messageStream) {
        return messageStream.map(msg -> "Received: " + msg);
    }
}
