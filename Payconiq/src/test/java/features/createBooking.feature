Feature: Create Hotel Booking

#Create a new booking and get the booking id
  Scenario Outline: Verify if new booking is being successfully created using CreateBookingAPI
    Given Create Booking Payload with "<firstname>" "<lastname>" <totalprice> "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
    When user calls "CreateBookingAPI" with "Post" http request
    Then the API call got success with status code 200
    And get the bookingId from the current booking response
    And verify created booking details "<lastname>" using "GetBookingAPI"
    
    Examples:
    | firstname  | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
    | Automation | User1    | 3000       | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
    
 
 #Update the first name and last name pf the existing booking   
 @UpdateAndDeleteBooking
 Scenario Outline: Verify current booking is being updated using partial payload
 Given Update current booking with partial payload "<firstname>" "<lastname>"
 When user calls "PartialUpdateAPI" with "Put" http request
 Then the API call got success with status code 200 
 And verify created booking details "<lastname>" using "GetBookingAPI"
 
  Examples:
    | firstname  | lastname   |
    | Reshma     | NewUser    |
 
 #Delete an existing booking   
 @UpdateAndDeleteBooking
 Scenario: Verify current booking is being deleted using booking id
 Given Delete current booking using booking id
 When user calls "DeleteBookingAPI" with "DELETE" http request
 Then the API call got success with status code 201
 And the status line contains "Created"
 
#Get all the booking ids exists 
Scenario: Verify all booking ids that exists without filter
 Given Get all booking ids without filter 
 When user calls "GetBookingIdsAPI" with "Get" http request
 Then the API call got success with status code 200
 And the status line contains "OK" 
 
 #Get the booking id using first name and last name
 @GetBookingIdFilter
 Scenario Outline: Verify booking ids using filter
 Given Get booking ids using "<firstname>" "<lastname>"
 When user calls "GetBookingIdsAPI" with "Get" http request
 Then the API call got success with status code 200
 Examples:
    | firstname  | lastname   |
    | Payconiq   | API        |
    
  