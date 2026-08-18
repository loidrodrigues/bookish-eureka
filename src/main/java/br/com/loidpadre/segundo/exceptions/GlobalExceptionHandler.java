package br.com.loidpadre.segundo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Essa anotação diz: "Spring, essa classe é o Porta-Voz oficial de erros!"
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Essa anotação diz: "Se o erro for de Validação, chame ESTE método!"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> lidarComErroDeValidacao(MethodArgumentNotValidException ex) {

        // Criamos um mapa (como um dicionário) para guardar "Campo" e "Mensagem"
        Map<String, String> erros = new HashMap<>();

        // O Spring nos dá a lista de erros. Nós varremos a lista e pegamos só o que
        // importa!
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        // Devolvemos o erro 400, mas agora com o nosso mapinha limpo!
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> erroDeNotFound(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}