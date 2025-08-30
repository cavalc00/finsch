package br.com.personal.model.entity;

import br.com.personal.model.enums.StatusAgendamento;
import br.com.personal.util.Constantes;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento_transferencia")
public class AgendamentoTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, precision = 3, scale = 1)
    private BigDecimal taxa;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(nullable = false, length = Constantes.TAMANHO_CONTA)
    private String contaDestino;

    @Column(nullable = false, length = Constantes.TAMANHO_CONTA)
    private String contaOrigem;

    @Column(nullable = false)
    private LocalDate dataTransferencia;

    @Column(nullable = false)
    private LocalDate dataAgendamento;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;
}
