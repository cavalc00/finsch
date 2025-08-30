package br.com.personal.service;

import br.com.personal.exception.RegraNegocioException;
import br.com.personal.service.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculadoraTaxaServiceTest {

    @InjectMocks
    private CalculadoraTaxaService service;

    @Mock
    private TaxaMesmoDiaStrategy taxaMesmoDiaStrategy;

    @Mock
    private TaxaDezDiasStrategy taxaDezDiasStrategy;

    @Mock
    private TaxaVinteDiasStrategy taxaVinteDiasStrategy;

    @Mock
    private TaxaTrintaDiasStrategy taxaTrintaDiasStrategy;

    @Mock
    private TaxaQuarentaDiasStrategy taxaQuarentaDiasStrategy;

    @BeforeEach
    public void setUp() {
        service.taxaStrategies = Set.of(taxaMesmoDiaStrategy,
                taxaDezDiasStrategy,
                taxaVinteDiasStrategy,
                taxaTrintaDiasStrategy,
                taxaQuarentaDiasStrategy);
    }

    @Test
    void testCalculaTaxa_strategyMesmoDiaAplicavel() {
        LocalDate hoje = LocalDate.now();

        when(taxaMesmoDiaStrategy.aplicavel(hoje, hoje)).thenReturn(true);

        when(taxaMesmoDiaStrategy.getTaxa()).thenReturn(BigDecimal.valueOf(2.5));

        BigDecimal taxa = service.calculaTaxa(hoje, hoje);

        assertEquals(BigDecimal.valueOf(2.5), taxa);
        verify(taxaMesmoDiaStrategy, times(1)).getTaxa();
    }

    @Test
    void testCalculaValorTotal_strategyVinteDiasAplicavel() {
        LocalDate hoje = LocalDate.now();
        LocalDate agendamento = hoje.plusDays(15);
        BigDecimal valor = BigDecimal.valueOf(100);

        when(taxaVinteDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(true);
        when(taxaVinteDiasStrategy.getTaxa()).thenReturn(BigDecimal.valueOf(8.2));
        when(taxaVinteDiasStrategy.calcular(valor)).thenCallRealMethod();

        BigDecimal total = service.calculaValorTotal(agendamento, hoje, valor);

        BigDecimal esperado = valor.add(valor.multiply(BigDecimal.valueOf(8.2).divide(BigDecimal.valueOf(100)))).setScale(2);
        assertEquals(esperado, total);
        verify(taxaVinteDiasStrategy, times(1)).calcular(valor);
    }

    @Test
    void testCalculaValorTotal_strategyQuarentaDiasAplicavel() {
        LocalDate hoje = LocalDate.now();
        LocalDate agendamento = hoje.plusDays(35);
        BigDecimal valor = BigDecimal.valueOf(200);

        when(taxaQuarentaDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(true);
        when(taxaQuarentaDiasStrategy.getTaxa()).thenReturn(BigDecimal.valueOf(4.7));
        when(taxaQuarentaDiasStrategy.calcular(valor)).thenCallRealMethod();

        BigDecimal total = service.calculaValorTotal(agendamento, hoje, valor);

        BigDecimal esperado = valor.add(valor.multiply(BigDecimal.valueOf(4.7).divide(BigDecimal.valueOf(100)))).setScale(2);
        assertEquals(esperado, total);
        verify(taxaQuarentaDiasStrategy, times(1)).calcular(valor);
    }

    @Test
    void testStrategyNaoAplicavel_deveLancarExcecao() {
        LocalDate hoje = LocalDate.now();
        LocalDate agendamento = hoje.plusDays(50);

        when(taxaMesmoDiaStrategy.aplicavel(agendamento, hoje)).thenReturn(false);
        when(taxaDezDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(false);
        when(taxaVinteDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(false);
        when(taxaTrintaDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(false);
        when(taxaQuarentaDiasStrategy.aplicavel(agendamento, hoje)).thenReturn(false);

        assertThrows(RegraNegocioException.class, () ->
                service.calculaTaxa(agendamento, hoje));
    }

}