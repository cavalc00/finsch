package br.com.personal.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroResponseDTO {
    private String message;
}
