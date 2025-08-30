package br.com.personal.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TaxaMesmoDiaStrategy implements TaxaStrategy {

    public final BigDecimal TAXA = BigDecimal.valueOf(2.5);

    @Override
    public boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        return dataAgendamento.isEqual(dataTransferencia);
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.add(valor.multiply((TAXA.divide(BigDecimal.valueOf(100)))));
    }

    @Override
    public BigDecimal getTaxa() {
        return TAXA;
    }
}
