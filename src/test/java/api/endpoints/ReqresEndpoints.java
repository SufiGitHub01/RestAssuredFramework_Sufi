package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.Reqres;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresEndpoints {
	
	//CRUD operations
	
	public static Response createUser(Reqres payload){
	
		Response response =
		given()
		     .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		     .body(payload)
		
		.when()
		     .post(Routes.post_url);
		
		return response;
		
	}
	
	public static Response readUser(int id) {
		
		Response response =
		given()
		    .pathParam("id", id)
		    
		.when()
		     .get(Routes.get_url);
		
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
			       .put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(int id) {
		
		Response response =
				given()
				    .pathParam("id", id)
				    
				.when()
				     .delete(Routes.delete_url);
				
				return response;
	}

}
