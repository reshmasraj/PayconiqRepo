package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.assertTrue;

public class StepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static int bookingIdValue;
	String tokenId;

	@Given("Create auth token using CreateAuthAPI")
	public String create_auth_token_using_create_auth_api() throws IOException {
		res = given().spec(requestSpecification()).body(data.createAuthToken());
		user_calls_with_http_request("CreateAuthAPI", "Post");
		tokenId = getJsonPath(response, "token");
		return tokenId;
	}

	@Given("Create Booking Payload with {string} {string} {int} {string} {string} {string} {string}")
	public void create_booking_payload_with(String fName, String lName, int tPrice, String dPaid, String cInDate,
			String cOutDate, String addNeeds) throws IOException {
		boolean deposit = Boolean.parseBoolean(dPaid);
		res = given().spec(requestSpecification())
				.body(data.createBookingPayload(fName, lName, tPrice, deposit, cInDate, cOutDate, addNeeds));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiResource, String method) {
		APIResources resourceAPI = APIResources.valueOf(apiResource);
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		switch (method.toUpperCase()) {
		case "POST":
			response = res.when().post(resourceAPI.getResource());
			break;
		case "GET":
			response = res.when().get(resourceAPI.getResource());
			break;
		case "PUT":
			response = res.when().put(resourceAPI.getResource());
			break;
		case "DELETE":
			response = res.when().delete(resourceAPI.getResource());
			break;
		default:
			break;
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("get the bookingId from the current booking response")
	public void get_the_bookingId_from_the_current_booking_response() throws IOException {
		bookingIdValue = Integer.parseInt(getJsonPath(response, "bookingid"));
		res = given().spec(requestSpecification()).pathParam("id", bookingIdValue);
	}

	@Then("verify created booking details {string} using {string}")
	public void verify_created_booking_details_using(String expectedLastName, String resourceName) throws IOException {
		user_calls_with_http_request(resourceName, "GET");
		String actualLastName = getJsonPath(response, "lastname");
		assertEquals(expectedLastName, actualLastName);
	}

	@Given("Update current booking with partial payload {string} {string}")
	public void update_current_booking_with_partial_payload(String first, String last) throws IOException {
		tokenId = create_auth_token_using_create_auth_api();
		res = given().spec(requestSpecification()).header("Cookie", "token=" + tokenId).pathParam("id", bookingIdValue)
				.body(data.updateBookingPayload(first, last));
	}

	@Given("Delete current booking using booking id")
	public void delete_current_booking_using_booking_id() throws IOException {
		tokenId = create_auth_token_using_create_auth_api();
		res = given().spec(requestSpecification()).header("Cookie", "token=" + tokenId).pathParam("id", bookingIdValue);
	}

	@Then("the status line contains {string}")
	public void the_status_line_contains(String message) {
		assertTrue((response.getStatusLine()).contains(message));
	}

	@Given("Get all booking ids without filter")
	public void get_all_booking_ids_without_filter() throws IOException {
		res = given().spec(requestSpecification());
	}

	@Given("Get booking ids using {string} {string}")
	public void get_booking_ids_using(String fName, String lName) throws IOException {
		res = given().spec(requestSpecification()).queryParam("firstname", fName).queryParam("lastname", lName);
	}

}
