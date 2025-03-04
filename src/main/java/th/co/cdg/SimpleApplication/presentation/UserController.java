package th.co.cdg.SimpleApplication.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.cdg.SimpleApplication.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired // ทำ constructor ของ UserRepository
    UserRepository userRepository;
}
