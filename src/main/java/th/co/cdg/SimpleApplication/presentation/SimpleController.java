package th.co.cdg.SimpleApplication.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {

    // ---- #1 HTTP Methods ---- //
    // 1. @GetMapping

    // ---- #2 Base API Name ---- //
    // /hello-with-your-name

    // ---- #3 Request Param ---- //
    // ?name=boo

    // ---- #4 Response HTTP Status ---- //
    // 200 OK

    // ---- #5 Response Body ---- //
    // Hello boo !!!

    @GetMapping(value = "hello-with-your-name") // #1 // #2
    public ResponseEntity<String> getHelloWorld(@RequestParam(name = "name") String name) { // #3 //required = false จะส่งก็ได้ไม่ส่งก็ได้ ถ้าเป็น true ถ้าไม่ส่งจะเกิดเคส 400
        return ResponseEntity
                .ok() // #4
                .body("Hello " + name + " !!!"); // #5
    }

}
