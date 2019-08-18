package co.grandcircus.HelpMeApp.model;

public class HudService {

	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "HudService [key=" + key + ", value=" + value + "]";
	}
	
	
}
