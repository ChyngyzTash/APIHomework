package API;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class homeworkAPI {

    @Test
            public void createPets()  {


        for (int i = 200; i <= 1250; i++) {
            given().accept(ContentType.JSON).contentType(ContentType.JSON)
                    .body(PayloadUtil.getPetPayload(i, "Bobik", "do not touch"))
                    .when().post("https://petstore.swagger.io/v2/pet")
                    .then().assertThat().statusCode(200);

            System.out.println(i + " Create");
        }
    }

        @Test
        public void deletePets()  {
            RestAssured.baseURI = "https://petstore.swagger.io";

            for (int i = 200; i <= 1250; i++) {
                RestAssured.basePath= "io/v2"+i;
                int StatusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                        .when().get("https://petstore.swagger.io/v2/pet/" + i).statusCode();
                if (StatusCode == 200){
                    given().accept(ContentType.JSON).contentType(ContentType.JSON).when().delete().then().statusCode(405);
                    System.out.println(i+"Delete");
                }else {

                    System.out.println(i + " Not fount");
                }
            }
        }

        @Test
    public void Characters() throws URISyntaxException, IOException {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder uri = new URIBuilder();
            uri.setScheme("https").setHost("api.got.show").setPath("api/map/characters/byId/5cc0743504e71a0010b852d9");
            HttpGet get = new HttpGet(uri.build());
            HttpResponse response = client.execute(get);

            ObjectMapper object = new ObjectMapper();

            Map<String, Object> all = object.readValue(response.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {});
            System.out.println(all.get("message"));
            System.out.println(all.get("data"));

//            Map<String, Map<String, Object>> title = (Map<String, Map<String, Object>>) all.get("data");
//            System.out.println(title.get("titles"));
//            System.out.println(title.get("books"));
//            System.out.println(title.get("_id"));

            Map<String, Object> any = new LinkedHashMap<>();
            Map<String, Map<String, Object>> title = (Map<String, Map<String, Object>>) all.get("data");
//            Set<String> keys = title.keySet();


            title.keySet().stream().map(key -> "info from API: "+ key+" : "+
                    title.get(key)).forEach(key -> System.out.println(key));


//            title.keySet().stream().forEach(key -> System.out.println(title.get(key)));
//            for(String key: keys) {
//                System.out.println(title.get(key));
//            }
        }



        }
