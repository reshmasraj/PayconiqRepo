package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import resources.Utils;

public class Hooks  extends Utils  {
	
	StepDefinition stepDef = new StepDefinition();
	
/*	@Before("@UpdatePartialBooking")
	public void beforeScenario() throws IOException {
		stepDef.create_auth_token_using_create_auth_api();
	}
*/
	
	
	@Before("@UpdateAndDeleteBooking")
	public void beforeScenario() throws IOException {
	if(StepDefinition.bookingIdValue==0) {
		stepDef.create_booking_payload_with("Jim", "Brown", 111, "true", "2018-01-06", "2019-01-05", "breakfast");
		stepDef.user_calls_with_http_request("CreateBookingAPI", "POST");
		stepDef.get_the_bookingId_from_the_current_booking_response();
	}
	}
	
	
	@Before("@GetBookingIdFilter")
	public void beforeGetBookingIdFilter() throws IOException {
		stepDef.create_booking_payload_with("Payconiq", "API", 111, "true", "2018-01-06", "2019-01-05", "breakfast");
		stepDef.user_calls_with_http_request("CreateBookingAPI", "POST");
		stepDef.get_the_bookingId_from_the_current_booking_response();
	}
	
	@After("@GetBookingIdFilter")
	public void afterGetBookingIdFilter() throws IOException {
		stepDef.delete_current_booking_using_booking_id();
		stepDef.user_calls_with_http_request("DeleteBookingAPI", "DELETE");
		
	} 
	
}
