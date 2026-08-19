package br.com.loidpadre.segundo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequestDto(
        @NotBlank(message = "Titulo é obrigatorio") @Size(min = 3, message = "Tarefa precisa ter pelo menos 3 caracteres") String title,
        @NotBlank(message = "Descrição é obrigatoria") String description,
        @NotNull(message = "Usuário é obrigatorio") Long userId) {

}
