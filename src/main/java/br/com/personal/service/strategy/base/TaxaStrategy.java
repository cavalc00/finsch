package br.com.personal.service.strategy.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public interface TaxaStrategy {
    boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento);

    BigDecimal getTaxa();

    default BigDecimal calcular(BigDecimal valor) {
        BigDecimal porcentagem = getTaxa().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal resultado = valor.add(valor.multiply(porcentagem)).setScale(2, RoundingMode.HALF_UP);

        return resultado;
    }
}
