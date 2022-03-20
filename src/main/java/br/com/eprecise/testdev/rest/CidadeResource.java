package br.com.eprecise.testdev.rest;

import br.com.eprecise.testdev.domain.service.CidadeService;
import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** Controller, Classe onde fica todos os ednpoints de /cidades
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {

    private CidadeService cidadeService;

    /**
     * Construtor para injetar depedencias
     * @param cidadeService
     */
    @Inject
    public CidadeResource(CidadeService cidadeService){
        this.cidadeService = cidadeService;
    }


    /**
     * Persistir objeto na base de dados
     * @param cidadeRequest
     * @param estadoId - Long
     * @return -  Resposta da requisicao, em caso de erro retorna 404. Caso ocorra tudo bem, retorna o objeto que foi persistido
     */
    @POST
    @Path("{estadoId}")
    @Transactional
    public Response insert(CreateCidadeRequest cidadeRequest,@PathParam("estadoId") Long estadoId) {
        return cidadeService.insert(cidadeRequest,estadoId);
    }


    /** Metodo que deleta o registro do objeto na base de dados
     * @param id - Long id do objeto a ser deletado
     * @return - Caso ocorra tudo bem o retorno e o status code 204 (noContent), caso negativo retorna 404 (not found)
     */
    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        return cidadeService.delete(id);
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
        return cidadeService.update(id,cidadeData);
    }


    /** Metodo que traz todos os registros da tabela na base de dados
     * @return - List de objetos
     */
    @GET
    public Response  findAll(){
       return cidadeService.findAll();
    }


    /** Metodo que traz todos os registros da tabela na base de dados que fazem parte de determinado Estado
     * @param estadoId - Long id do estado
     * @return Retorna um List de objetos 'Cidade' pertencente ao 'Estado passado como parâmetro'
     */
    @GET
    @Path("{estadoId}")
    @Transactional
    public Response findAllByEstado(@PathParam("estadoId") Long estadoId){
        return cidadeService.findAllByEstado(estadoId);
    }
    /** Metodo que traz todos os registros da tabela na base de dados que tem em seu nome a String passada como parametro
     * @param nome - String
     * @return - Retorna um List de objetos 'Cidade' que tenham em seu nome a string passada como parametro
     */
    @GET
    @Path("nome/{nome}")
    public Response findByName(@PathParam("nome") String nome){
        return cidadeService.findByName(nome);
    }


    /** Metodo que faz a contagem de todos os registro na tabela
     * @return - Integer valor de todos os registro na tabela
     */
    @GET
    @Path("/count")
    public Response countAll(){
        return cidadeService.countAll();
    }

}
