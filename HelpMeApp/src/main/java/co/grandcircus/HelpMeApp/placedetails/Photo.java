package co.grandcircus.HelpMeApp.placedetails;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer height;
	private Integer width;
	@JsonProperty("html_attributions")
	private String[] attributions;
	@JsonProperty("photo_reference")
	private String reference;

	public Photo() {
		super();
	}

	public Photo(Integer height, Integer width, String[] attributions, String reference) {
		super();
		this.height = height;
		this.width = width;
		this.attributions = attributions;
		this.reference = reference;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String[] getAttributions() {
		return attributions;
	}

	public void setAttributions(String[] attributions) {
		this.attributions = attributions;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Photo [height=" + height + ", width=" + width + ", attributions=" + Arrays.toString(attributions)
				+ ", reference=" + reference + "]";
	}

}
