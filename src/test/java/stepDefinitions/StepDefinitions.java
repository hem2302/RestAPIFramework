package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResourceswithEnum;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_Id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		// Set Response Specification for response common attributes
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
//Constructor will be called with value of the resource with you pass
		APIResourceswithEnum getApiType = APIResourceswithEnum.valueOf(resource);
		System.out.println(getApiType.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(getApiType.getResource());
			// } else if (method.equalsIgnoreCase("DELETE")) {
			// response = res.when().delete(getApiType.getResource());

		} else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(getApiType.getResource());

	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);

	}

	@And("the {string} in response body is {string}")
	public void the_in_response_body_is(String keyValue, String expectedValue) {

		// JsonPath js = new JsonPath(response);
		// String finalResponse = response.asString();
		// JsonPath js = new JsonPath(finalResponse);
		assertEquals(parseJsonResponse(response, keyValue), expectedValue);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_Id = parseJsonResponse(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		// call the method - *user_calls_with_http_request* for GET resource
		user_calls_with_http_request(resource, "GET");
		String actualName = parseJsonResponse(response, "name");
		assertEquals(expectedName, actualName);

	}

	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
}
