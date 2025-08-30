package br.com.personal.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaDezDiasStrategyTest {

    private TaxaDezDiasStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new TaxaDezDiasStrategy();
    }

    @Test
    public void testAplicavel_comDatasDentroDoIntervalo_deveRetornarTrue() {
        LocalDate hoje = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            LocalDate agendamento = hoje.plusDays(i);
            assertTrue(strategy.aplicavel(agendamento, hoje));
        }
    }

    @Test
    public void testAplicavel_comDatasForaDoIntervalo_deveRetornarFalse() {
        LocalDate hoje = LocalDate.now();

        LocalDate agendamentoFuturo = hoje.plusDays(11);

        assertFalse(strategy.aplicavel(agendamentoFuturo, hoje));
        assertFalse(strategy.aplicavel(hoje, hoje));
    }

    @Test
    public void testCalcular_deveRetornarMesmoValor() {
        BigDecimal valor = BigDecimal.valueOf(150);
        assertEquals(valor, strategy.calcular(valor));
    }

    @Test
    public void testGetTaxa_deveRetornarZeroComUmaCasaDecimal() {
        assertEquals(BigDecimal.valueOf(0.0).setScale(1), strategy.getTaxa());
    }
}