package br.com.personal.controller;

import br.com.personal.model.dto.AgendamentoTransferenciaRequestDTO;
import br.com.personal.model.dto.AgendamentoTransferenciaResponseDTO;
import br.com.personal.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/agendamentos")
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

    @GetMapping
    public ResponseEntity<List<AgendamentoTransferenciaResponseDTO>> listar() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }
}
