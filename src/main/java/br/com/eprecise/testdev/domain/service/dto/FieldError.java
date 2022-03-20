package br.com.eprecise.testdev.domain.service.dto;

import lombok.Data;

/** Classe de erro, Recebe os erros que vem da requisicao
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */

@Data
public class FieldError {

    private String field;
    private String message;

    /**
     * Construtor da classe, obrigando a classe sempre ser instanciada com os dois atributos
     * @param field
     * @param message
     */

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
