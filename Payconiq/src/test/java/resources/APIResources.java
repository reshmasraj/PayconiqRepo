package resources;

public enum APIResources {
	
	CreateBookingAPI("/booking"),
	GetBookingAPI("/booking/{id}"),
	GetBookingIdsAPI("/booking"),
	PartialUpdateAPI("/booking/{id}"),
	DeleteBookingAPI("booking/{id}"),
	CreateAuthAPI("/auth");
	
	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
