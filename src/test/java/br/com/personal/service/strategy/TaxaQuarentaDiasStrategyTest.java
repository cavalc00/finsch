package br.com.personal.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaQuarentaDiasStrategyTest {

    private TaxaQuarentaDiasStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new TaxaQuarentaDiasStrategy();
    }

    @Test
    void testAplicavel_comDatasDentroDoIntervalo_deveRetornarTrue() {
        LocalDate hoje = LocalDate.now();
        for (int i = 31; i <= 40; i++) {
            LocalDate agendamento = hoje.plusDays(i);
            assertTrue(strategy.aplicavel(agendamento, hoje));
        }
    }

    @Test
    void testAplicavel_comDatasForaDoIntervalo_deveRetornarFalse() {
        LocalDate hoje = LocalDate.now();

        LocalDate agendamentoProximo = hoje.plusDays(30);
        assertFalse(strategy.aplicavel(agendamentoProximo, hoje));

        LocalDate agendamentoDistante = hoje.plusDays(41);
        assertFalse(strategy.aplicavel(agendamentoDistante, hoje));
    }

    @Test
    void testCalcular_deveAplicarTaxaCorretamente() {
        BigDecimal valor = BigDecimal.valueOf(100);
        BigDecimal esperado = new BigDecimal("104.70");

        BigDecimal resultado = strategy.calcular(valor);
        assertEquals(esperado, resultado);
    }

    @Test
    void testGetTaxa_deveRetornarTaxaCorreta() {
        assertEquals(BigDecimal.valueOf(4.7), strategy.getTaxa());
    }
}