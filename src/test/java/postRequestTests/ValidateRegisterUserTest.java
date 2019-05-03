package postRequestTests;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateRegisterUserTest {

    @Test
    public void validateRegisterUserTest() {


        JSONObject requestJson = new JSONObject();

        try {
            requestJson.put("email", "sydney@fife");
            requestJson.put("password", "pistol");
        } catch (JsonException e) {
            e.printStackTrace();
        }

        Response responseRegisterUser = getResponse(requestJson, "https://reqres.in/api/register");


        System.out.println(responseRegisterUser.getBody().prettyPrint());

        System.out.println(responseRegisterUser.getStatusCode());
        Assert.assertEquals(responseRegisterUser.getStatusCode(), 201);

// create method for repititive actions and combine both test cases into a single class
    }

    @Test
    public void validateRegisterUserFailureTest() {


        JSONObject requestJson = new JSONObject();

        try {
            requestJson.put("email", "sydney@fife");

        } catch (JsonException e) {
            e.printStackTrace();
        }

        Response responseRegisterUser = getResponse(requestJson, "https://reqres.in/api/register");


        System.out.println(responseRegisterUser.getBody().prettyPrint());

        System.out.println(responseRegisterUser.getStatusCode());
        Assert.assertEquals(responseRegisterUser.getStatusCode(), 400);

// create method for repititive actions and combine both test cases into a single class
    }

    public Response getResponse(JSONObject requestJson, String endPoint) {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        request.body(requestJson.toString());
        Response responseRegister = request.post(endPoint);
        return responseRegister;


    }
}
