package th.co.cdg.SimpleApplication.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping(value = "hello-world")
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity
                .ok()
                .body("Hello World!");
    }
}
