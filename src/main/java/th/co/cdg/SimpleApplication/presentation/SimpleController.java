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
    public ResponseEntity<List<User>> getAllUserController() {
        if (!users.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(users);
        } else {
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @PostMapping(value = "user")
    public ResponseEntity<String> addUserController(@RequestBody User user) {
        if (areUserExist(user.getId())) {
            return ResponseEntity
                    .badRequest()
                    .body("user already exist");
        }else{
            users.add(user);
            return ResponseEntity
                    .ok()
                    .body("add user successfully");
        }
    }

    @PutMapping(value = "user")
    public ResponseEntity<String> updateUserController(@RequestBody User user) {

        if (null == user.getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Cannot update user: field 'id' is missing.");
        }

        User queryUser = users
                .stream()
                .filter(existedUser -> user.getId().equals(existedUser.getId()))
                .findAny()
                .orElse(null);
        if (null == queryUser) {
            return ResponseEntity
                    .badRequest()
                    .body("Cannot update user: User does not exist.");
        }

        if (null != user.getName()) queryUser.setName(user.getName()); //short if

        if (null != user.getTel()) {
            queryUser.setTel(user.getTel());
        }

        deleteUser(user);
        addUser(queryUser);

        return ResponseEntity
                .ok()
                .body("Update user successfully.");
    }

    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<String> deleteUserController(@PathVariable(name = "id") long id) {
        if (areUserExist(id)) {
            users.removeIf(user -> user.getId().equals(id));
            return ResponseEntity
                    .ok()
                    .body("delete user successfully");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("user does not exist");
        }
    }

    private boolean areUserExist(Long id) {
        return users
                .stream()
                .anyMatch(existedUser -> id.equals(existedUser.getId()));
    }

    private void deleteUser(User user) {
        users.removeIf(existedUser -> user.getId().equals(existedUser.getId()));
    }

    private void addUser(User user) {
        users.add(user);
    }

}
