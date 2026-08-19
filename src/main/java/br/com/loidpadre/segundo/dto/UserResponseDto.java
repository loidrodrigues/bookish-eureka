package br.com.loidpadre.segundo.dto;

import java.util.List;

public record UserResponseDto(Long id, String nome, String email, List<TaskResponseDto> task) {

}
