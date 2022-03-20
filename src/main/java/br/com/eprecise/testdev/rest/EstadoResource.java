package br.com.eprecise.testdev.rest;

import br.com.eprecise.testdev.domain.service.EstadoService;
import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;
import br.com.eprecise.testdev.domain.service.dto.CreateEstadoRequest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** Controler, Classe onde fica todos os ednpoints de /estados
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {

    private EstadoService estadoService;

    /**
     * Construtor para injetar as dependencias
     * @param estadoService
     */
    @Inject
    public EstadoResource(EstadoService estadoService){
        this.estadoService = estadoService;
    }

    /**
     /** Metodo que persiste um objeto na base de dados
     * @param estadoRequest - Objeto do tipo CreateEstadoRequest
     * @return - Resposta da requisicao, em caso de erro retorna 404. Caso ocorra tudo bem, retorna o objeto que foi persistido
     */
    @POST
    @Transactional
    public Response insert(CreateEstadoRequest estadoRequest){
       return estadoService.insert(estadoRequest);
    }


    /** Metodo que traz todos os registros da tabela na base de dados
     * @return - List de objetos
     */
    @GET
    public Response  findAll(){
       return estadoService.findAll();
    }


    /** Metodo que deleta o registro do objeto na base de dados
     * @param id - Long id do objeto a ser deletado
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
      return estadoService.delete(id);
    }


    /** Metodo que faz um update do registo do objeto na base de dados
     * @param id - Long id do objeto a ser modificado
     * @param cidadeData - objeto do tipo CreateCidadeRequest usado para trazer os novos dados
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    @Transactional
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, CreateCidadeRequest cidadeData){
        return estadoService.update(id,cidadeData);
    }

    /** Metodo que faz a contagem de todos os registro na tabela
     * @return - Integer valor de todos os registro na tabela
     */
    @GET
    @Path("/count")
    public Response countAll(){
        return estadoService.countAll();
    }
}
