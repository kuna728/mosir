package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.UserDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AuthService authService;

    @GetMapping
    public UserDTO getUserDetails() {
        return new UserDTO(authService.getCurrentUser().getUser());
    }
}
