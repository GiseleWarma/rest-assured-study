package com.xyzcorp;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FruitTest {
    //Rest-Assured
    //[{"description":"Winter fruit","name":"Apple"},
    // {"description":"Tropical fruit","name":"Pineapple"}]
    @Test
    public void testGetFruits(){
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .body("[0].description", equalTo("Winter fruit"));

    }
    @Test
    public void testPostNewFruit(){
        JSONObject bananaObject = new JSONObject()
                .put("name","banana")
                .put("description","okay a delicious treat");
        System.out.println(bananaObject);

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bananaObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .statusCode(200);


    }


}
