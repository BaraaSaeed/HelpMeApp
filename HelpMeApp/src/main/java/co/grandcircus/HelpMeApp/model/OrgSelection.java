package co.grandcircus.HelpMeApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "org_selection")
public class OrgSelection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long selectionId;
	private String name;
	private String keyWords;
	private Boolean idServices;

	public OrgSelection() {
		super();
	}

	public OrgSelection(Long selectionId, String name, String keyWords, Boolean idServices) {
		super();
		this.selectionId = selectionId;
		this.name = name;
		this.keyWords = keyWords;
		this.idServices = idServices;
	}

	public OrgSelection(String name, String keyWords) {
		super();
		this.name = name;
		this.keyWords = keyWords;
	}

	public Long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Boolean getIdServices() {
		return idServices;
	}

	public void setIdServices(Boolean idServices) {
		this.idServices = idServices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrgSelection [selectionId=" + selectionId + ", name=" + name + ", keyWords=" + keyWords
				+ ", idServices=" + idServices + "]";
	}

	
}
