package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.ReqresEndpoints;
import api.payload.Reqres;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
@Test(priority=1, dataProvider="Data", dataProviderClass= Dataproviders.class)	
public void testPostuser(String name, String job){
	
	
	    Reqres reqrespayload = new Reqres();
	    reqrespayload.setName(name);
	    reqrespayload.setJob(job);
	    
		Response response= ReqresEndpoints.createUser(reqrespayload);
		reqrespayload.setId(response.jsonPath().getInt("id"));
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
				
	}

//@Test(priority=2, dataProvider="UserNames", dataProviderClass= Dataproviders.class)
public void testDeleteuser(int Id) {
	
	Response response = ReqresEndpoints.deleteUser(Id);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),204);
	
}

}
