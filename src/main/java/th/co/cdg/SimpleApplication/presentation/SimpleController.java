package th.co.cdg.SimpleApplication.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {

    // ---- #1 HTTP Methods ---- //
    // 1. @GetMapping
    // 2. @PostMapping
    // 3. @PutMapping
    // 4. @DeleteMapping

    // ---- #2 Base API Name ---- //
    // Ex. /hello-world

    // ---- #3 Request Param ---- //
    // Ex. ?name=angel

    // ---- #4 Response HTTP Status ---- //
    // 200 OK

    // ---- #5 Response Body ---- //
    // Hello (name) !!!.

    @GetMapping(value = "hello-with-your-name")
    public ResponseEntity<String> getHelloWorld(@RequestParam(name = "name", defaultValue = "world") String name) { //required = false จะส่งก็ได้ไม่ส่งก็ได้ ถ้าเป็น true ถ้าไม่ส่งจะเกิดเคส 400
        return ResponseEntity
                .ok()
                .body("Hello " + name + " !!!");
    }

}
