package br.com.eprecise.testdev.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/** Classe para objetos do tipo Estado, utilizando de Lombok para gerar os codigos boolerplate
 * @author Cesar
 * @version 1.0
 * @since Release 01 da aplicacao
 */
@Entity
@Data
public class Estado  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "nome",unique = true,nullable = false)
    private String nome;

}
