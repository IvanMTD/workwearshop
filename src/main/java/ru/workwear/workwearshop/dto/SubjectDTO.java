package ru.workwear.workwearshop.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.workwear.workwearshop.enums.Role;

import java.util.Date;

@Data
@NoArgsConstructor
public class SubjectDTO {
    @NotBlank(message = "The field cannot be empty")
    @Size(min = 4, max = 16, message = "The length of the user name must be from 4 to 16 characters")
    private String username;
    @NotBlank(message = "The field cannot be empty")
    @Size(min = 8,message = "The minimum password length is 8 characters")
    private String password;
    @NotBlank(message = "The field cannot be empty")
    @Size(min = 8,message = "The minimum password length is 8 characters")
    private String passwordCheck;
    @NotNull(message = "The field cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Role role;
    private boolean enabled;
}
