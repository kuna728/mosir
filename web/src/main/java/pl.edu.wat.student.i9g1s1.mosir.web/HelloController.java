package pl.edu.wat.student.i9g1s1.mosir.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping
    public String sayHi() {
        return "Hi! Server time: " + LocalDateTime.now();
    }
}