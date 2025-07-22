package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminForm {
    @NotBlank(message = "姓は必須です")
    private String lastName;
    
    @NotBlank(message = "名は必須です")
    private String firstName;
    
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式で入力してください")
    private String email;
    
    @NotBlank(message = "パスワードは必須です")
    private String password;    
}
