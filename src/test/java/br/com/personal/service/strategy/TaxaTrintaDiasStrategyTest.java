package br.com.personal.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaTrintaDiasStrategyTest {

    private TaxaTrintaDiasStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new TaxaTrintaDiasStrategy();
    }

    @Test
    void testAplicavel_comDatasDentroDoIntervalo_deveRetornarTrue() {
        LocalDate hoje = LocalDate.now();
        for (int i = 21; i <= 30; i++) {
            LocalDate agendamento = hoje.plusDays(i);
            assertTrue(strategy.aplicavel(agendamento, hoje));
        }
    }

    @Test
    public void testAplicavel_comDatasForaDoIntervalo_deveRetornarFalse() {
        LocalDate hoje = LocalDate.now();

        LocalDate agendamentoProximo = hoje.plusDays(20);
        assertFalse(strategy.aplicavel(agendamentoProximo, hoje));

        LocalDate agendamentoDistante = hoje.plusDays(31);
        assertFalse(strategy.aplicavel(agendamentoDistante, hoje));
    }

    @Test
    public void testCalcular_deveAplicarTaxaCorretamente() {
        BigDecimal valor = BigDecimal.valueOf(100);
        BigDecimal esperado = new BigDecimal("106.90");

        BigDecimal resultado = strategy.calcular(valor);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testGetTaxa_deveRetornarTaxaCorreta() {
        assertEquals(BigDecimal.valueOf(6.9), strategy.getTaxa());
    }
}