package br.com.personal.model.dto;

import br.com.personal.util.Constantes;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AgendamentoTransferenciaRequestDTO {

    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "Conta destino não pode ser nula")
    @Size(min = Constantes.TAMANHO_CONTA, max = Constantes.TAMANHO_CONTA, message = "Tamanho da conta deve conter 10 caracteres.")
    private String contaDestino;

    @NotNull(message = "Conta origem não pode ser nula")
    @Size(min = Constantes.TAMANHO_CONTA, max = Constantes.TAMANHO_CONTA, message = "Tamanho da conta deve conter 10 caracteres.")
    private String contaOrigem;

    @NotNull(message = "Data transferencia não pode ser nula")
    @FutureOrPresent(message = "A data da transferência deve ser hoje ou no futuro")
    private LocalDate dataTransferencia;
}
