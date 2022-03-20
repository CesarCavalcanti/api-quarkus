package br.com.eprecise.testdev.domain.service.dto;

import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** Classe de erro, Recebe os dados que vem na requisicao e faz o tratamento devolvendo o erro formatado
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */

@Data
public class ResponseError {

    public static final int UNPORCESSABLE_ENTITY_STATUS = 422;

    private String message;
    private Collection<FieldError> errors;

    /**Construtor da classe, obrigando a mesma a ser instanciada com seus atributos
     * @param message
     * @param errors
     */
    public ResponseError(String message, Collection<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    /** Recebe uma Constraintviolation como parametro,
     *  ver qual foi os campos que vieram com erro,
     *  faz o tratamento com Stream,
     *  e devolve uma lista de todos os erros e suas determinadas mensagens
     * @param erro
     * @param <T> - tipo generifco de erro
     * @return ResponseError - Retorna o objeto ResponseError
     */
    public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T>> erro){
        List<FieldError> erros = erro
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath()
                        .toString(),cv.getMessage()))
                        .collect(Collectors.toList());

        String message = "Validation Error";

        var responseError = new ResponseError(message,erros);
        return responseError;
    }

    /**
     * metodo usado para identificar statuscodes que nao sao fornecidos pelo quarkus.
     * @param code - status de erro
     * @return Response status - retorna o status de erro desejado.
     */
    public Response withStatusCode(int code){
        return Response.status(code).entity(this).build();
    }
}
