Feature: Validating place APIs 
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI 

	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "post" http request 
	Then the API call is success with status code 200 
	And the "status" in response body is "OK" 
	And the "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
	Examples: 
	|name   |language	|address           |
	|abcdef|English 	|World cross center|
	
	Scenario: Verify if the place is being deleted successfully
	Given delete place payload
	When user calls "DeletePlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And the "status" in response body is "OK"