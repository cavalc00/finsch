package br.com.personal.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaVinteDiasStrategyTest {

    private TaxaVinteDiasStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new TaxaVinteDiasStrategy();
    }

    @Test
    public void testAplicavel_comDatasDentroDoIntervalo_deveRetornarTrue() {
        LocalDate hoje = LocalDate.now();
        for (int i = 11; i <= 20; i++) {
            LocalDate agendamento = hoje.plusDays(i);
            assertTrue(strategy.aplicavel(agendamento, hoje));
        }
    }

    @Test
    public void testAplicavel_comDatasForaDoIntervalo_deveRetornarFalse() {
        LocalDate hoje = LocalDate.now();

        LocalDate agendamentoProximo = hoje.plusDays(10);
        assertFalse(strategy.aplicavel(agendamentoProximo, hoje));

        LocalDate agendamentoDistante = hoje.plusDays(21);
        assertFalse(strategy.aplicavel(agendamentoDistante, hoje));
    }

    @Test
    public void testCalcular_deveAplicarTaxaCorretamente() {
        BigDecimal valor = BigDecimal.valueOf(100);
        BigDecimal esperado = new BigDecimal("108.20");

        BigDecimal resultado = strategy.calcular(valor);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testGetTaxa_deveRetornarTaxaCorreta() {
        assertEquals(BigDecimal.valueOf(8.2), strategy.getTaxa());
    }
}