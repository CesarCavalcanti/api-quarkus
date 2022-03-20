package br.com.eprecise.testdev.domain.service.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/** Classe dto, Recebe os dados que vem na requisicao
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Data
public class CreateCidadeRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

}