package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    private String email;

    @Min(18)
    private int age;
}
