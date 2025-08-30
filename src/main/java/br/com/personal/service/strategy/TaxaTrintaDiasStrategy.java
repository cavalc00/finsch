package br.com.personal.service.strategy;

import br.com.personal.service.strategy.base.TaxaStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TaxaTrintaDiasStrategy implements TaxaStrategy {

    private final BigDecimal TAXA = BigDecimal.valueOf(6.9);

    @Override
    public boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        long totalDias = dataAgendamento.until(dataTransferencia, ChronoUnit.DAYS);
        return totalDias >= 21 && totalDias <= 30;
    }

    @Override
    public BigDecimal getTaxa() {
        return TAXA;
    }
}
