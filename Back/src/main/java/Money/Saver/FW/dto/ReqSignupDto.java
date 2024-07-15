package Money.Saver.FW.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSignupDto {

    @NotBlank(message="이름을 입력해주세요.")
    private String name;

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message="생년월일을 입력해주세요.")
    private String birth;

    @NotBlank(message="핸드폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이번달 목표 금액을 입력해주세요.")
    private double targetMoney;
}
