package br.com.personal.service.strategy;

import br.com.personal.service.strategy.base.TaxaStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TaxaQuarentaDiasStrategy implements TaxaStrategy {

    private final BigDecimal TAXA = BigDecimal.valueOf(4.7);

    @Override
    public boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        long totalDias = dataAgendamento.until(dataTransferencia, ChronoUnit.DAYS);
        return totalDias >= 31 && totalDias <= 40;
    }

    @Override
    public BigDecimal getTaxa() {
        return TAXA;
    }
}
