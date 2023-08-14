package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Reqres;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresEndpoints2 {
	
	//CRUD operations using properties file
	
	public static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
		return routes;
	}
	
	public static Response createUser(Reqres payload){
		
		Response response =
		given()
		     .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		     .body(payload)
		
		.when()
		     .post(getURL().getString("post_url"));
		
		return response;
		
	}
	
	public static Response readUser(int id) {
		
		Response response =
		given()
		    .pathParam("id", id)
		    
		.when()
		     .get(getURL().getString("get_url"));
		
		return response;
	}
	
	public static Response updateUser(Reqres payload, int id) {
		
		Response response =
				given()
				.contentType(ContentType.JSON)
			     .accept(ContentType.JSON)
			     .body(payload)
			     .pathParam("id", id)
			     
			     .when()
			       .put(getURL().getString("update_url"));
		
		return response;
	}
	
	public static Response deleteUser(int id) {
		
		Response response =
				given()
				    .pathParam("id", id)
				    
				.when()
				     .delete(getURL().getString("delete_url"));
				
				return response;
	}

}
