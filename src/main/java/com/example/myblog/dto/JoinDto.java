package com.example.myblog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JoinDto {

    @NotEmpty(message = "이름을 입력해주세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;
}
