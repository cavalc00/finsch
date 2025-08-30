package br.com.personal.service.strategy;

import br.com.personal.service.strategy.base.TaxaStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TaxaDezDiasStrategy implements TaxaStrategy {

    private final BigDecimal TAXA = BigDecimal.valueOf(0.0);

    @Override
    public boolean aplicavel(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        long totalDias = dataAgendamento.until(dataTransferencia, ChronoUnit.DAYS);
        return totalDias >= 1 && totalDias <= 10;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor;
    }

    @Override
    public BigDecimal getTaxa() {
        return TAXA.setScale(1);
    }
}
