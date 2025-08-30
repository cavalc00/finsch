package br.com.personal.model.dto;

import br.com.personal.model.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AgendamentoTransferenciaResponseDTO {

    private Long id;

    private BigDecimal valor;

    private BigDecimal taxa;

    private BigDecimal valorTotal;

    private String contaDestino;

    private String contaOrigem;

    private LocalDate dataTransferencia;

    private LocalDate dataAgendamento;

    private StatusAgendamento status;
}
