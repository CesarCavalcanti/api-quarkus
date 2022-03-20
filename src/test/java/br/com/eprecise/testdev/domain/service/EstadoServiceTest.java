package br.com.eprecise.testdev.domain.service;

import br.com.eprecise.testdev.domain.service.dto.CreateCidadeRequest;
import br.com.eprecise.testdev.domain.service.dto.CreateEstadoRequest;
import br.com.eprecise.testdev.domain.service.dto.ResponseError;
import br.com.eprecise.testdev.rest.EstadoResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(EstadoResource.class)
class EstadoServiceTest {


    @Test
    @DisplayName("Deve adicionar um estado com sucesso")
    @Order(1)
    public void insertTest(){
        var estado = new CreateEstadoRequest();
        estado.setNome("PE");

        var response = given().contentType(ContentType.JSON).body(estado)
                .when().post().then().extract().response();

        assertEquals(201,response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    @DisplayName("Deve retornar error quando JSON não for válido")
    @Order(2)
    public void insertValidationErrorTest(){
        var estado = new CreateCidadeRequest();
        estado.setNome(null);

        var response = given().contentType(ContentType.JSON).body(estado).when().post()
                .then().extract().response();

        assertEquals(ResponseError.UNPORCESSABLE_ENTITY_STATUS,response.statusCode());
        assertEquals("Validation Error",response.jsonPath().getString("message"));

    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    @Order(3)
    public void findAllCidadeTest(){
        given()
             .contentType(ContentType.JSON)
        .when()
             .get()
                .then().statusCode(200)
                .body("size()", Matchers.is(1));
    }

    @Test
    @DisplayName("Deve deletar um estado com sucesso")
    @Order(4)
    public void updateTest(){
        given().contentType(ContentType.JSON).when().queryParam("id",1).put().then().statusCode(204);
    }

    @Test
    @DisplayName("Deve deletar um estado com sucesso")
    @Order(5)
    public void updateTestError(){
        given().contentType(ContentType.JSON).when().queryParam("id",999).put().then().statusCode(404);
    }



    @Test
    @DisplayName("Deve deletar um estado com sucesso")
    @Order(6)
    public void deleteTest(){
        given().contentType(ContentType.JSON).when().queryParam("id",1).delete().then().statusCode(204);
    }

    @Test
    @DisplayName("Deve deletar um estado com sucesso")
    @Order(7)
    public void deleteTestError(){
        given().contentType(ContentType.JSON).when().queryParam("id",999).delete().then().statusCode(404);
    }

}