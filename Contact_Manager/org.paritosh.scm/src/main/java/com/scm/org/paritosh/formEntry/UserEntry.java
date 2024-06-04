package com.scm.org.paritosh.formEntry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntry {
   @NotBlank(message = "user name cannot be blank")
    @Size(min=3,message="Min 3 character is required")
    private String username;
    @Email(message="Invalid Email address")
    private String email;
    @NotBlank
    @Size(min=6,message="Min 6 character required")
    private String password;
    @Size(min=8,max=12,message="Invalid Phone number")
    private String phoneNumber;
    @NotBlank
    private String about;
}
