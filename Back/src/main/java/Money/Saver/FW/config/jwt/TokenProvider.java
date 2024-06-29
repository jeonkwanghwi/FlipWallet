package Money.Saver.FW.config.jwt;

import Money.Saver.FW.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    /** JWT 토큰 생성 */
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        /** 추가함 */
        Key secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입 (JWT)
                .setIssuer(jwtProperties.getIssuer()) // 이슈 발급자
                .setIssuedAt(now) // 내용 iat - 현재시간
                .setExpiration(expiry) // 내용 exp - expiry 멤버 변수값
                .setSubject(user.getEmail()) // 내용 sub - 유저 이메일
                .claim("id", user.getId()) // 클레임 id - 유저 ID
                .signWith(secretKey, SignatureAlgorithm.HS256) // 수정된 signWith
                .compact();
    }

    /** 토큰 유효성 검증 */
    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /** 토큰 기반으로 인증정보 가져옴 */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    /** 토큰 기반으로 유저 ID 가져옴 */
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    /**  */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}