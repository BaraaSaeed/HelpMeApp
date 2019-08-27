package co.grandcircus.HelpMeApp.placedetails;

public class Geometry {

	private Location location;

	public Geometry() {
		super();
	}

	public Geometry(Location location) {
		super();
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + "]";
	}

}
