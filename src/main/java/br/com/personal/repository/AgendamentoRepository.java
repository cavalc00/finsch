package br.com.personal.repository;

import br.com.personal.model.entity.AgendamentoTransferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoTransferencia, Long> {
}
