package Money.Saver.FW.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqCreateAccessToken {
    private String refreshToken;
}