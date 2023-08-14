package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.ReqresEndpoints;
import api.payload.Reqres;
import io.restassured.response.Response;

public class ReqresTest {
	
	Faker faker;
	Reqres reqrespayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		
		faker = new Faker();
		reqrespayload = new Reqres();
		reqrespayload.setName(faker.name().name());
		reqrespayload.setJob(faker.job().position());
		
		
	}
	@Test(priority=1)
	public void testPostUser(){
		
		Response response= ReqresEndpoints.createUser(reqrespayload);
		response.then().log().all();
		reqrespayload.setId(response.jsonPath().getInt("id"));
		Assert.assertEquals(response.statusCode(), 201);
		
		
	}
	
	@Test(priority=2)
	public void testGetUserById() {
		
	System.out.println(reqrespayload.getId());
//	System.out.println(ReqresEndpoints.readUser(reqrespayload.getId()));
		Response response = ReqresEndpoints.readUser(reqrespayload.getId());
		response.then().log().all();
//		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testupdateUserById() {
		
		reqrespayload.setName(faker.name().name());
		reqrespayload.setJob(faker.job().position());
		
		Response responseAfterUpdate = ReqresEndpoints.updateUser(reqrespayload, reqrespayload.getId());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUserById() {
		
		Response response = ReqresEndpoints.deleteUser(reqrespayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),204);
	}

}
