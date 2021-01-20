package api_test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*; 
import static org.hamcrest.Matchers.emptyOrNullString;
import com.google.gson.*;
import java.util.List;


public class Find_ID {

final static String url="http://www.omdbapi.com/?apikey=5cbf3ee7&"; //Defining base URL apended an API key


public String bySearch_getID(String searchWord, String movieTitle){ //Searching by a word 
    String ID = null; //ID will be stored here
    
    try {
        Response response =  getResponseFromSearch(searchWord); //Private class is defined for Search by word

    	 JsonPath path = response.jsonPath(); 
    	 List<Movies_result> movies = path.getList("Search",Movies_result.class); //Results are deserialized into public class Movies_Result 
    	    
    	    for(Movies_result m : movies) {
    	    	if(m.getTitle().equals(movieTitle)) { //Making a search within Movies_results and when the title matches the desired Title
    	    		ID = m.getImdbID(); //ID of that movie is taken
    	    		System.out.println("\n ID taken and is"+ ID);
    	    		break;
    	    		}
    	    }
    	    return ID;
    }
    	
    catch(Exception error) {
		System.out.println("Error " + error.getMessage() + ID);
		return null;
    }
  
}


public void IDsearch(String ID){  //Searching by ID to confirm the title, year and released date of the movie exist
	try {
		  given().queryParam("i",ID).when().get(url).then().log().all()
		  		.body("Title",not(emptyOrNullString()))
	            .body("Year",not(emptyOrNullString()))
	            .body("Released",not(emptyOrNullString())); 
	} catch(Exception error) {
		System.out.println("Error " + error.getMessage());
		
}
} 

private static Response getResponseFromSearch(String searchBy) { //Searching by a word

		return given().queryParam("s",searchBy).when().get(url).then().log().all()
				.body("Search.Title",not(emptyOrNullString())).extract().response(); 

	}

	
}





