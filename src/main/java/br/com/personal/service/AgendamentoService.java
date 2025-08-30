package br.com.personal.service;

import br.com.personal.exception.RegraNegocioException;
import br.com.personal.mapper.AgendamentoTransferenciaMapper;
import br.com.personal.model.dto.AgendamentoTransferenciaRequestDTO;
import br.com.personal.model.dto.AgendamentoTransferenciaResponseDTO;
import br.com.personal.model.entity.AgendamentoTransferencia;
import br.com.personal.model.enums.StatusAgendamento;
import br.com.personal.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final CalculadoraTaxaService calculadoraTaxaService;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              CalculadoraTaxaService calculadoraTaxaService) {
        this.agendamentoRepository = agendamentoRepository;
        this.calculadoraTaxaService = calculadoraTaxaService;
    }

    public List<AgendamentoTransferenciaResponseDTO> listarAgendamentos() {
        List<AgendamentoTransferencia> agendamentoTransferencias = agendamentoRepository.findAll();

        return AgendamentoTransferenciaMapper.toResponseDTOList(agendamentoTransferencias);
    }

    public AgendamentoTransferenciaResponseDTO salvarAgendamento(AgendamentoTransferenciaRequestDTO request) {
        AgendamentoTransferencia agendamentoTransferencia = agendamentoRepository.save(this.getAgendamentoTransferencia(request));

        return AgendamentoTransferenciaMapper.toResponseDTO(agendamentoTransferencia);
    }

    private AgendamentoTransferencia getAgendamentoTransferencia(AgendamentoTransferenciaRequestDTO request) {
        LocalDate dataAgendamento = LocalDate.now();

        this.valida(request);

        return AgendamentoTransferencia.builder()
                .valor(request.getValor())
                .taxa(calculadoraTaxaService.calculaTaxa(request.getDataTransferencia(), dataAgendamento))
                .valorTotal(calculadoraTaxaService.calculaValorTotal(request.getDataTransferencia(), dataAgendamento, request.getValor()))
                .contaDestino(request.getContaDestino())
                .contaOrigem(request.getContaOrigem())
                .dataTransferencia(request.getDataTransferencia())
                .dataAgendamento(dataAgendamento)
                .status(StatusAgendamento.AGENDADO)
                .build();
    }

    private void valida(AgendamentoTransferenciaRequestDTO request) {
        if (request.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraNegocioException("Valor da transferência deve ser maior que zero.");
        }

        if (request.getContaOrigem().equals(request.getContaDestino())) {
            throw new RegraNegocioException("Conta de origem e destino não podem ser iguais.");
        }
    }
}
