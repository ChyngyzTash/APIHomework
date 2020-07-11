package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import groovy.util.ObservableMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


import static Utils.PayloadUtil.getPetPayload;

public class hWork1 {

    @Test
    public void createPets() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");

        HttpPost post = new HttpPost(uriBuilder.build());



        for(int i=200; i<=1200; i++) {
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            HttpEntity entity = new StringEntity(getPetPayload(i, "mat", "do not touch"));

            post.setEntity(entity);
            System.out.println(i+"Created");

        }
        HttpResponse response = client.execute(post);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

    }

//    Deserialize response from https://api.chucknorris.io/jokes/random
//    and print out the joke(value)


    @Test
    public void deserResponse() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https").setHost("api.chucknorris.io").setPath("jokes/random");
        HttpGet get = new HttpGet(uri.build());
        HttpResponse response = client.execute(get);

        ObjectMapper object = new ObjectMapper();

        Map<String,Object> deser = object.readValue(response.getEntity()
                .getContent(), new TypeReference<Map<String, Object>>() {});

        System.out.println("Value is: "+ deser.get("value"));

    }


    //    Deserialize response from http://tronalddump.io/random/quote
//    and print out the "value" and links.self.href


    @Test
    public void tronald() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uri = new URIBuilder();
        uri.setScheme("http").setHost("tronalddump.io").setPath("random/quote");

        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> tronald = objectMapper.readValue(response.getEntity()
                        .getContent(),new TypeReference<Map<String,Object>>() {});

        System.out.println("value is: "+ tronald.get("value"));


        Map<String, List<Map<String , Object>>> embe = (Map<String, List<Map<String , Object>>>) tronald.get("_embedded");


        Map<String,Map<String, String>> link1 = (Map<String, Map<String, String>>) embe.get("author").get(0).get("_links");

        Map<String, Map<String,String>> link2 = (Map<String, Map<String,String>>) embe.get("source").get(0).get("_links");

        Map<String,Map<String, String>> link3  = (Map<String,Map<String, String>>) tronald.get("_links");


        String href1 = link1.get("self").get("href");
        String href2 = link2.get("self").get("href");
        String href3 = link3.get("self").get("href");

        System.out.println("Href 1 is: " + href1);
        System.out.println("Href 2 is: " + href2);
        System.out.println("Href 3 is: " + href3);




//        Map<String, Object> author1 = (Map<String, Object>) embe.get("author");
//        Map<String, List<Object>> link = (Map<String, List<Object>>) author1.get("_links");
//        Map<String, Object> self20 = (Map<String, Object>) link.get("self");
//
//        System.out.println("Href number 2 is: "+self20.get("href"));
////
//
//        Map<String, Object> source2 = (Map<String, Object>) tronald.get("_links");
//        Map<String, String> self3 = (Map<String, String>) source.get("self");
//        System.out.println("Href number 2 is: "+self2.get("href"));

    }


}
