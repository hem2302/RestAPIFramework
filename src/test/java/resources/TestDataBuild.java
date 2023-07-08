package resources;

import java.util.ArrayList;

import pojo.AddApiSetJson;
import pojo.Location;

public class TestDataBuild {

	public AddApiSetJson addPlacePayload(String name, String language, String address) {
		AddApiSetJson api = new AddApiSetJson();
		api.setAccuracy(50);
		api.setName(name);
		api.setPhone_number("0123456789");
		api.setAddress(address);
		api.setWebsite("http://google.com");
		api.setLanguage(language);

		// set location
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		api.setLocation(location);
		// set types by creating an array list as it should be in arraylist
		ArrayList<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop1");
		typesList.add("shop2");
		api.setTypes(typesList);
		return api;
	}
	
	public String deletePlacePayload(String place_Id) {
		return "{\r\n    \"place_id\":\""+place_Id+"\"\r\n}";
	}

	
}
