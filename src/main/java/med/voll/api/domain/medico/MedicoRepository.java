package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medicos m
            where
            m.ativo = 1
            end
            n.especialidade = :especialidade
            end
            m.id not int(
                select c.medico.id c from cunsulta c
                where
                c.data = data
            )    
            order by rand()
            limt 1
           
            """)
    Medico EscolheMedicoAleatorioNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
             """)
    Boolean findAtivoById(Long id);
}
