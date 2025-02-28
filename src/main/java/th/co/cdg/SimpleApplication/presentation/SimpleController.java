package th.co.cdg.SimpleApplication.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.cdg.SimpleApplication.model.User;

import java.util.ArrayList;
import java.util.List;

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

    private List<User> users = new ArrayList<>();

    @GetMapping(value = "hello-with-your-name") // #1 // #2
    public ResponseEntity<String> getHelloWorld(@RequestParam(name = "name") String name) { // #3 //required = false จะส่งก็ได้ไม่ส่งก็ได้ ถ้าเป็น true ถ้าไม่ส่งจะเกิดเคส 400
        return ResponseEntity
                .ok() // #4
                .body("Hello " + name + " !!!"); // #5
    }

    @PostMapping(value = "hello-with-body") //consume = incoming(ขาเข้า), produces = outgoing(ขาออก)
    public ResponseEntity<String> getHelloWorld(@RequestBody User user) {
        return ResponseEntity
                .ok()
                .body("Hello " + user.getName() + " !!!");
    }

    // ---- Service สำหรับ User ทั้งหมด ---- //
    @GetMapping(value = "users")
    public ResponseEntity<List<User>> getAllUserService() {
        return ResponseEntity
                .ok()
                .body(users);
    }

    @PostMapping(value = "user")
    public ResponseEntity<String> addUserController(@RequestBody User user) {
        users.add(user);
        return ResponseEntity
                .ok()
                .body("add user successfully");
    }
}
