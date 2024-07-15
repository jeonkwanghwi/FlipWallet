package Money.Saver.FW.controller;

import Money.Saver.FW.domain.User;
import Money.Saver.FW.dto.ReqSignupDto;
import Money.Saver.FW.repository.UserRepository;
import Money.Saver.FW.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static Money.Saver.FW.response.ResponseBody.successResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody ReqSignupDto reqSignupDto) throws IOException {
        User user = userService.signUp(reqSignupDto);
        return successResponse(reqSignupDto);
    }

    @GetMapping("/isEmailDupl/{email}")
    public ResponseEntity<Integer> isEmailDupl(@PathVariable String email) {
        return ResponseEntity.ok(userRepository.existsByEmail(email) ? 1 : 0);
    }

}
