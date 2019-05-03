package getRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


public class ValidateResponseCodeTest {

    @Test
    public void validateResponseCode(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().prettyPrint());
        validateData(response);
    }

    private void validateData(Response response){
        List<Map<String, ?>> actualData = response.jsonPath().getList("data");
        List<Map<String, ?>> expectedData = new ArrayList<>( );
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("id",4);
        map1.put("first_name","Eve");
        map1.put("last_name","Holt");

        expectedData.add(map1);

        map2.put("id",5);
        map2.put("first_name","Charles");
        map2.put("last_name","Morris");

        expectedData.add(map2);

        System.out.println("Size is"+actualData.size());

        //Create a method to return expected data


        for(int i =0; i < 2; i++){
            System.out.println("Id : "+ actualData.get(i).get("id").toString());
           // Assert.assertEquals(actualData.get(i).size(),expectedData.get(i).size(),"Size mismatch");
            Assert.assertEquals(actualData.get(i).get("id").toString(), expectedData.get(i).get("id").toString(),"fail assert id");
            Assert.assertEquals(actualData.get(i).get("first_name").toString(), expectedData.get(i).get("first_name").toString(),"fail assert first name");
            Assert.assertEquals(actualData.get(i).get("last_name").toString(), expectedData.get(i).get("last_name").toString(),"fail assert last name");
        }
    }
}
