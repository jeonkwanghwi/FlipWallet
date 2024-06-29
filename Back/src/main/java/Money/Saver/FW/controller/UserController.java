package Money.Saver.FW.controller;

import Money.Saver.FW.config.jwt.TokenProvider;
import Money.Saver.FW.domain.User;
import Money.Saver.FW.dto.ReqLoginDto;
import Money.Saver.FW.repository.UserRepository;
import Money.Saver.FW.service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import static Money.Saver.FW.response.ResponseBody.successResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody ReqLoginDto reqLoginDto) {

        User user = userDetailService.loadUserByUsername(reqLoginDto.getEmail());

        /** JWT 생성
         * 20분짜리로 생성했음
         */
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(20));

        /** JWT를 accessToken이라는 이름으로 클라이언트에게 반환 */
        return ResponseEntity.ok(Map.of("accessToken", token));
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return successResponse(HttpStatus.OK);
    }

}
