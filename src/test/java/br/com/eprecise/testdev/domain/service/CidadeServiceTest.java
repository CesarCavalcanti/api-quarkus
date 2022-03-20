package br.com.eprecise.testdev.domain.service;

import br.com.eprecise.testdev.domain.model.Estado;
import br.com.eprecise.testdev.domain.repository.EstadoRepository;
import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;
import br.com.eprecise.testdev.rest.CidadeResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(CidadeResource.class)
class CidadeServiceTest {

    @Inject
    EstadoRepository estadoRepository;
    Long idEstado;

    @BeforeEach
    @Transactional
    public void setUp(){
        var estado = new Estado();
        estado.setNome("Pernambuco");

        estadoRepository.persist(estado);
        idEstado = estado.getId();

    }


    @Test
    @DisplayName("Deve adicionar uma cidade com sucesso")
    public void insertTest(){
        var cidade = new CreateCidadeRequest();
        cidade.setNome("Recife");

       given().contentType(ContentType.JSON).body(cidade).queryParam("id",idEstado).when().post().then().statusCode(201);
    }

    @Test
    @DisplayName("Deve retornar o erro 404 quando o estado for inexistente" )
    public void insertTestError(){
        var cidade = new CreateCidadeRequest();
        cidade.setNome("Olinda");

        given().contentType(ContentType.JSON).body(cidade).queryParam("id",idEstado).when().then().statusCode(404);
    }
}