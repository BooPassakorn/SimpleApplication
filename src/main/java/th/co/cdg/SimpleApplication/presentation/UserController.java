package th.co.cdg.SimpleApplication.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.cdg.SimpleApplication.model.User;
import th.co.cdg.SimpleApplication.repository.UserRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired // ทำ constructor ของ UserRepository
    UserRepository userRepository;

    @GetMapping(value = "get-all-users")
    public ResponseEntity<ArrayList<User>> getAllUserController () {
        return ResponseEntity
                .ok()
                .body(userRepository.queryAllUser());
    }

    @PostMapping(value = "add-user")
    public ResponseEntity<String> addUserController (@RequestBody User user) {
        int result = userRepository.insertNewUser(user);

        if (result != 0) {
            return ResponseEntity
                    .ok()
                    .body("Add new user successfully");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Cannot add new user");
        }
    }
}
