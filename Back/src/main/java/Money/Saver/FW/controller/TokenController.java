package Money.Saver.FW.controller;

import Money.Saver.FW.dto.ReqCreateAccessToken;
import Money.Saver.FW.dto.ResCreateAccessToken;
import Money.Saver.FW.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {
    
    private final TokenService tokenService;

    /** 리프레시 토큰으로 재발급하는 컨트롤러 */
    @PostMapping("/token/reissue")
    public ResponseEntity<ResCreateAccessToken> createNewAccessToken(@RequestBody ReqCreateAccessToken request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResCreateAccessToken(newAccessToken));
    }
}