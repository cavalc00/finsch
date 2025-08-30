package br.com.personal.exception;

import br.com.personal.model.dto.ErroResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> trataArgumentosInvalidos(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponseDTO.builder()
                                .message(e.getFieldError().getDefaultMessage())
                                .build()
                );
    }


    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponseDTO> trataRegraNegocio(RegraNegocioException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponseDTO.builder()
                                .message(e.getMessage())
                                .build()
                );
    }
}
