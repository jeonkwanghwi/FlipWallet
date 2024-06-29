package Money.Saver.FW.controller;

import Money.Saver.FW.domain.User;
import Money.Saver.FW.dto.ReqSignupDto;
import Money.Saver.FW.repository.UserRepository;
import Money.Saver.FW.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

        // 이메일 중복 체크
        if (userRepository.existsByEmail(reqSignupDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        return successResponse(reqSignupDto);
    }

}
