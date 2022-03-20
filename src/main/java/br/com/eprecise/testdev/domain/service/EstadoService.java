package br.com.eprecise.testdev.domain.service;

import br.com.eprecise.testdev.domain.model.Estado;
import br.com.eprecise.testdev.domain.repository.EstadoRepository;
import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;
import br.com.eprecise.testdev.domain.service.dto.CreateEstadoRequest;
import br.com.eprecise.testdev.domain.service.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Set;

/** Classe de servico, feita para abstrair o contato do controlador com a classe de persistencia
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */

@ApplicationScoped
public class EstadoService {

    private EstadoRepository estadoRepository;
    private Validator validator;

    /**
     * Construtor para injetar as dependencias
     * @param estadoRepository
     * @param validator
     */
    @Inject
    public EstadoService(EstadoRepository estadoRepository, Validator validator){

        this.estadoRepository = estadoRepository;
        this.validator = validator;
    }

    /**
     /** Metodo que persiste um objeto na base de dados
     * @param estadoRequest - Objeto do tipo CreateEstadoRequest
     * @return - Resposta da requisicao, em caso de erro retorna 404. Caso ocorra tudo bem, retorna o objeto que foi persistido
     */
    public Response insert(CreateEstadoRequest estadoRequest){

        Set<ConstraintViolation<CreateEstadoRequest>> result = validator.validate(estadoRequest);
        if(!result.isEmpty()){
            return ResponseError.createFromValidation(result)
                    .withStatusCode(ResponseError.UNPORCESSABLE_ENTITY_STATUS);
        }

        Estado estado = new Estado();
        estado.setNome(estadoRequest.getNome());

        estadoRepository.persist(estado);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(estado)
                .build();
    }

    /** Metodo que traz todos os registros da tabela na base de dados
     * @return - List de objetos
     */
    public Response  findAll(){

        PanacheQuery<Estado> query = estadoRepository.findAll();
        return Response.ok(query.list()).build();
    }

    /** Metodo que deleta o registro do objeto na base de dados
     * @param id - Long id do objeto a ser deletado
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    public Response delete(Long id){

        Estado estado = estadoRepository.findById(id);

        if(estado != null){
            estadoRepository.delete(estado);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /** Metodo que faz um update do registo do objeto na base de dados
     * @param id - Long id do objeto a ser modificado
     * @param cidadeData - objeto do tipo CreateCidadeRequest usado para trazer os novos dados
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    public Response update(Long id, CreateCidadeRequest cidadeData){

        Estado estado = estadoRepository.findById(id);

        if(estado != null){
            estado.setNome(cidadeData.getNome());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /** Metodo que faz a contagem de todos os registro na tabela
     * @return - Integer valor de todos os registro na tabela
     */
    public Response countAll(){
        Long count = estadoRepository.count();
        return Response.ok(count).build();
    }

}
