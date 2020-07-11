package API.apiHomework;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class restAshure {

    @Test
    public void getK() {
        Response response = given().accept(ContentType.JSON).when().get("https://api.got.show/api/map/characters");
        Map<String, Object> deser = response.getBody().as(new TypeRef<Map<String, Object>>() {
        });


        List<Map<String, Object>> data = (List<Map<String, Object>>) deser.get("data");
        Map<String, List<String>> al = new HashMap<>();

        for (Map<String, Object> houses : data) {
            String house = (String) houses.get("house");
            List<String> names = new ArrayList<>();
            al.put(house,names);
        }

//        data.stream().forEach(character -> al.put((String)character.get("house"), new ArrayList<>()));
//
//        data.stream().forEach(character -> al.get(character.get("house")).add((String) character.get("name")));
//
//        System.out.println(al);


        for(Map<String,Object> character: data){
            String house = (String)character.get("house");
            String name = (String) character.get("name");

            al.get(house).add(name);
        }

        System.out.println(al);
    }
}













//        Map<String,List<String>> al = new HashMap<>();
//        List liva = new ArrayList();
//        data.stream().filter(ali->ali.get("house")==null).forEach(nA->liva.add( nA.get("_id")));
////        al.get(liva)
//        System.out.println(liva);

//        for (Map<String, Object> character : data) {
//            String house = (String) character.get("house");
//            Object id =  character.get("_id");
//            System.out.println(house +" -- "+ id);
//
//        }
//

