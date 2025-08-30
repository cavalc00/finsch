package br.com.personal.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaMesmoDiaStrategyTest {

    private TaxaMesmoDiaStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new TaxaMesmoDiaStrategy();
    }

    @Test
    public void testAplicavel_comDatasIguais_deveRetornarTrue() {
        LocalDate hoje = LocalDate.now();
        assertTrue(strategy.aplicavel(hoje, hoje));
    }

    @Test
    public void testAplicavel_comDatasDiferentes_deveRetornarFalse() {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);
        assertFalse(strategy.aplicavel(hoje, amanha));
    }

    @Test
    public void testCalcular_deveAplicarTaxaCorretamente() {
        BigDecimal valor = BigDecimal.valueOf(100);
        BigDecimal esperado = new BigDecimal("102.50");
        BigDecimal resultado = strategy.calcular(valor);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testGetTaxa_deveRetornarTaxaCorreta() {
        assertEquals(BigDecimal.valueOf(2.5), strategy.getTaxa());
    }
}