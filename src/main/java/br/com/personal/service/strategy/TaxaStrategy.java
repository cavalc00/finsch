package br.com.personal.service.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TaxaStrategy {
    boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento);
    BigDecimal calcular(BigDecimal valor);
    BigDecimal getTaxa();
}
