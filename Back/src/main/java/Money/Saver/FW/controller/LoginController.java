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

import static Money.Saver.FW.response.ResponseBody.successResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    /** 테스트용 */
    @GetMapping("/login")
    public ResponseEntity<Object> loginPage() {
        return ResponseEntity.ok("정상 로그아웃 완료, login 페이지로 Redirect");
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody ReqLoginDto reqLoginDto) {

        User user = userDetailService.loadUserByUsername(reqLoginDto.getEmail());

        /** JWT 생성
         * 10분짜리로 생성했음
         */
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(10));

        /** JWT를 accessToken이라는 이름으로 클라이언트에게 반환 */
        return ResponseEntity.ok(Map.of("accessToken", token));
    }

    /** 로그아웃 후 초기 메인화면(로그인 페이지)으로 Redirect */
    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("정상 로그아웃");
    }

}
