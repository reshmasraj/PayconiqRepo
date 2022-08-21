package resources;

import pojo.BookingDates;
import pojo.CreateAuth;
import pojo.CreateBooking;

public class TestDataBuild {
	pojo.CreateBooking createBooking = new pojo.CreateBooking();
	
	public CreateBooking createBookingPayload(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
		createBooking.setAdditionalneeds(additionalneeds);
		createBooking.setDepositpaid(depositpaid);
		createBooking.setFirstname(firstname);
		createBooking.setLastname(lastname);
		createBooking.setTotalprice(totalprice);
		
		pojo.BookingDates bookingDate = new BookingDates();
		bookingDate.setCheckin(checkin);
		bookingDate.setCheckout(checkout);
		createBooking.setBookingdates(bookingDate);
		
		return createBooking;
		
	}
	
	public CreateAuth createAuthToken() {
		pojo.CreateAuth createAuth = new pojo.CreateAuth();
		createAuth.setUsername("admin");
		createAuth.setPassword("password123");		
		return createAuth;
	}
	
	public CreateBooking updateBookingPayload(String fName, String lName) {
		createBooking.setAdditionalneeds("breakfast");
		createBooking.setDepositpaid(true);
		createBooking.setFirstname(fName);
		createBooking.setLastname(lName);
		createBooking.setTotalprice(111);
		pojo.BookingDates bookingDate = new BookingDates();
		bookingDate.setCheckin("2018-01-06");
		bookingDate.setCheckout("2019-01-05");
		createBooking.setBookingdates(bookingDate);
		
		return createBooking;
		
	}
	

}
