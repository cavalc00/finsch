package br.com.personal.service.strategy;

import br.com.personal.service.strategy.base.TaxaStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TaxaMesmoDiaStrategy implements TaxaStrategy {

    private final BigDecimal TAXA = BigDecimal.valueOf(2.5);

    @Override
    public boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        return dataAgendamento.isEqual(dataTransferencia);
    }

    @Override
    public BigDecimal getTaxa() {
        return TAXA;
    }
}
