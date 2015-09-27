package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parse.Conversation;
import parse.Parse;
import hello.Response;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/index")
    public void indexing() {
        Parse.learn("models/messages.htm", "Natasha Golovchenko");
    }

    @RequestMapping("/response")
    public Response greeting(@RequestParam(value="text") String name) {
        return new Response(name);
    }
}
