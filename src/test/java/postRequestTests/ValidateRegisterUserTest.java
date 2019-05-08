package postRequestTests;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidateRegisterUserTest {

    private final String registrationEndPoint = "https://reqres.in/api/register";

    @Test
    public void validateRegisterUserTest() {

        Map<String, String> jsonInputMap = new HashMap<String, String>() {{
            put("email", "sydney@fife");
            put("password", "pistol");
        }};

        JSONObject requestJson = getJsonRequestBody(jsonInputMap);

        Response responseRegisterUser = getResponse(requestJson, registrationEndPoint);

        Assert.assertEquals(responseRegisterUser.getStatusCode(), 201);


    }

    @Test
    public void validateRegisterUserFailureTest() {


        Map<String, String> jsonInputMap = new HashMap<String, String>() {{
            put("email", "sydney@fife");
        }};

        JSONObject requestJson = getJsonRequestBody(jsonInputMap);

        Response responseRegisterUser = getResponse(requestJson, registrationEndPoint);

        Assert.assertEquals(responseRegisterUser.getStatusCode(), 400);

    }

    private JSONObject getJsonRequestBody(Map<String, String> values) {

        JSONObject requestJson = new JSONObject();

        try {

            for (Map.Entry<String, String> keyValuePair : values.entrySet())
                requestJson.put(keyValuePair.getKey(), keyValuePair.getValue());

        } catch (JsonException e) {
            e.printStackTrace();
          }

        return requestJson;
    }

    public Response getResponse(JSONObject requestJson, String endPoint) {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        request.body(requestJson.toString());
        Response responseRegister = request.post(endPoint);
        return responseRegister;


    }
}
