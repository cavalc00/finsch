package br.com.personal.service;

import br.com.personal.exception.RegraNegocioException;
import br.com.personal.service.strategy.TaxaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class CalculadoraTaxaService {

    private final Set<TaxaStrategy> taxaStrategies;

    @Autowired
    public CalculadoraTaxaService(Set<TaxaStrategy> taxaStrategies) {
        this.taxaStrategies = taxaStrategies;
    }

    private TaxaStrategy getTaxaStrategy(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        return taxaStrategies.stream()
                .filter(t -> t.aplicavel(dataTransferencia, dataAgendamento))
                .findFirst()
                .orElseThrow(() -> new RegraNegocioException("Taxa não aplicável para intervalo de datas. Transferência não permitida."));
    }

    public BigDecimal calculaTaxa(LocalDate dataTransferencia, LocalDate dataAgendamento) {
        return this.getTaxaStrategy(dataTransferencia, dataAgendamento).getTaxa();
    }

    public BigDecimal calculaValorTotal(LocalDate dataTransferencia, LocalDate dataAgendamento, BigDecimal valor) {
        return this.getTaxaStrategy(dataTransferencia, dataAgendamento).calcular(valor);
    }
}
