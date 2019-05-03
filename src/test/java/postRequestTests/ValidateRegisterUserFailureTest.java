package postRequestTests;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateRegisterUserFailureTest {

    @Test
    public void validateRegisterUserTest(){

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");

        JSONObject requestJson = new JSONObject();

        try{
            requestJson.put("email","sydney@fife");

        }

        catch (JsonException e){
            e.printStackTrace();
        }


        request.body(requestJson.toString());
        Response responseRegister = request.post("https://reqres.in/api/register");
        System.out.println(responseRegister.getBody().prettyPrint());
        System.out.println(responseRegister.getStatusCode());
        Assert.assertEquals(responseRegister.getStatusCode(),400);


    }
}
