package br.com.personal.mapper;

import br.com.personal.model.dto.AgendamentoTransferenciaResponseDTO;
import br.com.personal.model.entity.AgendamentoTransferencia;

import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoTransferenciaMapper {

    public static AgendamentoTransferenciaResponseDTO toResponseDTO(AgendamentoTransferencia entity) {
        return AgendamentoTransferenciaResponseDTO.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .taxa(entity.getTaxa())
                .valorTotal(entity.getValorTotal())
                .contaOrigem(entity.getContaOrigem())
                .contaDestino(entity.getContaDestino())
                .dataTransferencia(entity.getDataTransferencia())
                .dataAgendamento(entity.getDataAgendamento())
                .status(entity.getStatus())
                .build();
    }

    public static List<AgendamentoTransferenciaResponseDTO> toResponseDTOList(List<AgendamentoTransferencia> entities) {
        return entities.stream().map(AgendamentoTransferenciaMapper::toResponseDTO).collect(Collectors.toList());
    }
}
