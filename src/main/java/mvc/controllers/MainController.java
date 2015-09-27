package mvc.controllers;

import java.util.concurrent.atomic.AtomicLong;

import mvc.model.ResponseDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parse.util.Parse;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/index")
    public void indexing() {
        Parse.learn("models/messages.htm", "Natasha Golovchenko");
    }

    @RequestMapping("/response")
    public ResponseDAO greeting(@RequestParam(value="text") String name) {
        return new ResponseDAO(name);
    }
}
