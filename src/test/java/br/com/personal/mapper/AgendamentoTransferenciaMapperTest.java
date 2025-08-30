package br.com.personal.mapper;

import br.com.personal.model.dto.AgendamentoTransferenciaResponseDTO;
import br.com.personal.model.entity.AgendamentoTransferencia;
import br.com.personal.model.enums.StatusAgendamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AgendamentoTransferenciaMapperTest {

    @Test
    void deveConverterEntityParaResponseDTO() {
        AgendamentoTransferencia entity = AgendamentoTransferencia.builder()
                .id(1L)
                .valor(BigDecimal.valueOf(250))
                .taxa(BigDecimal.valueOf(5.5))
                .valorTotal(BigDecimal.valueOf(255.5))
                .contaOrigem("123")
                .contaDestino("456")
                .dataAgendamento(LocalDate.of(2025, 8, 30))
                .dataTransferencia(LocalDate.of(2025, 9, 5))
                .status(StatusAgendamento.AGENDADO)
                .build();

        AgendamentoTransferenciaResponseDTO dto = AgendamentoTransferenciaMapper.toResponseDTO(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getValor()).isEqualByComparingTo("250");
        assertThat(dto.getTaxa()).isEqualByComparingTo("5.5");
        assertThat(dto.getValorTotal()).isEqualByComparingTo("255.5");
        assertThat(dto.getContaOrigem()).isEqualTo("123");
        assertThat(dto.getContaDestino()).isEqualTo("456");
        assertThat(dto.getDataAgendamento()).isEqualTo(LocalDate.of(2025, 8, 30));
        assertThat(dto.getDataTransferencia()).isEqualTo(LocalDate.of(2025, 9, 5));
        assertThat(dto.getStatus()).isEqualTo(StatusAgendamento.AGENDADO);
    }

    @Test
    void deveConverterListaDeEntitiesParaListaDeResponseDTOs() {
        AgendamentoTransferencia ag1 = AgendamentoTransferencia.builder()
                .id(1L)
                .valor(BigDecimal.valueOf(100))
                .taxa(BigDecimal.valueOf(1.5))
                .valorTotal(BigDecimal.valueOf(101.5))
                .contaOrigem("111")
                .contaDestino("222")
                .dataAgendamento(LocalDate.now())
                .dataTransferencia(LocalDate.now().plusDays(5))
                .status(StatusAgendamento.AGENDADO)
                .build();

        AgendamentoTransferencia ag2 = AgendamentoTransferencia.builder()
                .id(2L)
                .valor(BigDecimal.valueOf(200))
                .taxa(BigDecimal.valueOf(2.5))
                .valorTotal(BigDecimal.valueOf(202.5))
                .contaOrigem("333")
                .contaDestino("444")
                .dataAgendamento(LocalDate.now())
                .dataTransferencia(LocalDate.now().plusDays(10))
                .status(StatusAgendamento.AGENDADO)
                .build();

        List<AgendamentoTransferenciaResponseDTO> result =
                AgendamentoTransferenciaMapper.toResponseDTOList(List.of(ag1, ag2));

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getValor()).isEqualByComparingTo("100");
        assertThat(result.get(1).getId()).isEqualTo(2L);
        assertThat(result.get(1).getContaDestino()).isEqualTo("444");
    }
}