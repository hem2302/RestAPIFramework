package resources;

public enum APIResourceswithEnum {
	//enum is a special class which is a collection of constants and methods
	AddPlaceAPI("/maps/api/place/add/json"), 
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");

	private String resource;

	APIResourceswithEnum(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
