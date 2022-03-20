package br.com.eprecise.testdev.domain.service;

import br.com.eprecise.testdev.domain.model.Cidade;
import br.com.eprecise.testdev.domain.model.Estado;
import br.com.eprecise.testdev.domain.repository.CidadeRepository;
import br.com.eprecise.testdev.domain.repository.EstadoRepository;
import br.com.eprecise.testdev.domain.service.dto.CidadeResponse;
import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

/** Classe de servico, feita para abstrair o contato do controlador com a classe de persistencia
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */

@ApplicationScoped
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;

    /**
     * Construtor para injetar as dependencias
     * @param cidadeRepository
     * @param estadoRepository
     */
    @Inject
    public CidadeService (CidadeRepository cidadeRepository,EstadoRepository estadoRepository){
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    /** Metodo que persiste um objeto na base de dados
     * @param cidadeRequest - Objeto do tipo CreateCidadeRequest
     * @param id - Long id do objeto Estado o qual essa cidade faz parte
     * @return - Resposta da requisicao, em caso de erro retorna 404. Caso ocorra tudo bem, retorna o objeto que foi persistido
     */
    public Response insert(CreateCidadeRequest cidadeRequest,Long id){
        Estado estado = estadoRepository.findById(id);

        if(estado == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Cidade cidade = new Cidade();
        cidade.setNome(cidadeRequest.getNome());
        cidade.setEstado(estado);

        cidadeRepository.persist(cidade);

        return Response.status(Response.Status.CREATED).entity(cidade).build();
    }

    /** Metodo que deleta o registro do objeto na base de dados
     * @param id - Long id do objeto a ser deletado
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    public Response delete(Long id){

        Cidade cidade = cidadeRepository.findById(id);

        if(cidadeRepository.isPersistent(cidade)){
            cidadeRepository.delete(cidade);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /** Metodo que faz um update do registo do objeto na base de dados
     * @param id - Long id do objeto a ser modificado
     * @param cidadeData - objeto do tipo CreateCidadeRequest usado para trazer os novos dados
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    public Response update(Long id,CreateCidadeRequest cidadeData){

        Cidade cidade = cidadeRepository.findById(id);

        if(cidadeRepository.isPersistent(cidade)){
            cidade.setNome(cidadeData.getNome());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /** Metodo que traz todos os registros da tabela na base de dados
     * @return - List de objetos
     */
    public Response  findAll(){
        PanacheQuery<Cidade> query = cidadeRepository
                .findAll(Sort.by("estado"));
        return Response.ok(query.list()).build();
    }

    /** Metodo que traz todos os registros da tabela na base de dados que fazem parte de determinado Estado
     * @param estadoId - Long id do estado
     * @return Retorna um List de objetos 'Cidade' pertencente ao 'Estado passado como parâmetro'
     */
    public Response findAllByEstado(Long estadoId){

        Estado estado = new Estado();
        estado = estadoRepository.findById(estadoId);
        if(estado == null){
            Response.status(Response.Status.NOT_FOUND).build();
        }

        PanacheQuery<Cidade> query = cidadeRepository.find("estado",estado);
        var list = query.list();

        var cidadeResponseList = list.stream()
                .map(CidadeResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(cidadeResponseList).build();
    }

    /** Metodo que traz todos os registros da tabela na base de dados que tem em seu nome a String passada como parametro
     * @param nome - String
     * @return - Retorna um List de objetos 'Cidade' que tenham em seu nome a string passada como parametro
     */
    public Response findByName(String nome){

        PanacheQuery<Cidade> query = cidadeRepository.find("select c from cidade c where lower(c.nome) like '%:" + nome.toLowerCase() + "%'");
        var list = query.list();

        var cidadeResponseList = list.stream()
                .map(CidadeResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(cidadeResponseList).build();
    }

    /** Metodo que faz a contagem de todos os registro na tabela
     * @return - Integer valor de todos os registro na tabela
     */
    public Response countAll(){
        Long count = cidadeRepository.count();
        return Response.ok(count).build();
    }


}
