package br.com.eprecise.testdev.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


/** Classe para objetos do tipo Cidade, utilizando de Lombok para gerar os codigos boolerplate
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Data
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "nome",nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
