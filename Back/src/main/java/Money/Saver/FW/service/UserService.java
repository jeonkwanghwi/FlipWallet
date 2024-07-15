package Money.Saver.FW.service;

import Money.Saver.FW.domain.User;
import Money.Saver.FW.dto.ReqSignupDto;
import Money.Saver.FW.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public User signUp(ReqSignupDto reqSignupDto) {
        return userRepository.save(User.builder()
                .name(reqSignupDto.getName())
                .email(reqSignupDto.getEmail())
                .birth(reqSignupDto.getBirth())
                .phoneNumber(reqSignupDto.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(reqSignupDto.getPassword()))
                .targetMoney(reqSignupDto.getTargetMoney())
                .build());
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }


    // UpdateTargetMoney 추가하기
}