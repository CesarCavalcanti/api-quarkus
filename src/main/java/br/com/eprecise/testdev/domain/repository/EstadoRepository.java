package br.com.eprecise.testdev.domain.repository;

import br.com.eprecise.testdev.domain.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/** Classe de repositorio, feita para abstrair a persistencia de dados
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

}
