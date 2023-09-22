package demo.stepsdefinations.actions;

import demo.utilies.PayloadReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;


public class requestActions {

    private Response response;
    private JSONObject requestPayload;

    RequestSpecification requestSpec = RestAssured.given().when();


    public void responseBodyShouldContainValueForKey(String value, String key) {
        assertEquals("Value is incorrect for key: " + key,
                value, response.getBody().jsonPath().get(key).toString());
    }

    public void responseCodeShouldBe(int responseCode) {

        assertEquals("Response code is incorrect", responseCode, response.statusCode());
    }

    public Response performFullRequest(String requestType, String url) {
        response = null;

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // switch based on request type
        switch (requestType) {
            case "GET":
                response = RestAssured.given().spec(requestSpec).get(url);
                break;
            case "POST":
                response = RestAssured.given()
                        .spec(requestSpec)
                        .header("Content-Type", "application/json")
                        .body(requestPayload)
                        .post(url);
                break;
            case "DELETE":
                System.out.println(url);
                response = RestAssured.given()
                        .spec(requestSpec)
                        .delete(url);
                break;

        }
        return response;
    }

    public void getRequestBodyFile(String resourceFile) {
        requestPayload = new PayloadReader().readJsonFromFile("payload/createPayLoad/"+resourceFile);
    }

    public void validateResponseSchema(String resourceFile) {
        response.then().body(matchesJsonSchemaInClasspath("schemas/"+resourceFile));
    }
}

