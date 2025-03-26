package com.ms.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record UserRecordDto(@NotBlank String name, @NotBlank @Email String email) {

}
