package th.co.cdg.SimpleApplication.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import th.co.cdg.SimpleApplication.model.User;
import th.co.cdg.SimpleApplication.repository.UserRepository;

import java.io.IOException;
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
        try {
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
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Cannot add new user cause SQL problem.");
        }
    }

    @PutMapping(value = "update-user")
    public ResponseEntity<String> updateUserByIdController (@RequestBody User user) {
        int result = userRepository.updateUserById(user);

        if (result != 0) {
            return ResponseEntity
                    .ok()
                    .body("Update user successfully");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Cannot update user");
        }
    }

    @DeleteMapping(value = "delete-user/{id}")
    public ResponseEntity<String> deleteUserByIdController (@PathVariable(name = "id") Long id) {
        int result = userRepository.deleteUserById(id);

        if (result != 0) {
            return ResponseEntity
                    .ok()
                    .body("Delete user successfully");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Cannot delete user");
        }
    }

    @PostMapping(value = "add-user-and-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addUserAndImageController (@RequestParam(name = "userData") String userData,
                                                             @RequestParam(name = "image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userData, User.class);
        byte[] imageUser = image.getBytes();
        user.setImage(imageUser);
        try {
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
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Cannot add new user cause SQL problem.");
        }
    }

    @GetMapping(value = "image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageController (@PathVariable(name = "id") Long id) {
        return ResponseEntity
                .ok()
                .body(userRepository.getImageById(id));
    }

}
