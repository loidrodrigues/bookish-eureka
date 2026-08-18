package br.com.loidpadre.segundo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(@NotBlank(message = "O nome é obrigatorio") String nome,
        @NotBlank(message = "O e-mail é obrigatorio") @Email(message = "Formato de E-mail inválido") String email,
        @NotBlank(message = "A senha é obrigatorio") @Size(min = 6) String senha) {

}
