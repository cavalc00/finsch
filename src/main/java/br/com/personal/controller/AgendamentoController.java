package br.com.personal.controller;

import br.com.personal.model.dto.AgendamentoTransferenciaRequestDTO;
import br.com.personal.model.dto.AgendamentoTransferenciaResponseDTO;
import br.com.personal.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoTransferenciaResponseDTO> salvarAgendamento(@RequestBody @Valid AgendamentoTransferenciaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.salvarAgendamento(request));
    }
}
