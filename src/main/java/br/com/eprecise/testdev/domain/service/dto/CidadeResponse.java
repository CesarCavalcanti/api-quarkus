package br.com.eprecise.testdev.domain.service.dto;

import br.com.eprecise.testdev.domain.model.Cidade;
import lombok.Data;

/** Classe de resposta, feita para estruturar a resposta que o cliente ira receber
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Data
public class CidadeResponse {

    private Long id;
    private String nome;

    /**
     *
     * @param cidade
     * @return CidadeResponse - Um objeto do tipo CidadeResponse com os atributos Id e Nome,formatando a resposta da requisicao.
     */
    public static CidadeResponse fromEntity(Cidade cidade){
        var response = new CidadeResponse();
        response.setNome(cidade.getNome());
        response.setId(cidade.getId());
        return response;
    }
}
