package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class Praktikum {
    @Test
    public void getWithJsonBdy() {
        RestAssured.baseURI = "https://postman-echo.com";

        Response response = given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args.foo1", equalTo("bar1"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .extract()
                .response();

        String headersHost = response.path("headers.host");
        System.out.println(headersHost);
    }

    @Test
    public void postWithJsonBody() {
        RestAssured.baseURI = "https://httpbin.org";
        JSONObject requestBody = new JSONObject()
                .put("name", "test name")
                .put("age", 18)
                .put("hobby", "sport");

        Response response = given()
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", containsString("test name"))
                .body("headers.Content-Length", equalTo("45"))
                .extract().response();

        response.prettyPrint();

        String origin = response.path("origin");
        System.out.println(origin);
    }

    @Test

    public void getMyInfoStatusCode() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
        given().log().all()
                .auth().oauth2("введи_сюда_свой_токен")
                .get("/api/users/me")
                .then().log().all().statusCode(200);
    }

    @Test
    public void createNewPlaceAndCheckResponse() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
        // File json = new File("newCard.json");
        String json = "{\"name\": \"Очень интересное место\"}";
        Response response =
                given().log().all()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .log().all().statusCode(201);
    }

    @Test
    public void testGetRequest() {
        RestAssured.baseURI = "https://api.example.com";

        given()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("age", equalTo(30));
    }

    @Test
    public void testPostRequest() {
        RestAssured.baseURI = "https://api.example.com";
        String requestBody = "{\"name\": \"Jane Smith\", \"age\": 25}";
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

        @Test
    public void checkAvatarAndTest() {
        RestAssured.baseURI = "https://reqres.in/";
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().get("data");
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Неверный статус-код", 200, statusCode);

        List<String> avatars = response.getBody().jsonPath().getList("data.avatar");
        Assert.assertEquals("Неверное количество аватаров", 6, avatars.size());

        for (String avatar : avatars) {
            Assert.assertNotNull(avatar, "Значение аватара равно null");
        }
    }
    @Test
    public void checkAvatarAndTests() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        List<String> avatars = response.jsonPath().getList("data.avatar");
        Assert.assertEquals(6, avatars.size());
        for (String avatar : avatars) {
            Assert.assertEquals(true, avatar != null);
        }
   }

    @Test
    public void checkAvatar() {
        RestAssured.baseURI = "https://reqres.in/";
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().get("data");
    }
}